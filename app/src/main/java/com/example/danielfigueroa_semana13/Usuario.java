package com.example.danielfigueroa_semana13;

public class Usuario {

    private String id;
    private String nombre;
    private String correo;
    private String contrasena;
    private String contrasenaOtra;



    public Usuario() {
    }

    public Usuario (String id, String nombre, String correo, String contrasena, String contrasenaOtra) {
        this.nombre = nombre;
        this.id = id;
        this.correo = correo;
        this.contrasena = contrasena;
        this.contrasenaOtra = contrasenaOtra;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getContrasenaOtra() {
        return contrasenaOtra;
    }

    public void setContrasenaOtra(String contrasenaOtra) {
        this.contrasenaOtra = contrasenaOtra;
    }
}
