package com.ar.mvc.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.mvc.dao.ProductoDao;
import com.ar.mvc.model.Producto;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	private ProductoDao productoDao;

	public void setProductoDao(ProductoDao productoDao) {
		this.productoDao = productoDao;
	}
	

	@Override
	@Transactional
	public void addProducto(Producto p) {
		productoDao.addProducto(p);

	}

	@Override
	@Transactional
	public void updateProducto(Producto p) {
		productoDao.updateProducto(p);
	}

	@Override
	@Transactional
	public List<Producto> listProductos() {
		return productoDao.listProductos();
	}

	@Override
	@Transactional
	public Producto getProductoById(String id) {
		return productoDao.getProductoById(id);
	}

	@Override
	@Transactional
	public void removeProducto(String id) {
		productoDao.removeProducto(id);
	}

}
