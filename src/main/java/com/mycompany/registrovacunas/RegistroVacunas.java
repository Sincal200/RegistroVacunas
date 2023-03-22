/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrovacunas;


import java.io.*;
import java.util.*;

public class RegistroVacunas {
    public HashMap<String, ArrayList<Vacuna>> registros;

    public RegistroVacunas() {
        registros = new HashMap<>();
    }

    public void registrarVacuna(String cui, String nombre, String fecha) {
        // Verificar si ya existen vacunas registradas para este CUI
        if (registros.containsKey(cui)) {
            // Si ya existen vacunas, agregar la nueva vacuna a la lista correspondiente
            ArrayList<Vacuna> vacunas = registros.get(cui);
            vacunas.add(new Vacuna(nombre, fecha));
        } else {
            // Si no existen vacunas, crear una nueva lista y agregar la nueva vacuna
            ArrayList<Vacuna> vacunas = new ArrayList<>();
            vacunas.add(new Vacuna(nombre, fecha));
            registros.put(cui, vacunas);
        }
    }

    public String buscarVacunas(String cui) {
    if (registros.containsKey(cui)) {
        ArrayList<Vacuna> vacunas = registros.get(cui);
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < vacunas.size(); i++) {
            Vacuna vacuna = vacunas.get(i);
            resultado.append(vacuna.getNombre()).append(" (").append(vacuna.getFecha()).append(")");
            if (i != vacunas.size() - 1) {
                resultado.append(", ");
            }
        }
        return resultado.toString();
    } else {
        return "No se encontraron vacunas para el CUI " + cui;
    }
}


    public void cargarRegistros(String archivo) {
    try {
        FileReader fileReader = new FileReader(archivo);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String linea;
        while ((linea = bufferedReader.readLine()) != null) {
            String[] partes = linea.split(",");
            String cui = partes[0];
            ArrayList<Vacuna> vacunas = new ArrayList<>();
            for (int i = 1; i < partes.length; i += 2) {
                String nombre = partes[i];
                String fecha = partes[i + 1];
                vacunas.add(new Vacuna(nombre, fecha));
            }
            registros.put(cui, vacunas);
        }

        bufferedReader.close();
        fileReader.close();
    } catch (IOException e) {
        System.out.println("Error al cargar los registros: " + e.getMessage());
    }
}

     public void guardarRegistros(String nombreArchivo) {
        try {
            // Crear el archivo de texto y el escritor
            FileWriter escritor = new FileWriter(nombreArchivo);
            // Recorrer el HashMap y escribir cada registro en el archivo
            for (Map.Entry<String, ArrayList<Vacuna>> entry : registros.entrySet()) {
                String cui = entry.getKey();
                ArrayList<Vacuna> vacunas = entry.getValue();
                // Escribir el CUI
                escritor.write(cui + ",");
                // Recorrer la lista de vacunas correspondiente y escribirlas en el archivo
                for (Vacuna vacuna : vacunas) {
                    escritor.write(vacuna.getNombre() + "," + vacuna.getFecha() + ",");
                }
                escritor.write("\n");
                
            }
            // Cerrar el escritor
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error al guardar los registros en el archivo: " + e.getMessage());
        }
    }

}