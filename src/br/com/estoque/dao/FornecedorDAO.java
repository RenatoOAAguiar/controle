package br.com.estoque.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.estoque.model.Fornecedor;
import br.com.estoque.negocio.InterfaceFornecedor;
import br.com.estoque.utils.HibernateUtil;

public class FornecedorDAO implements InterfaceFornecedor{

	@Override
	public void save(Fornecedor fornecedor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(fornecedor);
		t.commit();
		
	}

	@Override
	public Fornecedor getFornecedor(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Fornecedor) session.load(Fornecedor.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fornecedor> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Fornecedor> lista = session.createQuery("from Fornecedor").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(Fornecedor fornecedor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(fornecedor);
		t.commit();
		
	}

	@Override
	public void update(Fornecedor fornecedor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(fornecedor);
		t.commit();
	}

}
