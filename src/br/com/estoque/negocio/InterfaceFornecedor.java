package br.com.estoque.negocio;

import java.util.List;

import br.com.estoque.model.Fornecedor;

public interface InterfaceFornecedor {

	public void save(Fornecedor fornecedor);

	public Fornecedor getFornecedor(long id);

	public List<Fornecedor> list();

	public void remove(Fornecedor fornecedor);

	public void update(Fornecedor fornecedor);

}
