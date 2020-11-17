package fourzeta.models.dto;

import java.io.Serializable;


public class JogoDTO implements Serializable {
	
	private int id;
	
	private String partida;
	
	private int dupla1_id;

	private int dupla2_id;

	private int torneio_id;

	private int chave_id;

	private String categoria;

	private String data;
	
	private String horario;

	private String placar;
	
	private String etapa;
	
	public JogoDTO() {}
	
	public JogoDTO(int id, int idTorn, int idChave, int idDupla1, int idDupla2, String cat, String data, String horario,
			String placar, String etapa) {
		this.id = id;
		this.torneio_id = idTorn;
		this.chave_id = idChave;
		this.dupla1_id = idDupla1;
		this.dupla2_id = idDupla2;
		this.categoria = cat;
		this.data = data;
		this.horario = horario;
		this.placar = placar;
		this.etapa = etapa;
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

	public int getDupla1_id() {
		return dupla1_id;
	}

	public void setDupla1_id(int dupla1_id) {
		this.dupla1_id = dupla1_id;
	}

	public int getDupla2_id() {
		return dupla2_id;
	}

	public void setDupla2_id(int dupla2_id) {
		this.dupla2_id = dupla2_id;
	}

	public int getTorneio_id() {
		return torneio_id;
	}

	public void setTorneio_id(int torneio_id) {
		this.torneio_id = torneio_id;
	}

	public int getChave_id() {
		return chave_id;
	}

	public void setChave_id(int chave_id) {
		this.chave_id = chave_id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getPlacar() {
		return placar;
	}

	public void setPlacar(String placar) {
		this.placar = placar;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	
	
}
