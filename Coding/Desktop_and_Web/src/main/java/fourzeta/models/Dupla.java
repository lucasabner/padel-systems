package fourzeta.models;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fourzeta.IElement;
import fourzeta.controllers.desktop.InscricaoController;
import fourzeta.desktop_views.InscricaoDuplas;
import fourzeta.resources.AtletaResource;
import fourzeta.resources.DuplaResource;

@Entity
@JsonIgnoreProperties("torneio")
public class Dupla implements Serializable, IElement{

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

	private String ponTotal;

	public int getId() {
		return id;
	}

	public Dupla() {
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

	public String getPonTotal() {
		return ponTotal;
	}
	public Torneio getTorneio() {
		return torneio;
	}

	public void setTorneio(Torneio torneio) {
		this.torneio = torneio;
	}

	public void setPonTotal(String ponTotal) {
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

	public Dupla bindDupla(InscricaoController inscricaoController, InscricaoDuplas tela)
			throws ParseException, RemoteException, MalformedURLException, NotBoundException {
	
		Atleta atleta1 = new Atleta();
		Atleta atleta2 = new Atleta();
	
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
		atleta1.setNome(tela.getTxtNome1().getText());
		String cpfTxt = tela.getTxtCpf1().getText().replaceAll(".", "");
		cpfTxt = tela.getTxtCpf1().getText().replace("-", "");
		atleta1.setId(Long.parseLong(cpfTxt)); 
		atleta1.setTel(tela.getTxtTelefone1().getText());
		atleta1.setEmail(tela.getTxtEmail1().getText());
		if (tela.getCbxMasc1().isSelected()) {
			atleta1.setSexo("MASCULINO");
		} else if (tela.getCbxFem1().isSelected()) {
			atleta1.setSexo("FEMININO");
		} else {
			atleta1.setSexo("OUTRO");
		}
	
		atleta1.setDataNascimento(tela.getTxtDataNascimento1().getText());
	
		atleta2.setNome(tela.getTxtNome2().getText());
		atleta2.setId(Long.parseLong(tela.getTxtCpf2().getText()));
		atleta2.setTel(tela.getTxtTelefone2().getText());
		atleta2.setEmail(tela.getTxtEmail2().getText());
		if (tela.getCbxMasc2().isSelected()) {
			atleta2.setSexo("MASCULINO");
		} else if (tela.getCbxFem2().isSelected()) {
			atleta2.setSexo("FEMININO");
		} else {
			atleta2.setSexo("OUTRO");
	
		}
		setImpedimento(inscricaoController.setarImpedimento(tela.getComboImpedimento().getSelectedItem().toString()));
		setCategoria(tela.getComboCategoria().getSelectedItem().toString());
		atleta2.setDataNascimento(tela.getTxtDataNascimento2().getText());
		setAtleta1(atleta1);
		setAtleta2(atleta2);
		setTorneio(inscricaoController.torneio);
	     inscricaoController.verificaCpf.verificarCpf(this);
		inscricaoController.ar = new AtletaResource();
		inscricaoController.ar.registraAtleta(getAtleta1());
		inscricaoController.ar.registraAtleta(getAtleta2());
		inscricaoController.dr = new DuplaResource();
		inscricaoController.dr.registraDupla(this);
	
		return this;
	}

}
