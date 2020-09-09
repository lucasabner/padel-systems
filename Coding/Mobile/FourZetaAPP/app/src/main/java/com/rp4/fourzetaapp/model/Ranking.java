package com.rp4.fourzetaapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ranking implements Comparable<Ranking>{

    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("´pntos")
    private int pontos;
    @Expose
    @SerializedName("atleta")
    private Atleta atleta;
    @Expose
    @SerializedName("circuito")
    private Circuito circuito;

    private String categoria;

    public int getId() {
        return id;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    public Circuito getCircuito() {
        return circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public int compareTo(Ranking arg0) {
        boolean maior = arg0.getId() > this.getId();
        if (maior)
            return -1;
        else
            return 1;
    }
}
