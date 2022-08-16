package com.example.tennis_liga;

public class Match {
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String tipo_evento;
    private String competicion;
    private int idP1;
    private int idP2;
    private int photoP1;
    private int photoP2;
    private String fecha;

    public String getTipo_evento() {
        return tipo_evento;
    }

    public void setTipo_evento(String tipo_evento) {
        this.tipo_evento = tipo_evento;
    }

    public String getCompeticion() {
        return competicion;
    }

    public void setCompeticion(String competicion) {
        this.competicion = competicion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getIdP1() {
        return idP1;
    }

    public void setIdP1(int idP1) {
        this.idP1 = idP1;
    }

    public int getIdP2() {
        return idP2;
    }

    public void setIdP2(int idP2) {
        this.idP2 = idP2;
    }

    public int getPhotoP1() {
        return photoP1;
    }

    public void setPhotoP1(int photoP1) {
        this.photoP1 = photoP1;
    }

    public int getPhotoP2() {
        return photoP2;
    }

    public void setPhotoP2(int photoP2) {
        this.photoP2 = photoP2;
    }
}
