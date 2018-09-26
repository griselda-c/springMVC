package com.ar.mvc.dao;

import java.util.List;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ar.mvc.model.Cliente;

@Repository
public class ClienteDaoImpl implements ClienteDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addCliente(Cliente c) {
		Session session = this.sessionFactory.getCurrentSession();
		//session.persist(c); //persist necesita que el clientecontacto sea parte de la session. como no esta salta error
		session.save(c);

	}

	@Override
	public void updateCliente(Cliente c) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.update(c);
		} catch (Exception e) {
			System.out.println("ERROR UPDATE " +e);
		}
		
	}

	@Override
	public List<Cliente> listClientes() {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Cliente> listClientes = session.createQuery("From Cliente").list();
			return listClientes;
		}catch (Exception e) {
			System.out.println("ERROR " +e);
			return null;
		}
		
	}

	@Override
	public Cliente getClienteById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Cliente cliente = (Cliente) session.get(Cliente.class, id);
		return cliente;
	}

	@Override
	public void removeCliente(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Cliente cliente = (Cliente) session.load(Cliente.class, id);
		if (null != cliente) {
			session.delete(cliente);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> search(String clienteName) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM Cliente WHERE nomCli like :nombre");
			query.setParameter("nombre", "%"+clienteName+"%");
			System.out.println("SEARCH DESPUES DE LA CONSULTA***************");
			return query.list();
		} catch (Exception e) {
			System.out.println("ERROR " +e);
			return null;
		}
	}

	@Override
	public Cliente getClienteByName(String nombre) {
		try {
			System.out.println("NOMBRE: " +nombre);
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM Cliente WHERE nomCli =:nombre");
			query.setParameter("nombre",nombre);
			return (Cliente) query.list().get(0);
		} catch (Exception e) {
			System.out.println("ERROR BY NAME  " +e);
			return null;
		}
	}

	@Override
	public List<Cliente> getClienteByPage(int pagina, int cantidad) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Cliente");
		query.setFirstResult((pagina-1) * cantidad);
		query.setMaxResults(cantidad);
		return query.list();
	}

}
