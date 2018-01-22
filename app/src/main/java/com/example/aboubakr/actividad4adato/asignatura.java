package com.example.aboubakr.actividad4adato;

/**
 * Created by aboubakr on 22/1/18.
 */

public class asignatura {
    private String id,nombre,horas;

    public asignatura(String id, String nombre, String horas) {
        this.id = id;
        this.nombre = nombre;
        this.horas = horas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }
}

