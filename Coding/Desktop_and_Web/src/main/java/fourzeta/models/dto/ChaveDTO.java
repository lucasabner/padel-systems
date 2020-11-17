package fourzeta.models.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fourzeta.models.Jogo;

public class ChaveDTO implements Serializable{

	private int id;

	private String nome;

	private String categoria;

	private int dupla1_id;

	private int dupla2_id;

	private int dupla3_id;

	private List<Integer> jogos_id;

	private int torneio_id;

	public ChaveDTO() {
	}

	public ChaveDTO(int id, int idTorn, int idDupla1, int idDupla2, int idDupla3, List<Jogo> jogos, String nome,
			String cat) {
		this.id = id;
		this.torneio_id = idTorn;
		this.dupla1_id = idDupla1;
		this.dupla2_id = idDupla2;
		this.dupla3_id = idDupla3;
		this.pegaIdJogos(jogos);
		this.nome = nome;
		this.categoria = cat;
		
	}
	
	private void pegaIdJogos(List<Jogo> jogos) {
		jogos_id = new ArrayList<>();
		for (Jogo jogo : jogos) {
			jogos_id.add(jogo.getId());
		}
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	public int getDupla3_id() {
		return dupla3_id;
	}

	public void setDupla3_id(int dupla3_id) {
		this.dupla3_id = dupla3_id;
	}

	public List<Integer> getJogos_id() {
		return jogos_id;
	}

	public void setJogos_id(List<Integer> jogos_id) {
		this.jogos_id = jogos_id;
	}

	public int getTorneio_id() {
		return torneio_id;
	}

	public void setTorneio_id(int torneio_id) {
		this.torneio_id = torneio_id;
	}
	

}
