package fourzeta.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fourzeta.IElement;
import fourzeta.controllers.desktop.EncerrarController;
import fourzeta.desktop_views.CadastrarTorneio;
import fourzeta.resources.ChaveResource;
import fourzeta.resources.JogoResource;

@Entity
@JsonIgnoreProperties("circuito")
public class Torneio implements Serializable, IElement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	private String nome;
	private String descricao;
	@Column(name = "qtdatletas")
	private int qtdAtletas;
	private String valor;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Ranking.class, mappedBy = "torneio", cascade = CascadeType.REMOVE)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<Ranking> rankings;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Dupla.class, mappedBy = "torneio", cascade = CascadeType.REMOVE)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<Quadra> quadras;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Dupla.class, mappedBy = "torneio", cascade = CascadeType.ALL)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<Dupla> duplas;

	@ManyToOne()
	private Circuito circuito;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Chave.class, mappedBy = "torneio", cascade = CascadeType.REMOVE)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<Chave> chaves; // Lista deve estar SEMPRE ORDENADA por duplas com maiores pontos

	private String datFimInsc;

	private String datIniJogos;

	private String datFimJogos;

	@Transient
	private int[] distribuicaoJogos;

	@Column(name = "inscencerradas", nullable = false)
	private boolean inscEncerradas;

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

	



	// Recebe as duplas do torneio ordenadas por ponto
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

	public List<Ranking> getRankings() {
		return rankings;
	}

	public void setRankings(List<Ranking> rankings) {
		this.rankings = rankings;
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

	public List<Quadra> getQuadras() {
		return quadras;
	}

	public void setQuadras(List<Quadra> quadras) {
		this.quadras = quadras;
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

	//
	public void setDistribuicaoJogos(int[] distribuicaoJogos) {
		this.distribuicaoJogos = distribuicaoJogos;
	}

	public boolean isInscEncerradas() {
		return inscEncerradas;
	}

	public void setInscEncerradas(boolean inscEncerradas) {
		this.inscEncerradas = inscEncerradas;
	}

}
