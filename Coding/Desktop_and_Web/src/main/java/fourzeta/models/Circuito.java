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
import org.hibernate.annotations.Fetch;
import fourzeta.IElement;
import fourzeta.resources.CircuitoResource;

@Entity
public class Circuito implements Serializable, IElement{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	private String nome;
	private String descricao;
	private CircuitoResource cr;
	private Torneio torneio;



	// Criou a tabela Circuito_Rankings, ou seja, Um circuito tem varios ranks
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Ranking.class, mappedBy ="circuito",cascade = CascadeType.ALL)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<Ranking> rankings;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Torneio.class, mappedBy ="circuito",cascade = CascadeType.ALL)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<Torneio> torneios;
	
	@ManyToOne()
	private Usuario usuario;
	
	public List<Ranking> getRankings() {
		return rankings;
	}

	public void setRankings(List<Ranking> rankings) {
		this.rankings = rankings;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public Circuito() {
		this.rankings = new ArrayList<Ranking>();
		this.torneios = new ArrayList<Torneio>();
	}

	public Circuito(int id, String nome, String descricao, ArrayList<Ranking> rankings, List<Torneio> torneios) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.rankings = rankings;
		this.torneios = torneios;
	}
	
	public Circuito bindCircuito(CadastrarCircuito tela) {
	
	//faz get no banco de Dados
	public Circuito getCircuito() {
		cr = new CircuitoResource();
		Circuito circuito = new Circuito();

		Circuito circuito = new Circuito();
		circuito.setNome(tela.getTextNomeCircuito().getText());
		circuito.setDescricao(tela.getTextDescricaoCircuito().getText());

		return circuito;

	}

		for (Circuito c : cr.listaCircuitos()) {
			for (Torneio t : c.getTorneios()) {
				if (torneio.getNome().equalsIgnoreCase(t.getNome())) {
					circuito = c;
				}
			}
		}
		return circuito;
	}

	public List<Ranking> getRanksByCategoria(String txtCategoria) {
		String categoria = txtCategoria.toUpperCase();
//		switch (txtCategoria) {
//		case "PRIMEIRA":
//			categoria = "PRIMEIRA";
//			break;
//		case "SEGUNDA":
//			categoria = "SEGUNDA";
//			break;
//		case "TERCEIRA":
//			categoria = "TERCEIRA";
//			break;
//		case "QUARTA":
//			categoria = "QUARTA";
//			break;
//		case "QUINTA":
//			categoria = "QUINTA";
//			break;
//		case "INICIANTE":
//			categoria = "INICIANTE";
//			break;
//		default:
//			categoria = "INICIANTE";
//		}

		List<Ranking> ranksCat = new ArrayList<Ranking>();
		ranksCat = this.getPontuacoes();

		List<Ranking> pontCat = new ArrayList<Ranking>();

	 
		for (Ranking pontuacao : ranksCat) {
			if (pontuacao.getCategoria().equals(categoria)) {
				pontCat.add(pontuacao);
			}
		}
		pontCat.sort(new OrderPontuacaoDecrescente());

		return pontCat;
	}

	public Torneio getTorneio(int id) {
		for (Torneio t : this.torneios) {
			if (t.getId() == id) {
				return t;
			}
		}
		return null;
	}

	public List<Ranking> getPontuacoes() {
		return rankings;
	}

	public void setPontuacoes(List<Ranking> ponts) {
		this.rankings = ponts;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Torneio> getTorneios() {
		return torneios;
	}

	public void setTorneios(List<Torneio> torneios) {
		this.torneios = torneios;
	}

	//Sem anotação @Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


}
