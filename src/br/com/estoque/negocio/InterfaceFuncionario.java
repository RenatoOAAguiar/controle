package br.com.estoque.negocio;

import java.util.List;

import br.com.estoque.model.Funcionario;

public interface InterfaceFuncionario {

	/**
	 * Interface para o método save
	 * 
	 * @param funcionario
	 */
	public void save(Funcionario funcionario);

	/**
	 * Interface para o método getFuncionario, retorna objeto funcionario
	 * 
	 * @param cpf
	 * @return {@Funcionario}
	 */
	public Funcionario getFuncionario(String cpf);

	/**
	 * Interface para o método login, retorna objeto funcionario
	 * 
	 * @param login
	 * @param senha
	 * @return {@Funcionario}
	 */
	public Funcionario login(String login, String senha);

	/**
	 * Interface para o método list, retorna uma lista de objetos funcionario
	 * 
	 * @param cpf
	 * @param nome
	 * @return {@List<Funcionario>}
	 */
	public List<Funcionario> list(String cpf, String nome);

	/**
	 * Interface para o método remove
	 * 
	 * @param funcionario
	 */
	public void remove(Funcionario funcionario);

	/**
	 * Interface para o método update
	 * 
	 * @param funcionario
	 */
	public void update(Funcionario funcionario);

}
