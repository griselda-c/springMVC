package com.ar.mvc.dao;

import java.util.List;

import com.ar.mvc.model.Oferta;



public interface OfertaDao {

	public void addOferta(Oferta oferta);
	public void updateOferta(Oferta oferta);
	public List<Oferta> listOfertas();
	public Oferta getOfertaById(int id);
	public void removeOferta(String id);
}
