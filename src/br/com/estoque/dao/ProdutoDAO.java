package br.com.estoque.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import br.com.estoque.negocio.InterfaceProduto;
import br.com.estoque.utils.HibernateUtil;

public class ProdutoDAO implements InterfaceProduto {

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
		Transaction t = session.beginTransaction();
		Query query;
		query = session.createQuery("from Produto where id = :id");
		query.setParameter("id", id);

		Produto produto = (Produto) query.uniqueResult();
		t.commit();
		return produto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> list(String nome, String fornecedor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query;
		FornecedorDAO f = new FornecedorDAO();
		Fornecedor forn = new Fornecedor();
		long id = 0L;
		if (nome.equals("") && fornecedor.equals("")) {
			query = session.createQuery("from Produto");
		} else if (!nome.equals("") && fornecedor.equals("")) {
			query = session.createQuery("from Produto where lower(nome) like lower(concat('%', :nome, '%'))");
			query.setParameter("nome", nome);

		} else if (nome.equals("") && !fornecedor.equals("")) {
			forn = f.getFornecedor(fornecedor);
			id = forn != null ? forn.getId() : 0L;
			query = session.createQuery("from Produto where fornecedor_id = :fornecedor ");
			query.setParameter("fornecedor", id);
		} else {
			forn = f.getFornecedor(fornecedor);
			id = forn != null ? forn.getId() : 0L;
			query = session.createQuery(
					"from Produto where lower(nome) like lower(concat('%', :nome, '%')) and fornecedor_id = :fornecedor ");
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
		Produto p = (Produto) session.createCriteria(Produto.class)
				.add(Restrictions.eq("id", produto.getId())).uniqueResult();
		session.delete(p);
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
