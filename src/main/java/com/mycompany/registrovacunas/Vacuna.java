
package com.mycompany.registrovacunas;


public class Vacuna {
     private String nombre;
    private String fecha;

    // Constructor de la clase Vacuna
    public Vacuna(String nombre, String fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    // Métodos getters para los atributos de la vacuna
    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }
}
