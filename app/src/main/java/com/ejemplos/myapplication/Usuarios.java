package com.ejemplos.myapplication;

/**
 * Created by xusa on 15/10/2015.
 */
public class Usuarios {
    String nombre;
    String Apellidos;
    Boolean seleccionado;

    public Usuarios(String nombre, String apellidos) {
        this.nombre = nombre;
        Apellidos = apellidos;
        seleccionado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }
}
