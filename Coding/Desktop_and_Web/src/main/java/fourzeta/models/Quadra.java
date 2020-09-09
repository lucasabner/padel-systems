package fourzeta.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import fourzeta.IElement;

@Entity
public class Quadra implements Serializable, IElement{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private int id;

	@ManyToOne
	private Torneio torneio;
	
	private String categoria;
	
	private String nome;

	public Quadra() {

	}
	

	public Torneio getTorneio() {
		return torneio;
	}

	public void setTorneio(Torneio torneio) {
		this.torneio = torneio;
	}



	public String getCategoria() {
		return categoria;
	}



	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public Quadra(String num) {
		this.nome = num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNum() {
		return nome;
	}

	public void setNum(String num) {
		this.nome = num;
	}

	public String toString() {
		return "Quadra " + nome;
	}

}
