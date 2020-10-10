package fourzeta.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import fourzeta.IElement;

@Entity
public class Ranking implements Comparable<Ranking>, Serializable, IElement {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Null()
	private int pontos;

	@NotNull()
	@ManyToOne(targetEntity = Atleta.class)
	private Atleta atleta;

	@ManyToOne
	@NotNull()
	private Circuito circuito;

	@ManyToOne
	@NotNull()
	private Torneio torneio;

	@NotNull()
	private String categoria;

	public int getId() {
		return id;
	}

	public Torneio getTorneio() {
		return torneio;
	}

	public void setTorneio(Torneio torneio) {
		this.torneio = torneio;
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
