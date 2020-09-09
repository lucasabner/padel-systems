package com.rp4.fourzetaapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Torneio implements Parcelable {

    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("nome")
    private String nome;
    @Expose
    @SerializedName("descricao")
    private String descricao;
    @Expose
    @SerializedName("qtdAtletas")
    private int qtdAtletas;
    @Expose
    @SerializedName("valor")
    private String valor;
    @SerializedName("duplas")
    @Expose(serialize = false, deserialize = true)
    private List<Dupla> duplas;
//    private transient List<Dupla> duplas;
    @Expose
    @SerializedName("circuito")
    private Circuito circuito;
    @Expose
    @SerializedName("chaves")
    private List<Chave> chaves; // Lista deve estar SEMPRE ORDENADA por duplas com maiores pontos
    @Expose
    @SerializedName("datFimInsc")
    private String datFimInsc;
    @Expose
    @SerializedName("detIniJogos")
    private String datIniJogos;
    @Expose
    @SerializedName("datFimJogos")
    private String datFimJogos;
    @Expose
    @SerializedName("distribuicaoJogos")
    private int[] distribuicaoJogos;
    @Expose
    @SerializedName("inscEncerradas")
    private boolean inscEncerradas;


    protected Torneio(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        descricao = in.readString();
//        qtdAtletas = in.readInt();
//        valor = in.readString();
        circuito = in.readParcelable(Circuito.class.getClassLoader());
//        datFimInsc = in.readString();
//        datIniJogos = in.readString();
//        datFimJogos = in.readString();
//        distribuicaoJogos = in.createIntArray();
//        inscEncerradas = in.readByte() != 0;
    }

    public static final Creator<Torneio> CREATOR = new Creator<Torneio>() {
        @Override
        public Torneio createFromParcel(Parcel in) {
            return new Torneio(in);
        }

        @Override
        public Torneio[] newArray(int size) {
            return new Torneio[size];
        }
    };

    public List<Chave> getChavesCat(String categoria) {
        List<Chave> chavesCat = new ArrayList<Chave>();
        chavesCat = this.getChaves();

        List<Chave> cCat = new ArrayList<Chave>();

        for (Chave chave : chavesCat) {
            if (chave.getCategoria().equals(categoria)) {
                cCat.add(chave);
            }
        }
        return cCat;
    }

    public Torneio() {
        this.distribuicaoJogos = new int[6];
        this.chaves = new ArrayList<Chave>();
        this.duplas = new ArrayList<Dupla>();
    }

    public Circuito getCircuito() {
        return circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    public List<Dupla> getDuplas() {
        return duplas;
    }

    public void setDuplas(List<Dupla> duplas) {
        this.duplas = duplas;
    }

    public void setChave(List<Chave> chaves) {
        this.chaves = chaves;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdAtletas() {
        return qtdAtletas;
    }

    public void setQtdAtletas(int qtdAtletas) {
        this.qtdAtletas = qtdAtletas;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<Chave> getChaves() {
        return chaves;
    }

    public void setChaves(List<Chave> chaves) {
        this.chaves = chaves;
    }

    public String getDatFimInsc() {
        return datFimInsc;
    }

    public void setDatFimInsc(String datFimInsc) {
        this.datFimInsc = datFimInsc;
    }

    public String getDatIniJogos() {
        return datIniJogos;
    }

    public void setDatIniJogos(String datIniJogos) {
        this.datIniJogos = datIniJogos;
    }

    public String getDatFimJogos() {
        return datFimJogos;
    }

    public void setDatFimJogos(String string) {
        this.datFimJogos = string;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int[] getDistribuicaoJogos() {
        return distribuicaoJogos;
    }

    public void setDistribuicaoJogos(int[] distribuicaoJogos) {
        this.distribuicaoJogos = distribuicaoJogos;
    }

    public boolean isInscEncerradas() {
        return inscEncerradas;
    }

    public void setInscEncerradas(boolean inscEncerradas) {
        this.inscEncerradas = inscEncerradas;
    }

    @Override
    public String toString(){
        return this.getNome();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nome);
        dest.writeString(this.descricao);
//        dest.writeInt(this.qtdAtletas);
//        dest.writeString(this.valor);
//        circuito = in.readParcelable(Circuito.class.getClassLoader());
//        dest.writeString(this.datFimInsc);
//        dest.writeString(this.datIniJogos);
//        dest.writeString(this.datFimJogos);
//        distribuicaoJogos = in.createIntArray();
//        inscEncerradas = in.readByte() != 0;
    }
}
