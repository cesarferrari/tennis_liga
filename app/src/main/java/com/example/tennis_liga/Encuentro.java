package com.example.tennis_liga;

public class Encuentro {
    private int id_encuentro;
    private String competition;
    private String name_p1;
    private String last_name_p1;
    private String name_p2;
    private String last_name_p2;
    private String fecha;
    private String type_match;

    public int getId_encuentro() {
        return id_encuentro;
    }

    public void setId_encuentro(int id_encuentro) {
        this.id_encuentro = id_encuentro;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getName_p1() {
        return name_p1;
    }

    public void setName_p1(String name_p1) {
        this.name_p1 = name_p1;
    }

    public String getLast_name_p1() {
        return last_name_p1;
    }

    public void setLast_name_p1(String last_name_p1) {
        this.last_name_p1 = last_name_p1;
    }

    public String getName_p2() {
        return name_p2;
    }

    public void setName_p2(String name_p2) {
        this.name_p2 = name_p2;
    }

    public String getLast_name_p2() {
        return last_name_p2;
    }

    public void setLast_name_p2(String last_name_p2) {
        this.last_name_p2 = last_name_p2;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getType_match() {
        return type_match;
    }

    public void setType_match(String type_match) {
        this.type_match = type_match;
    }
}
