package fourzeta.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fourzeta.IElement;

@Entity
@JsonIgnoreProperties("duplas")
public class Atleta implements Serializable, IElement{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String nome;
	@Column()
	private String cpf;
	@Column(name = "Telefone")
	private String tel;
	private String email;

	private String dataNascimento;

//	@Enumerated(EnumType.ORDINAL)
	private String sexo;

	// Dessa maneira criou o Atleta Dupla
//	@ManyToOne()
//	private List<Dupla> duplas;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
