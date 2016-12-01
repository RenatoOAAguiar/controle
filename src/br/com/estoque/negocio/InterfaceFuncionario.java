package br.com.estoque.negocio;

import java.util.List;

import br.com.estoque.model.Funcionario;

public interface InterfaceFuncionario {

	/**
	 * Interface para o m�todo save
	 * 
	 * @param funcionario
	 */
	public void save(Funcionario funcionario);

	/**
	 * Interface para o m�todo getFuncionario, retorna objeto funcionario
	 * 
	 * @param cpf
	 * @return {@Funcionario}
	 */
	public Funcionario getFuncionario(String cpf);

	/**
	 * Interface para o m�todo login, retorna objeto funcionario
	 * 
	 * @param login
	 * @param senha
	 * @return {@Funcionario}
	 */
	public Funcionario login(String login, String senha);

	/**
	 * Interface para o m�todo list, retorna uma lista de objetos funcionario
	 * 
	 * @param cpf
	 * @param nome
	 * @return {@List<Funcionario>}
	 */
	public List<Funcionario> list(String cpf, String nome);

	/**
	 * Interface para o m�todo remove
	 * 
	 * @param funcionario
	 */
	public void remove(Funcionario funcionario);

	/**
	 * Interface para o m�todo update
	 * 
	 * @param funcionario
	 */
	public void update(Funcionario funcionario);

}
