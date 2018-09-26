package com.ar.mvc.servicios;

import java.util.List;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.mvc.dao.ClienteDao;
import com.ar.mvc.model.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	private ClienteDao clienteDao;
	
	
	public void setClienteDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}

	@Override
	@Transactional
	public void addCliente(Cliente c) {
		clienteDao.addCliente(c);

	}

	@Override
	@Transactional
	public void updateCliente(Cliente c) {
		clienteDao.updateCliente(c);

	}

	@Override
	@Transactional
	public List<Cliente> listClientes() {
		return clienteDao.listClientes();
	}

	@Override
	@Transactional
	public Cliente getClienteById(int id) {
		return clienteDao.getClienteById(id);
	}

	@Override
	@Transactional
	public void removeCliente(int id) {
		clienteDao.removeCliente(id);

	}

	@Override
	@Transactional
	public List<Cliente> search(String clienteName) {
		return clienteDao.search(clienteName);
	}

	@Override
	@Transactional
	public Cliente getClienteByName(String nombre) {
		return clienteDao.getClienteByName(nombre);
	}

	@Override
	@Transactional
	public List<Cliente> getClienteByPage(int pagina, int cantidad) {
		return clienteDao.getClienteByPage(pagina, cantidad);
	}

}
