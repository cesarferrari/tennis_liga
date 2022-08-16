package com.example.tennis_liga;

public class Competicion {
   private  String id_competition;
    private String competicion;
    private int photo;
    private String tipo_evento;

    public String getId_competition() {
        return id_competition;
    }

    public void setId_competition(String id_competition) {
        this.id_competition = id_competition;
    }

    public String getCompeticion() {
        return competicion;
    }

    public void setCompeticion(String competicion) {
        this.competicion = competicion;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getTipo_evento() {
        return tipo_evento;
    }

    public void setTipo_evento(String tipo_evento) {
        this.tipo_evento = tipo_evento;
    }

    @Override
    public String toString() {
        return
                id_competition +
                        "-" + competicion
                ;
    }


}
