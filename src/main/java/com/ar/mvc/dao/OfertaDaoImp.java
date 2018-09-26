package com.ar.mvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.ar.mvc.model.Oferta;

@Repository
public class OfertaDaoImp implements OfertaDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	

	@Override
	public void addOferta(Oferta oferta) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(oferta);

	}

	@Override
	public void updateOferta(Oferta oferta) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			
			session.update(oferta);
		} catch (Exception e) {
			System.out.println(e);
		}
		

	}

	@Override
	public List<Oferta> listOfertas() {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Oferta> ofertaList = session.createQuery("FROM precioProducto").list();
		return ofertaList;
	}

	@Override
	public Oferta getOfertaById(int id) {
		Oferta oferta = null;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			
			oferta = (Oferta) session.get(Oferta.class, id);
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return oferta;
		
	}

	@Override
	public void removeOferta(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Oferta oferta = (Oferta) session.load(Oferta.class, id);
		if (null != oferta) {
			session.delete(oferta);
		}
	}
}
