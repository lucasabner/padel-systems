package com.rp4.fourzetaapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rp4.fourzetaapp.dao.JogoDAO;

import java.util.ArrayList;
import java.util.List;

public class Chave {

    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("nome")
    private String nome;
    @Expose
    @SerializedName("categoria")
    private String categoria;
    @Expose
    @SerializedName("dupla1")
    private Dupla dupla1;
    @Expose
    @SerializedName("dupla2")
    private Dupla dupla2;
    @Expose
    @SerializedName("dupla3")
    private Dupla dupla3;
    @Expose
    @SerializedName("jogos")
    private List<Jogo> jogos;
    @Expose
    @SerializedName("torneio")
    private Torneio torneio;

    public List<Jogo> getJogos() {
        return jogos;
    }

    public Chave() {
        this.jogos = new ArrayList<Jogo>();
    }

    public Chave(int id, String nome, String categoria, Dupla dupla1, Dupla dupla2, Dupla dupla3, List<Jogo> jogos) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.dupla1 = dupla1;
        this.dupla2 = dupla2;
        this.dupla3 = dupla3;
        this.jogos = jogos;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Dupla getDupla1() {
        return dupla1;
    }

    public void setDupla1(Dupla dupla1) {
        this.dupla1 = dupla1;
    }

    public Dupla getDupla2() {
        return dupla2;
    }

    public void setDupla2(Dupla dupla2) {
        this.dupla2 = dupla2;
    }

    public Dupla getDupla3() {
        return dupla3;
    }

    public void setDupla3(Dupla dupla3) {
        this.dupla3 = dupla3;
    }
}
