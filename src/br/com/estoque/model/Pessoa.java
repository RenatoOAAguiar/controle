package br.com.estoque.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1826201378042662885L;

	@Column(length = 15, nullable = false)
	private String nome;

	@Column(length = 30, nullable = false)
	private String sobrenome;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataNasc;

	@Column(unique=true, nullable = false)
	private String cpf;
	
	@Column(length = 10, nullable = false)
	private String cep;
	
	@Column(length = 30, nullable = false)
	private String logradouro;

	@Column(length = 30, nullable = false)
	private String setor;

	@Column(nullable = true)
	private String complemento;

	@Column(length = 5, nullable = true)
	private int numero;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
}
