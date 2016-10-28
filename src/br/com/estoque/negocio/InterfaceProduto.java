package br.com.estoque.negocio;

import java.util.List;

import br.com.estoque.model.Produto;

public interface InterfaceProduto {

	public void save(Produto produto);

	public Produto getProduto(long id);

	public List<Produto> list();

	public void remove(Produto produto);

	public void update(Produto produto);

}
