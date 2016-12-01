package br.com.estoque.negocio;

import java.util.List;

import br.com.estoque.model.Produto;

public interface InterfaceProduto {

	/**
	 * Interface para o método save
	 * 
	 * @param produto
	 */
	public void save(Produto produto);

	/**
	 * Interface para o método getProduto, retorna um objeto do tipo Produto
	 * 
	 * @param id
	 * @return {@Produto}
	 */
	public Produto getProduto(long id);

	/**
	 * Interface para o método list, retorna uma lista de objetos do tipo
	 * Produto
	 * 
	 * @param nome
	 * @param fornecedor
	 * @return {@List<Produto>}
	 */
	public List<Produto> list(String nome, String fornecedor);

	/**
	 * Interface para o método remove
	 * 
	 * @param produto
	 */
	public void remove(Produto produto);

	/**
	 * Interface para o método update
	 * 
	 * @param produto
	 */
	public void update(Produto produto);

}
