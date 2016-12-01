package br.com.estoque.model;

import java.sql.Timestamp;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Produto", schema = "estoque")
public class Produto {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 30, nullable = false)
	private String nome;

	@Column(length = 8, nullable = false)
	private int quantidade;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao = new Timestamp(System.currentTimeMillis());
	
	@Column(nullable = false)
	private double valor;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
