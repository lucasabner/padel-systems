package fourzeta.models;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fourzeta.IElement;
import fourzeta.controllers.desktop.InscricaoController;
import fourzeta.desktop_views.InscricaoDuplas;
import fourzeta.resources.AtletaResource;
import fourzeta.resources.CircuitoResource;
import fourzeta.resources.RankingResource;

@Entity
@JsonIgnoreProperties("duplas")
public class Atleta implements Serializable, IElement {

	@Id
	@Column()
	private long cpf;

	private String nome;

	@Column(name = "Telefone")
	private String tel;
	private String email;
	private String dataNascimento;

	
	private AtletaResource ar;
	private Circuito cr;
	private InscricaoDuplas tela;
	private InscricaoController inscricao;
	private RankingResource rr;

//	@Enumerated(EnumType.ORDINAL)
	private String sexo;

	// Dessa maneira o Ranking que vai ter o ID do atleta
	@OneToMany(mappedBy = "atleta")
	@Cascade(CascadeType.ALL)
	private List<Ranking> rankings;

	public List<Ranking> getRankings() {
		return rankings;
	}

	public void setRankings(List<Ranking> rankings) {
		this.rankings = rankings;
	}

	public Atleta() {
		this.rankings = new ArrayList<Ranking>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo.toUpperCase();
	}

	public int getId() {
		return (int) cpf;
	}

	public void setId(long id) {
		this.cpf = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean verificarCpf(Dupla dupla) throws MalformedURLException, RemoteException, NotBoundException {
		cr.getCircuito();
		Ranking rank1 = new Ranking();
		Ranking rank2 = new Ranking();
		rank1.setCategoria(tela.getComboCategoria().getSelectedItem().toString());
		rank1.setCircuito(cr);
		rank1.setPontos(0);

		rank2.setCategoria(tela.getComboCategoria().getSelectedItem().toString());
		rank2.setCircuito(cr);
		rank2.setPontos(0);

		boolean encontrado1 = false, encontrado2 = false;

		ar = new AtletaResource();
		for (Atleta a : ar.listaAtletas()) {
			if (a.getId() == dupla.getAtleta1().getId()) {
				dupla.getAtleta1().getRankings().add(inscricao.getRankingAtleta(a));
				dupla.setAtleta1(a);
				encontrado1 = true;

			} else if (a.getId() == dupla.getAtleta2().getId()) {
				dupla.setAtleta2(a);
				dupla.getAtleta2().getRankings().add(inscricao.getRankingAtleta(a));
				encontrado2 = true;
			}
		}
		rr = new RankingResource();
		if (encontrado1 == false && encontrado2 == false) {
			dupla.getAtleta1().getRankings().add(rank1);
			rank1.setAtleta(dupla.getAtleta1());

			dupla.getAtleta2().getRankings().add(rank2);
			rank2.setAtleta(dupla.getAtleta2());
			dupla.setPonTotal("" + rank1.getPontos() + rank2.getPontos());
			rr.registraRanking(rank1);
			rr.registraRanking(rank2);

		} else if (encontrado1 == false && encontrado2 == true) {
			dupla.getAtleta1().getRankings().add(rank1);
			rank1.setAtleta(dupla.getAtleta1());
			dupla.setPonTotal("" + rank1.getPontos() + rank2.getPontos());
			rr.registraRanking(rank1);
			ar.registraAtleta(dupla.getAtleta1());
		} else if (encontrado1 == true && encontrado2 == false) {
			dupla.getAtleta2().getRankings().add(rank2);
			rank2.setAtleta(dupla.getAtleta2());
			dupla.setPonTotal("" + rank1.getPontos() + rank2.getPontos());
			rr.registraRanking(rank2);
			ar.registraAtleta(dupla.getAtleta2());
		}

		return true;
	}


}
