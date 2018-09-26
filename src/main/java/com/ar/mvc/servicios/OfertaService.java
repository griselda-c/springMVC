package com.ar.mvc.servicios;

import java.util.List;

import com.ar.mvc.model.Oferta;

public interface OfertaService {
	
	public void addOferta(Oferta oferta);
	public void updateOferta(Oferta oferta);
	public List<Oferta> listOfertas();
	public Oferta getOfertaById(int id);
	public void removeOferta(String id);

}
