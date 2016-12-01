package br.com.estoque.negocio;

import java.util.List;

import br.com.estoque.model.Fornecedor;

public interface InterfaceFornecedor {

	/**
	 * Interface para o m�todo save
	 * 
	 * @param fornecedor
	 */
	public void save(Fornecedor fornecedor);

	/**
	 * Interface para o m�todo getFornecedor
	 * 
	 * @param nome
	 * @return
	 */
	public Fornecedor getFornecedor(String nome);

	/**
	 * Interface para o m�todo list
	 * 
	 * @return
	 */
	public List<Fornecedor> list();

	/**
	 * Interface para o m�todo remove
	 * 
	 * @param fornecedor
	 */
	public void remove(Fornecedor fornecedor);

	/**
	 * Interface para o m�todo update
	 * 
	 * @param fornecedor
	 */
	public void update(Fornecedor fornecedor);

}
