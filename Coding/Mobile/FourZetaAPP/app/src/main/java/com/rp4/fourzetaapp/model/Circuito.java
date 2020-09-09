package com.rp4.fourzetaapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rp4.fourzetaapp.util.OrderPontuacaoDecrescente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Circuito implements Parcelable {

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
    @SerializedName("rankings")
    private List<Ranking> rankings;
    @Expose
    @SerializedName("torneios")
    private List<Torneio> torneios;
    @Expose
    @SerializedName("usuario")
    private Usuario usuario;

    protected Circuito(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        descricao = in.readString();
//        torneios = in.createTypedArrayList(Torneio.CREATOR);
    }

    public static final Creator<Circuito> CREATOR = new Creator<Circuito>() {
        @Override
        public Circuito createFromParcel(Parcel in) {
            return new Circuito(in);
        }

        @Override
        public Circuito[] newArray(int size) {
            return new Circuito[size];
        }
    };

    public List<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public Circuito() {
        this.rankings = new ArrayList<Ranking>();
        this.torneios = new ArrayList<Torneio>();
    }

    public Circuito(int id, String nome, String descricao, ArrayList<Ranking> rankings, List<Torneio> torneios) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.rankings = rankings;
        this.torneios = torneios;
    }

//    public List<Ranking> getRanksByCategoria(String txtCategoria) {
//        String categoria = txtCategoria.toUpperCase();
//
//        List<Ranking> ranksCat = new ArrayList<Ranking>();
//        ranksCat = this.getPontuacoes();
//
//        List<Ranking> pontCat = new ArrayList<Ranking>();
//
//        for (Ranking pontuacao : ranksCat) {
//            if (pontuacao.getCategoria().equals(categoria)) {
//                pontCat.add(pontuacao);
//            }
//        }
//        pontCat.sort(new OrderPontuacaoDecrescente());
//
//        return pontCat;
//    }

    public Torneio getTorneio(int id) {
        for (Torneio t : this.torneios) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public List<Ranking> getPontuacoes() {
        return rankings;
    }

    public void setPontuacoes(List<Ranking> ponts) {
        this.rankings = ponts;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Torneio> getTorneios() {
        return torneios;
    }

    public void setTorneios(List<Torneio> torneios) {
        this.torneios = torneios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
//        dest.writeList(this.rankings);
//        dest.writeList(this.torneios);
    }
}
