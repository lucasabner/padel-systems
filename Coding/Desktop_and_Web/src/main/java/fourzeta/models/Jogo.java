package fourzeta.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import fourzeta.IElement;

@Entity
public class Jogo implements Comparable<Jogo>, Serializable, IElement {
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private int id;
	
	private String partida;
	
	@OneToOne()
	private Dupla dupla1;
	
	@OneToOne()
	private Dupla dupla2;

	@ManyToOne
	private Chave chave;

	@OneToOne
	private Quadra quadra;
	
	// lower
	private String categoria;

	private String data;
	
	private String horario;

	private String placar;
	
	private String etapa;


	public String getEtapa() {
		return etapa;
	}


	public void setEtapa(String etapa) {
		this.etapa = etapa.toUpperCase();
	}


	public Jogo() {
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



	public String getHorario() {
		return horario;
	}



	public void setHorario(String horario) {
		this.horario = horario;
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

	public String getDuplas() {
		return this.partida;
	}


	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
