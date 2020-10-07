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

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Dupla.class, mappedBy = "torneio", cascade = CascadeType.REMOVE)
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
	
	public void distribuirJogos() {
		EncerrarController eC = new EncerrarController();
		List<Jogo> jogos = new ArrayList<Jogo>();
		for (Chave chave : this.getChaves()) { // Jogos 1 VS 2
			Jogo jo1 = new Jogo();
			if (chave.getDupla1() != null && chave.getDupla2() != null) {
				jo1.setPartida(chave.getDupla1().toString() + "     X     " + chave.getDupla2().toString());
				jo1.setDupla1(chave.getDupla1());
				jo1.setDupla2(chave.getDupla2());
				jo1.setCategoria(chave.getCategoria());
				jo1.setChave(chave);
			}
			jogos.add(eC.distribuirHorarios(chave, chave.getDupla1(), chave.getDupla2(), jo1));
			chave.setJogos(jogos);
			JogoResource jr = new JogoResource();
			jr.registraJogo(jo1);
			ChaveResource chaver = new ChaveResource();
			chaver.registraChave(chave);

		}
		for (Chave chave : this.getChaves()) { // Jogos 1 VS 3
			Jogo jo2 = new Jogo();
			if (chave.getDupla1() != null && chave.getDupla3() != null) {
				jo2.setPartida(chave.getDupla1().toString() + "     X     " + chave.getDupla3().toString());
				jo2.setDupla1(chave.getDupla1());
				jo2.setDupla2(chave.getDupla3());
				jo2.setCategoria(chave.getCategoria());
				jo2.setChave(chave);
			}
			jogos.add(eC.distribuirHorarios(chave, chave.getDupla1(), chave.getDupla3(), jo2));
			chave.setJogos(jogos);
			JogoResource jr = new JogoResource();
			jr.registraJogo(jo2);
			ChaveResource chaver = new ChaveResource();
			chaver.registraChave(chave);
		}
		for (Chave chave : this.getChaves()) { // Jogos 2 VS 3
			Jogo jo3 = new Jogo();
			if (chave.getDupla2() != null && chave.getDupla3() != null) {
				jo3.setPartida(chave.getDupla2().toString() + "     X     " + chave.getDupla3().toString());
				jo3.setDupla1(chave.getDupla2());
				jo3.setDupla2(chave.getDupla3());
				jo3.setCategoria(chave.getCategoria());
				jo3.setChave(chave);
			}
			jogos.add(eC.distribuirHorarios(chave, chave.getDupla2(), chave.getDupla3(), jo3));
			chave.setJogos(jogos);
			JogoResource jr = new JogoResource();
			jr.registraJogo(jo3);

			ChaveResource chaver = new ChaveResource();
			chaver.registraChave(chave);
		}
	}

	public void retirarSuplentes() {
		int numDuplasSemSuplentes = duplas.size() % 3;
		for (int i = 0; i < numDuplasSemSuplentes; i++) {
			duplas.remove(duplas.size() - 1); // nao salva no banco
		}
	}
	
	

	// Recebe as duplas do torneio ordenadas por ponto
	public void montarChave() {
		retirarSuplentes();
		List<Dupla> primeira = new ArrayList<Dupla>();
		List<Dupla> segunda = new ArrayList<Dupla>();
		List<Dupla> terceira = new ArrayList<Dupla>();
		List<Dupla> quarta = new ArrayList<Dupla>();
		List<Dupla> quinta = new ArrayList<Dupla>();
		List<Dupla> sexta = new ArrayList<Dupla>();
		for (Dupla d : duplas) {
			switch (d.getCategoria()) {
			case "PRIMEIRA":
				primeira.add(d);
				break;
			case "SEGUNDA":
				segunda.add(d);
				break;
			case "TERCEIRA":
				terceira.add(d);
				break;
			case "QUARTA":
				quarta.add(d);
				break;
			case "QUINTA":
				quinta.add(d);
				break;
			case "SEXTA":
				sexta.add(d);
				break;
			}
		}

		primeira.sort(new OrderDuplasPontuacao());
		segunda.sort(new OrderDuplasPontuacao());
		terceira.sort(new OrderDuplasPontuacao());
		quarta.sort(new OrderDuplasPontuacao());
		quinta.sort(new OrderDuplasPontuacao());
		sexta.sort(new OrderDuplasPontuacao());

		distribuirChaves(primeira, "PRIMEIRA");
		distribuirChaves(segunda, "SEGUNDA");
		distribuirChaves(terceira, "TERCEIRA");
		distribuirChaves(quarta, "QUARTA");
		distribuirChaves(quinta, "QUINTA");
		distribuirChaves(sexta, "SEXTA");

	}

	private void distribuirChaves(List<Dupla> duplas, String cat) {
		int numChaves = duplas.size() / 3;

		// Cria chaves
		for (int i = 0; i < numChaves; i++) {
			// criando objetos em loop
			Chave c = new Chave();
			c.setNome("Chave");
			c.setCategoria(cat);
			c.setTorneio(this);
			chaves.add(c);
		}

		// Primeiro linha
		int numDupla = 0;
		int aux = 0;
		for (int i = 0; i < numChaves; i++) {
			chaves.get(aux).setDupla1(duplas.get(numDupla));
			aux++;
			numDupla++;
		}

		// Segunda linha
		aux--;
		for (int i = 0; i < numChaves; i++) {
			chaves.get(aux).setDupla2(duplas.get(numDupla));
			aux--;
			numDupla++;
		}

		// Terceira linha
		aux++;
		for (int i = 0; i < numChaves; i++) {
			chaves.get(aux).setDupla3(duplas.get(numDupla));
			aux++;
			numDupla++;
		}
	   ChaveResource chaver = new ChaveResource();
		for (Chave c : chaves) {
			chaver.registraChave(c);
		}
		
		
	}

	public Torneio bindTorneio(CadastrarTorneio tela) {

		Torneio torneio = new Torneio();
		torneio.setNome(tela.getTextNomeTorneio().getText());
		torneio.setDescricao(tela.getTextDescricaoTorneio().getText());
		torneio.setDatIniJogos(tela.getTextDataInicio().getText());
		torneio.setDatFimJogos(tela.getTextDataFim().getText());
		if (tela.getComboBoxQuadra1().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[0] = converterQuadra(tela.getComboBoxQuadra1().getSelectedItem().toString());
		}
		if (tela.getComboBoxQuadra2().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[1] = converterQuadra(tela.getComboBoxQuadra2().getSelectedItem().toString());
		}
		if (tela.getComboBoxQuadra3().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[2] = converterQuadra(tela.getComboBoxQuadra3().getSelectedItem().toString());
		}
		if (tela.getComboBoxQuadra4().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[3] = converterQuadra(tela.getComboBoxQuadra4().getSelectedItem().toString());
		}
		if (tela.getComboBoxQuadra5().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[4] = converterQuadra(tela.getComboBoxQuadra5().getSelectedItem().toString());
		}
		if (tela.getComboBoxQuadra6().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[5] = converterQuadra(tela.getComboBoxQuadra6().getSelectedItem().toString());
		}

		return torneio;

	}

	public int converterQuadra(String itemSelecionado) {
		switch (itemSelecionado) {
		case "LARANJA":
			return 1;
		case "AZUL":
			return 2;
		case "VERDE":
			return 3;
		}
		return -1;
	}

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
