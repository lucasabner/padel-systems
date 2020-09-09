package com.rp4.fourzetaapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable{

	@Expose
	@SerializedName("id")
	private int id;
	@Expose
	@SerializedName("nome")
	private String nome;
	@Expose
	@SerializedName("nickname")
	private String nickname;
	@Expose
	@SerializedName("senha")
	private String senha;
	@Expose
	@SerializedName("circuitos")
	private List<Circuito> circuitos;

	public int getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public List<Circuito> getCircuitos() {
		return circuitos;
	}


	public void setCircuitos(List<Circuito> circuitos) {
		this.circuitos = circuitos;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
