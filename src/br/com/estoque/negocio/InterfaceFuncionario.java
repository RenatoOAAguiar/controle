package br.com.estoque.negocio;

import java.util.List;

import br.com.estoque.model.Funcionario;

public interface InterfaceFuncionario {

	public void save(Funcionario funcionario);

	public Funcionario getFuncionario(long id);

	public List<Funcionario> list(String cpf, String nome);

	public void remove(Funcionario funcionario);

	public void update(Funcionario funcionario);

}
