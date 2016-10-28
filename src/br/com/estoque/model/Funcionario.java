package br.com.estoque.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Funcionario", schema = "estoque")
public class Funcionario extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7686326325346456719L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "permissao_id")
	private Permissao permissao;

	@Column
	private String senha;

	@Column
	private String login;

	private static int permissaoLogar;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public static int getPermissaoLogar() {
		return permissaoLogar;
	}

	public static void setPermissaoLogar(int permissaoLogar) {
		Funcionario.permissaoLogar = permissaoLogar;
	}

}