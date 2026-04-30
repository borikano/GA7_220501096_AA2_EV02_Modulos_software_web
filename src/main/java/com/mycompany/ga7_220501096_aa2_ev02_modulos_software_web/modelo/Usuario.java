package com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.modelo;

import java.sql.Timestamp;

public class Usuario {

    private int id;
    private String nombre;
    private String correo;
    private String clave;
    private boolean estado;
    private Timestamp fechaCreacion;

    public Usuario() {
    }

    public Usuario(String nombre, String correo, String clave) {
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        this.estado = true;
    }

    public Usuario(int id, String nombre, String correo, String clave, boolean estado, Timestamp fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", correo='" + correo + '\''
                + ", clave='********'"
                + ", estado=" + estado
                + ", fechaCreacion=" + fechaCreacion
                + '}';
    }
}