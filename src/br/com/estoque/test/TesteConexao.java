package br.com.estoque.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import br.com.estoque.utils.HibernateUtil;

public class TesteConexao {

	private SessionFactory sessionFactory;

	private Session session;

	@Test
	public void teste() {
		sessionFactory = HibernateUtil.getSessionFactory();

		session = sessionFactory.openSession();

		session.close();

	}

}
