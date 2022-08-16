package com.example.tennis_liga;

import java.io.Serializable;

public class Resultados1 implements Serializable{
    private int games=0;
    private int sets;
    private int wins;
    private int loses;
    private int reassambled;
    private int againts;
    private int tie_break;
    private int third_set;
    private int totalTercer;
    private int totalG;
    private int totalS;
    private int totalTie;
    private int totalRemont;
    private int superTotalG;
    private int superTotalS;
    private int restaTercer;
    private int restaTie;
    private int totalW;
    private String nombre;
    private int first,second,third,fourth,fifth;
    private String urlImagePrincipal,urlImageGanados,urlImagePerdidos,urlImageFrecuentes;

    public Resultados1(int games, int sets, int wins, int loses, int reassambled, int againts, int tie_break, int third_set) {
        this.games = games;
        this.sets = sets;
        this.wins = wins;
        this.loses = loses;
        this.reassambled = reassambled;
        this.againts = againts;
        this.tie_break = tie_break;
        this.third_set = third_set;
    }
    public Resultados1(){

    }

    public String getUrlImagePrincipal() {
        return urlImagePrincipal;
    }

    public void setUrlImagePrincipal(String urlImagePrincipal) {
        this.urlImagePrincipal = urlImagePrincipal;
    }

    public String getUrlImageGanados() {
        return urlImageGanados;
    }

    public void setUrlImageGanados(String urlImageGanados) {
        this.urlImageGanados = urlImageGanados;
    }

    public String getUrlImagePerdidos() {
        return urlImagePerdidos;
    }

    public void setUrlImagePerdidos(String urlImagePerdidos) {
        this.urlImagePerdidos = urlImagePerdidos;
    }

    public String getUrlImageFrecuentes() {
        return urlImageFrecuentes;
    }

    public void setUrlImageFrecuentes(String urlImageFrecuentes) {
        this.urlImageFrecuentes = urlImageFrecuentes;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getThird() {
        return third;
    }

    public void setThird(int third) {
        this.third = third;
    }

    public int getFourth() {
        return fourth;
    }

    public void setFourth(int fourth) {
        this.fourth = fourth;
    }

    public int getFifth() {
        return fifth;
    }

    public void setFifth(int fifth) {
        this.fifth = fifth;
    }

    public int getTotalW() {
        return totalW;
    }

    public void setTotalW(int totalW) {
        this.totalW = totalW;
    }

    public int getTotalTercer() {
        return totalTercer;
    }

    public void setTotalTercer(int totalTercer) {
        this.totalTercer = totalTercer;
    }

    public int getTotalG() {
        return totalG;
    }

    public void setTotalG(int totalG) {
        this.totalG = totalG;
    }

    public int getTotalS() {
        return totalS;
    }

    public void setTotalS(int totalS) {
        this.totalS = totalS;
    }

    public int getTotalTie() {
        return totalTie;
    }

    public void setTotalTie(int totalTie) {
        this.totalTie = totalTie;
    }

    public int getTotalRemont() {
        return totalRemont;
    }

    public void setTotalRemont(int totalRemont) {
        this.totalRemont = totalRemont;
    }

    public int getSuperTotalG() {
        return superTotalG;
    }

    public void setSuperTotalG(int superTotalG) {
        this.superTotalG = superTotalG;
    }

    public int getSuperTotalS() {
        return superTotalS;
    }

    public void setSuperTotalS(int superTotalS) {
        this.superTotalS = superTotalS;
    }

    public int getRestaTercer() {
        return restaTercer;
    }

    public void setRestaTercer(int restaTercer) {
        this.restaTercer = restaTercer;
    }

    public int getRestaTie() {
        return restaTie;
    }

    public void setRestaTie(int restaTie) {
        this.restaTie = restaTie;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getReassambled() {
        return reassambled;
    }

    public void setReassambled(int reassambled) {
        this.reassambled = reassambled;
    }

    public int getAgaints() {
        return againts;
    }

    public void setAgaints(int againts) {
        this.againts = againts;
    }

    public int getTie_break() {
        return tie_break;
    }

    public void setTie_break(int tie_break) {
        this.tie_break = tie_break;
    }

    public int getThird_set() {
        return third_set;
    }

    public void setThird_set(int third_set) {
        this.third_set = third_set;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
