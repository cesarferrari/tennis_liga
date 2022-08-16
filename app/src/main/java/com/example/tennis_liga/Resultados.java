package com.example.tennis_liga;

public class Resultados {
    int p1_sets=0;
    int p2_sets=0;int tie_breakP1=0;int tie_breakP2=0;int remontadaP1=0;int remontadaP2=0;int remontadaContraP1=0;
    int remontadaContraP2=0;int winnerP1=0;int winnerP2=0;int loserP1=0;int loserP2=0;int tercerSetP1=0;
    int tercerSetP2=0;int gamesP1=0;int gamesP2=0;
    int id_encuentro=0;
    public Resultados() {
    }

    public int getP1_sets() {
        return p1_sets;
    }

    public void setP1_sets(int p1_sets) {
        this.p1_sets = p1_sets;
    }

    public int getP2_sets() {
        return p2_sets;
    }

    public void setP2_sets(int p2_sets) {
        this.p2_sets = p2_sets;
    }

    public int getTie_breakP1() {
        return tie_breakP1;
    }

    public void setTie_breakP1(int tie_breakP1) {
        this.tie_breakP1 = tie_breakP1;
    }

    public int getTie_breakP2() {
        return tie_breakP2;
    }

    public void setTie_breakP2(int tie_breakP2) {
        this.tie_breakP2 = tie_breakP2;
    }

    public int getRemontadaP1() {
        return remontadaP1;
    }

    public void setRemontadaP1(int remontadaP1) {
        this.remontadaP1 = remontadaP1;
    }

    public int getRemontadaP2() {
        return remontadaP2;
    }

    public void setRemontadaP2(int remontadaP2) {
        this.remontadaP2 = remontadaP2;
    }

    public int getRemontadaContraP1() {
        return remontadaContraP1;
    }

    public void setRemontadaContraP1(int remontadaContraP1) {
        this.remontadaContraP1 = remontadaContraP1;
    }

    public int getRemontadaContraP2() {
        return remontadaContraP2;
    }

    public void setRemontadaContraP2(int remontadaContraP2) {
        this.remontadaContraP2 = remontadaContraP2;
    }

    public int getWinnerP1() {
        return winnerP1;
    }

    public void setWinnerP1(int winnerP1) {
        this.winnerP1 = winnerP1;
    }

    public int getWinnerP2() {
        return winnerP2;
    }

    public void setWinnerP2(int winnerP2) {
        this.winnerP2 = winnerP2;
    }

    public int getLoserP1() {
        return loserP1;
    }

    public void setLoserP1(int loserP1) {
        this.loserP1 = loserP1;
    }

    public int getLoserP2() {
        return loserP2;
    }

    public void setLoserP2(int loserP2) {
        this.loserP2 = loserP2;
    }

    public int getTercerSetP1() {
        return tercerSetP1;
    }

    public void setTercerSetP1(int tercerSetP1) {
        this.tercerSetP1 = tercerSetP1;
    }

    public int getTercerSetP2() {
        return tercerSetP2;
    }

    public void setTercerSetP2(int tercerSetP2) {
        this.tercerSetP2 = tercerSetP2;
    }

    public int getGamesP1() {
        return gamesP1;
    }

    public void setGamesP1(int gamesP1) {
        this.gamesP1 = gamesP1;
    }

    public int getGamesP2() {
        return gamesP2;
    }

    public void setGamesP2(int gamesP2) {
        this.gamesP2 = gamesP2;
    }

    public int getId_encuentro() {
        return id_encuentro;
    }

    public void setId_encuentro(int id_encuentro) {
        this.id_encuentro = id_encuentro;
    }

    @Override
    public String toString() {
        return
                  "juegos "+gamesP1
                ;
    }
}
