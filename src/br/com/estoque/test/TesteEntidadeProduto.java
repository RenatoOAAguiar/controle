package br.com.estoque.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.com.estoque.model.Produto;

public class TesteEntidadeProduto {

	@Test
	public void testCadastro() {
		Produto cadastro = criarCad();
		Assert.assertNotNull(cadastro);
		Assert.assertNotNull(cadastro.getId());
		Assert.assertNotNull(cadastro.getNome());
		Assert.assertNotNull(cadastro.getQuantidade());
		Assert.assertNotNull(cadastro.getDataAlteracao());
		Assert.assertNotNull(cadastro.getValor());
	}

	public Produto criarCad() {
		return criarCadastro(2L,"João",10,new Date(),255.1);
	}

	public Produto criarCadastro(long id, String nome, int quantidade,Date data, double valor ) {
		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome(nome);
		produto.setDataAlteracao(data);
		produto.setQuantidade(quantidade);
		produto.setValor(valor);
		return produto;
	}

}
