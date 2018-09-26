package com.ar.mvc.servicios;

import java.util.List;

import com.ar.mvc.model.Cliente;

public interface ClienteService {
	public void addCliente(Cliente c);

	public void updateCliente(Cliente c);

	public List<Cliente> listClientes();

	public Cliente getClienteById(int clienteId);
	
	public Cliente getClienteByName(String nombre);

	public void removeCliente(int id);
	
	public List<Cliente> search(String clienteName);
	
	public List<Cliente> getClienteByPage(int pagina, int cantidad);

}
