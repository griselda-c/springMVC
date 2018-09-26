package com.ar.mvc.dao;

import java.util.List;

import com.ar.mvc.model.Cliente;

public interface ClienteDao {
	public void addCliente(Cliente c);

	public void updateCliente(Cliente c);

	public List<Cliente> listClientes();

	public Cliente getClienteById(int id);
	
	public Cliente getClienteByName(String nombre);

	public void removeCliente(int id);
	
	public List<Cliente> search(String clienteName);
	
	public List<Cliente> getClienteByPage(int pagina, int cantidad);

}
