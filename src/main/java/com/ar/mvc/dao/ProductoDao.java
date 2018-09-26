package com.ar.mvc.dao;

import java.util.List;

import com.ar.mvc.model.Producto;

public interface ProductoDao {
	
	public void addProducto(Producto p);
	public void updateProducto(Producto p);
	public List<Producto> listProductos();
	public Producto getProductoById(String id);
	public void removeProducto(String id);

}
