package com.example.t1_practica.entidades;

import java.util.ArrayList;

/**
 * Esta clase nos permite crear notas que mas tarde seran almacenadas.
 */
public class Notas {

    private int id;
    private String nombre;
    private String descripcion;
    private String fecha;
    private String contacto;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }


}
