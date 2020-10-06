package fourzeta.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fourzeta.IElement;

@Entity
@JsonIgnoreProperties("torneio")
public class Dupla implements Comparable<Dupla>, Serializable, IElement{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	// Torneio não é mapeado
	@ManyToOne
	private Torneio torneio;

//	@Enumerated(EnumType.ORDINAL)
	private String categoria;

//	@Enumerated(EnumType.STRING)
	private String impedimento;

	// Com o mappedBy aqui criou a tabela Atleta_dupla
	@OneToOne()
	private Atleta atleta1;
	
	@OneToOne()
	private Atleta atleta2;

	private long ponTotal;

	//@Override
	public int getId() {
		return id;
	}

	//Desnecessario pq já vem como padrao,a nao ser q tenha outro
	// construcotr personalizado
	
	
	

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria.toUpperCase();
	}


<<<<<<< HEAD
	public long getPonTotal() {
=======
	public String getPonTotal() {
>>>>>>> parent of d72ccd7... Refatorações na controller
		return ponTotal;
	}
	

	public Torneio getTorneio() {
		return torneio;
	}

	public void setTorneio(Torneio torneio) {
		this.torneio = torneio;
	}

	public void setPonTotal(long ponTotal) {
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
	
	@Override
	public int compareTo(Dupla arg0) {
		boolean maior = arg0.getId() > this.getId();
		if (maior)
			return -1;
		else
			return 1;
	}

}
