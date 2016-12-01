package br.com.estoque.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.estoque.model.Cargo;
import br.com.estoque.negocio.InterfaceCargo;
import br.com.estoque.utils.HibernateUtil;

public class CargoDAO implements InterfaceCargo {
	@Override
	public Cargo getCargo(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Cargo) session.load(Cargo.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cargo> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Cargo> lista = session.createQuery("from Cargo order by id").list();
		t.commit();
		return lista;
	}
}
