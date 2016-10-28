package br.com.estoque.negocio;

import java.util.List;

import br.com.estoque.model.Cargo;

public interface InterfaceCargo {

	public Cargo getCargo(long id);

	public List<Cargo> list();

}
