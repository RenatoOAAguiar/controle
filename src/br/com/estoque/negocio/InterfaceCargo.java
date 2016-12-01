package br.com.estoque.negocio;

import java.util.List;

import br.com.estoque.model.Cargo;

public interface InterfaceCargo {

	/**
	 * Interface para o m�todo getCargo, tem como par�metro id, retornando um objeto do tipo {@Cargo}
	 * 
	 * @param id
	 * @return
	 */
	public Cargo getCargo(long id);

	/**
	 * Interface para o m�todo list, que retornar� um List do tipo {@Cargo}
	 * 
	 * @return
	 */
	public List<Cargo> list();

}
