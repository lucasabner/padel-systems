package com.rp4.fourzetaapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jogo implements Comparable<Jogo>{

    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("partida")
    private String partida;
    @Expose
    @SerializedName("etapa")
    private int etapa;
    @Expose
    @SerializedName("chave")
    private Chave chave;
    @Expose
    @SerializedName("quadra")
    private Quadra quadra;
    @Expose
    @SerializedName("categoria")
    private String categoria;
    @Expose
    @SerializedName("data")
    private String data;
    @Expose
    @SerializedName("placar")
    private String placar;

    public Jogo() {
    }

    public Jogo(String partida) {
        this.partida = partida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public Quadra getQuadra() {
        return quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    public String getPlacar() {
        return placar;
    }

    public void setPlacar(String placar) {
        this.placar = placar;
    }

    public String[] getDuplas() {
        return this.partida.split("VS");
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        categoria = categoria;
    }

    @Override
    public int compareTo(Jogo arg0) {
        boolean maior = arg0.getId() > this.getId();
        if (maior)
            return -1;
        else
            return 1;
    }

}
