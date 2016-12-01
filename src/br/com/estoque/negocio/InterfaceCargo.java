package br.com.estoque.negocio;

import java.util.List;

import br.com.estoque.model.Cargo;

public interface InterfaceCargo {

	/**
	 * Interface para o método getCargo, tem como parâmetro id, retornando um objeto do tipo {@Cargo}
	 * 
	 * @param id
	 * @return
	 */
	public Cargo getCargo(long id);

	/**
	 * Interface para o método list, que retornará um List do tipo {@Cargo}
	 * 
	 * @return
	 */
	public List<Cargo> list();

}
