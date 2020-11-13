package com.example.danielfigueroa_semana13;

public class Usuario {

    private String id;
    private String nombre;


    public Usuario() {
    }

    public Usuario (String id, String nombre) {
        this.nombre = nombre;
        this.id = id;
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
}
