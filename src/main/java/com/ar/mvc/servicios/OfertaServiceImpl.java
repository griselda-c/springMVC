package com.ar.mvc.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ar.mvc.dao.OfertaDao;
import com.ar.mvc.model.Oferta;

@Service
public class OfertaServiceImpl implements OfertaService{
	
	private OfertaDao ofertaDao;

	public void setOfertaDao(OfertaDao ofertaDao) {
		this.ofertaDao = ofertaDao;
	}

	@Override
	@Transactional
	public void addOferta(Oferta oferta) {
		ofertaDao.addOferta(oferta);
		
	}

	@Override
	@Transactional
	public void updateOferta(Oferta oferta) {
		ofertaDao.updateOferta(oferta);
		
	}

	@Override
	@Transactional
	public List<Oferta> listOfertas() {
		return ofertaDao.listOfertas();
	}

	@Override
	@Transactional
	public Oferta getOfertaById(int id) {
		return ofertaDao.getOfertaById(id);
	}

	@Override
	@Transactional
	public void removeOferta(String id) {
		ofertaDao.removeOferta(id);	
	}

}
