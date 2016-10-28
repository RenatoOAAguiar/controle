package br.com.estoque.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
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
	public List<Produto> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Produto> lista = session.createQuery("from Produto").list();
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
