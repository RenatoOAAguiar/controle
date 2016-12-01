package br.com.estoque.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.com.estoque.model.Cargo;
import br.com.estoque.model.Funcionario;
import br.com.estoque.model.Permissao;

public class TesteConexao {

	@Test
	public void testCadastro() {
		Funcionario cadastro = criarCad();
		Assert.assertNotNull(cadastro);
		Assert.assertNotNull(cadastro.getId());
		Assert.assertNotNull(cadastro.getNome());
		Assert.assertNotNull(cadastro.getCep());
		Assert.assertNotNull(cadastro.getCpf());
		Assert.assertNotNull(cadastro.getLogradouro());
		Assert.assertNotNull(cadastro.getComplemento());
		Assert.assertNotNull(cadastro.getSobrenome());
		Assert.assertNotNull(cadastro.getDataNasc());		
		Assert.assertNotNull(cadastro.getSenha());
		Assert.assertNotNull(cadastro.getSetor());
		Assert.assertNotNull(cadastro.getCargo());
		Assert.assertNotNull(cadastro.getDataContratacao());
		Assert.assertNotNull(cadastro.getLogin());
		Assert.assertNotNull(cadastro.getNumero());
		Assert.assertNotNull(cadastro.getPermissao());
	}

	public Funcionario criarCad() {
		return criarCadastro(1L,"Maria","75380000","11111111111","Rua A","CASA 1",new Date(),
				"senha","Setor Central",1,new Date(),"login",255,1,"sobrenome");
	}

	public Funcionario criarCadastro(long id, String nome, String cep, String cpf, 
			String logradouro, String complemento, Date dataNasc, String senha, String setor, 
			int cargoId, Date datacontratacao, String login, int numero, int permissaoId,String sobrenome) {
		Funcionario cadastro = new Funcionario();
		cadastro.setId(id);
		cadastro.setNome(nome);
		Cargo cargo = new Cargo();
		cargo.setId(cargoId);
		cadastro.setCargo(cargo);
		cadastro.setCep(cep);
		cadastro.setComplemento(complemento);
		cadastro.setCpf(cpf);
		cadastro.setDataNasc(dataNasc);
		cadastro.setLogin(login);
		cadastro.setSenha(senha);
		cadastro.setNumero(numero);
		Permissao permissao = new Permissao();
		permissao.setId(permissaoId);
		cadastro.setPermissao(permissao);
		cadastro.setDataNasc(dataNasc);
		cadastro.setDataContratacao(datacontratacao);
		cadastro.setSetor(setor);
		cadastro.setLogradouro(logradouro);
		cadastro.setSobrenome(sobrenome);
		return cadastro;
	}

}
