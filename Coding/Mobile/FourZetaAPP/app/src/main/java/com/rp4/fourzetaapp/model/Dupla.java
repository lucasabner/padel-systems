package com.rp4.fourzetaapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Dupla {

    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("torneio")
    private Torneio torneio;
    @Expose
    @SerializedName("categoria")
    private String categoria;
    @Expose
    @SerializedName("impedimento")
    private String impedimento;
    @Expose
    @SerializedName("atleta1")
    private Atleta atleta1;
    @Expose
    @SerializedName("atleta2")
    private Atleta atleta2;
    @Expose
    @SerializedName("ponTotal")
    private String ponTotal;

    public int getId() {
        return id;
    }

    public Dupla() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria.toUpperCase();
    }

    public String getPonTotal() {
        return ponTotal;
    }

    public Torneio getTorneio() {
        return torneio;
    }

    public void setTorneio(Torneio torneio) {
        this.torneio = torneio;
    }

    public void setPonTotal(String ponTotal) {
        this.ponTotal = ponTotal;
    }

    public String toString() {
        return this.atleta1.getNome() + " / " + this.atleta2.getNome();
    }

    public Atleta getAtleta1() {
        return atleta1;
    }

    public void setAtleta1(Atleta atleta1) {
        this.atleta1 = atleta1;
    }

    public Atleta getAtleta2() {
        return atleta2;
    }

    public void setAtleta2(Atleta atleta2) {
        this.atleta2 = atleta2;
    }

    public String getImpedimento() {
        return impedimento;
    }

    public void setImpedimento(String impedimento) {
        this.impedimento = impedimento;
    }
}
