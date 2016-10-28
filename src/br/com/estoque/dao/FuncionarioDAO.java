package br.com.estoque.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.estoque.model.Funcionario;
import br.com.estoque.negocio.InterfaceFuncionario;
import br.com.estoque.utils.HibernateUtil;

public class FuncionarioDAO implements InterfaceFuncionario{
	
	@Override
	public void save(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(funcionario);
		t.commit();
		
	}

	@Override
	public Funcionario getFuncionario(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Funcionario) session.load(Funcionario.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Funcionario> lista = session.createQuery("from Funcionario").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(funcionario);
		t.commit();
		
	}

	@Override
	public void update(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(funcionario);
		t.commit();
	}

}
