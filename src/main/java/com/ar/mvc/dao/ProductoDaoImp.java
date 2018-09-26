package com.ar.mvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.ar.mvc.model.Producto;

@Repository
public class ProductoDaoImp implements ProductoDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addProducto(Producto p) {
		Session session = this.sessionFactory.getCurrentSession();
		p.getOferta().setProducto(p);
		session.persist(p);

	}

	@Override
	public void updateProducto(Producto p) {
		Session session = this.sessionFactory.getCurrentSession();
		System.out.println("ENTRE AL UPDATEpRODUCTO "+p.getOferta().getFechaFin());
		try {
			p.getOferta().setProducto(p);
			session.update(p);
		} catch (Exception e) {
			System.out.println("ERROR "+e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> listProductos() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Producto> productosList = session.createQuery("FROM Producto").list();
		return productosList;
	}

	@Override
	public Producto getProductoById(String id) {
		Session session = this.sessionFactory.getCurrentSession();		
		//Producto p = (Producto) session.load(Producto.class, id);
		Producto producto = (Producto) session.get(Producto.class, id);
		return producto;
	}

	@Override
	public void removeProducto(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Producto p = (Producto) session.load(Producto.class, id);
		if(null != p){
			session.delete(p);
		}

	}
	
	

}
