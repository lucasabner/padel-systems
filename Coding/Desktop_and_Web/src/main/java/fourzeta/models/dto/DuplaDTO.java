package fourzeta.models.dto;

import java.io.Serializable;

public class DuplaDTO implements Serializable {
	private int id;

	private int torneio_id;

	private String categoria;

	private String impedimento;

	private int atleta1_id;

	private int atleta2_id;

	private long ponTotal;
	
	public DuplaDTO() {}

	public DuplaDTO(int id, int idTorn, int idA1, int idA2, String cat, String impedimento, long ponTotal) {
		this.id = id;
		this.torneio_id = idTorn;
		this.atleta1_id = idA1;
		this.atleta2_id = idA2;
		this.categoria = cat;
		this.impedimento = impedimento;
		this.ponTotal = ponTotal;
	}
	
	public int getTorneio_id() {
		return torneio_id;
	}

	public void setTorneio_id(int torneio_id) {
		this.torneio_id = torneio_id;
	}

	public int getAtleta1_id() {
		return atleta1_id;
	}

	public void setAtleta1_id(int atleta1_id) {
		this.atleta1_id = atleta1_id;
	}

	public int getAtleta2_id() {
		return atleta2_id;
	}

	public void setAtleta2_id(int atleta2_id) {
		this.atleta2_id = atleta2_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria.toUpperCase();
	}

	public long getPonTotal() {
		return ponTotal;
	}


	public void setPonTotal(long ponTotal) {
		this.ponTotal = ponTotal;
	}


	public String getImpedimento() {
		return impedimento;
	}

	public void setImpedimento(String impedimento) {
		this.impedimento = impedimento;
	}

}
