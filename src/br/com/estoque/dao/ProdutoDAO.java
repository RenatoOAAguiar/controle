package br.com.estoque.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import br.com.estoque.negocio.InterfaceProduto;
import br.com.estoque.utils.HibernateUtil;

public class ProdutoDAO implements InterfaceProduto{

	@Override
	public void save(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(produto);
		t.commit();
		
	}

	@Override
	public Produto getProduto(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Produto) session.load(Produto.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> list(String nome, String fornecedor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query;
		if(nome.equals("") && fornecedor.equals("")){
			query = session.createQuery("from Produto");
		}
		else{
			long id = 0L;
			if(!fornecedor.equals("")){
				FornecedorDAO f = new FornecedorDAO();
				Fornecedor forn = new Fornecedor();
				forn = f.getFornecedor(fornecedor);
				id = forn != null? forn.getId(): 0L;
			}
			query = session.createQuery("from Produto where nome = :nome or fornecedor_id = :fornecedor ");
			query.setParameter("nome", nome);
			query.setParameter("fornecedor", id);
		}
		List<Produto> lista = query.list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(produto);
		t.commit();
		
	}

	@Override
	public void update(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(produto);
		t.commit();
	}

}
