package com.example.tennis_liga;

import java.io.Serializable;

public class Personajes  implements Serializable{
    private String name;
    private String l_name;
    private String birthday;
    private String nal;
    private String pais;
    private String address;
    private String size;
    private String style;
    private String hit;
    private String racket;
    private String cord;
    private int rol;
    private String pass;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNal() {
        return nal;
    }

    public void setNal(String nal) {
        this.nal = nal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getRacket() {
        return racket;
    }

    public void setRacket(String racket) {
        this.racket = racket;
    }

    public String getCord() {
        return cord;
    }

    public void setCord(String cord) {
        this.cord = cord;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    private int foto;
    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }
    public String getNombre() {
        return name;
    }

    public void setNombre(String name) {
        this.name = name;
    }
}

