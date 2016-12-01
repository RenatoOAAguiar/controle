package br.com.estoque.negocio;

import java.util.List;

import br.com.estoque.model.Fornecedor;

public interface InterfaceFornecedor {

	/**
	 * Interface para o método save
	 * 
	 * @param fornecedor
	 */
	public void save(Fornecedor fornecedor);

	/**
	 * Interface para o método getFornecedor
	 * 
	 * @param nome
	 * @return
	 */
	public Fornecedor getFornecedor(String nome);

	/**
	 * Interface para o método list
	 * 
	 * @return
	 */
	public List<Fornecedor> list();

	/**
	 * Interface para o método remove
	 * 
	 * @param fornecedor
	 */
	public void remove(Fornecedor fornecedor);

	/**
	 * Interface para o método update
	 * 
	 * @param fornecedor
	 */
	public void update(Fornecedor fornecedor);

}
