package br.com.estoque.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.estoque.model.Funcionario;
import br.com.estoque.negocio.InterfaceFuncionario;
import br.com.estoque.utils.HibernateUtil;

public class FuncionarioDAO implements InterfaceFuncionario {

	@Override
	public void save(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(funcionario);
		t.commit();

	}

	@Override
	public Funcionario getFuncionario(String cpf) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query;
		query = session.createQuery("from Funcionario where cpf = :cpf");
		query.setParameter("cpf", cpf);

		Funcionario funcionario = (Funcionario) query.uniqueResult();
		t.commit();
		return funcionario;
	}

	@Override
	public Funcionario login(String login, String senha) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query;
		query = session.createQuery("from Funcionario where login = :login and senha = :senha ");
		query.setParameter("login", login);
		query.setParameter("senha", senha);

		Funcionario funcionario = (Funcionario) query.uniqueResult();
		t.commit();
		return funcionario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionario> list(String cpf, String nome) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query;
		if (cpf.equals("") && nome.equals("")) {
			query = session.createQuery("from Funcionario");
		} else if (!cpf.equals("") && nome.equals("")) {
			query = session.createQuery("from Funcionario where cpf = :cpf");
			query.setParameter("cpf", cpf);
		} else if (cpf.equals("") && !nome.equals("")) {
			query = session.createQuery("from Funcionario where lower(nome) like lower(concat('%', :nome, '%'))");
			query.setParameter("nome", nome);
		} else {
			query = session.createQuery("from Funcionario where cpf = :cpf and lower(nome) like lower(concat('%', :nome, '%'))");
			query.setParameter("cpf", cpf);
			query.setParameter("nome", nome);
		}
		List<Funcionario> lista = query.list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(getFuncionario(funcionario.getCpf()));
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
