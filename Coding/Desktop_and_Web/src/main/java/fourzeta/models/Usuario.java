package fourzeta.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import fourzeta.IElement;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable, IElement{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nome;
	
	@Column(name = "nickname", length = 30, nullable = false)
	private String nickname;

	@Column(name = "senha", length = 16, nullable = false)
	private String senha;
	
	@OneToMany(fetch = FetchType.EAGER,  targetEntity = Circuito.class, mappedBy ="usuario")
	@Cascade(CascadeType.ALL)
	private List<Circuito> circuitos;

	public int getId() {
		return id;
	}
	

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public List<Circuito> getCircuitos() {
		return circuitos;
	}


	public void setCircuitos(List<Circuito> circuitos) {
		this.circuitos = circuitos;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
