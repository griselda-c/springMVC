package com.ar.mvc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ar.mvc.model.Cliente;
import com.ar.mvc.model.Producto;
import com.ar.mvc.servicios.ClienteService;

@Controller
public class ClienteController {
	private ClienteService clienteService;

	@Autowired(required = true)
	@Qualifier("clienteService")
	public void setClienteService(ClienteService clienteService) {
		try {
			this.clienteService = clienteService;
		} catch (Exception e) {
			System.out.println("ERROR " + e);
		}

	}

	@RequestMapping("agregarCliente")
	public ModelAndView irCliente() {
		ModelAndView model = new ModelAndView("clienteFormulario", "cliente", new Cliente());
		model.addObject("titulo", "Agregar Cliente");
		return model;
	}

	@RequestMapping(value = "/buscarCliente", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Cliente> agregarCliente(String nomCli) {
		List<Cliente> clientes = clienteService.search(nomCli);
		return clientes;

	}

	@RequestMapping(value = "/clientes")
	public @ResponseBody ModelAndView clientes() {
		ModelAndView model = new ModelAndView("clientesTable");
		model.addObject("clientes", clienteService.listClientes());
		return model;
	}
	
	@RequestMapping(value = "/registrarCliente", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody void registrarCliente(@ModelAttribute Cliente cliente) {
		Cliente contacto = clienteService.getClienteByName(cliente.getCodCliContacto().getNomCli());
		cliente.setCodCliContacto(contacto);
		clienteService.addCliente(cliente);
		clientes();
	}


	/*
	 * @RequestMapping(value="/editarCliente") public String
	 * editarCliente(@RequestParam("clienteId") int clienteId, ModelMap model){
	 * Cliente cliente = clienteService.getClienteById(clienteId); ModelAndView
	 * model = new ModelAndView("formCliente", "cliente", cliente);
	 * model.addObject("titulo", "Editar Cliente");
	 * model.addAttribute("cliente", cliente); return "formCliente"; }
	 */
	
	@RequestMapping(value = "/serviceEditarCliente", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Cliente ServiceEditarCliente(@ModelAttribute("clienteEdit")  Cliente clienteEdit) {
		if(clienteEdit.getCodCliContacto().getNomCli() != null){
			Cliente contacto = clienteService.getClienteByName(clienteEdit.getCodCliContacto().getNomCli());
			clienteEdit.setCodCliContacto(contacto);
		}else
			clienteEdit.setCodCliContacto(null);
		

		clienteService.updateCliente(clienteEdit);
		return clienteEdit;
	}

	@RequestMapping("/editarCliente")
	public ModelAndView editarCliente(@RequestParam("clienteId") int clienteId) {
	    Cliente cliente = clienteService.getClienteById(clienteId);
		ModelAndView model = new ModelAndView("formEditCliente", "clienteEdit", cliente);
		model.addObject("mensaje", cliente.getNomCli());
		return model;
	}
/*
	@RequestMapping(value = "/paginacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cliente> paginacion(Integer cantidad, ModelMap model) {
		List<Cliente> clientes = clienteService.getClienteByPage(1, cantidad);
		if (clientes.isEmpty() || clientes == null) {
			System.out.println("clientes vacia");
		} else {
			for (Cliente cliente : clientes) {
				if (cliente.getCodCliContacto() == null) {
					System.out.println("NULL");
				} else {
					System.out.println(cliente.getCodCliContacto().getId());
				}

			}
		}

		model.addAttribute("clientes", clientes);

		return clientes;
	}
*/
	@RequestMapping(value = "/listado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cliente> listadoClientes() {
		List<Cliente> clientes = clienteService.listClientes();
		return clientes;
	}
	
	@RequestMapping(value="/eliminarCliente", method=RequestMethod.POST)
	public @ResponseBody void eliminarCliente(String clienteId){		
		clienteService.removeCliente(Integer.parseInt(clienteId));
		clientes();
	}

	@RequestMapping(value = "exportarClientes")
	public void exportarClientes() throws IOException {

		List<Cliente> clientes = clienteService.listClientes();

		String rutaArchivo = System.getProperty("user.home") + "/ejemploExcelJava.xls";

		// creamos un objeto de tipo File para que contenga el archivo...
		File archivoXLS = new File(rutaArchivo);

		// verificamos si existe dentro del sistema
		// y de ser así lo eliminamos para crear una nueva copia de trabajo
		if (archivoXLS.exists())
			archivoXLS.delete();
		archivoXLS.createNewFile();


		// Creamos el libro de trabajo de Excel formato OOXML
		Workbook workbook = new HSSFWorkbook();

		// La hoja donde pondremos los datos
		Sheet pagina = workbook.createSheet("Listado de clientes");

		String[] titulos = { "ID", "nombre cliente", "localidad", "compra máxima", "cliente contacto" };

		// Creamos el estilo paga las celdas del encabezado
		CellStyle style = workbook.createCellStyle();
		// Indicamos que tendra un fondo azul aqua
		// con patron solido del color indicado
		style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// Creamos una fila en la hoja en la posicion 0 para el encabezado
		Row fila = pagina.createRow(0);

		// Creamos el encabezado
		for (int i = 0; i < titulos.length; i++) {
			// Creamos una celda en esa fila, en la posicion
			// indicada por el contador del ciclo
			Cell celda = fila.createCell(i);

			// Indicamos el estilo que deseamos
			// usar en la celda, en este caso el unico
			// que hemos creado
			celda.setCellStyle(style);
			celda.setCellValue(titulos[i]);
		}


		// creamos una fila para cada cliente

		int indice = 1;
		fila = pagina.createRow(indice);
		for (Cliente cliente : clientes) {
			fila = pagina.createRow(indice);
			indice++;
			Cell celda = fila.createCell(0);
			celda.setCellValue(cliente.getId());
			celda = fila.createCell(1);
			celda.setCellValue(cliente.getNomCli());
			celda = fila.createCell(2);
			celda.setCellValue(cliente.getLocalidad());
			celda = fila.createCell(3);
			celda.setCellValue(cliente.getCompraMaxima());
			celda = fila.createCell(4);
			if(cliente.getCodCliContacto() != null)
				celda.setCellValue(cliente.getCodCliContacto().getId());
			else
				celda.setCellValue("no tiene");
		}

		// Ahora guardaremos el archivo
		try {
			// Creamos el flujo de salida de datos,
			// apuntando al archivo donde queremos
			// almacenar el libro de Excel
			FileOutputStream salida = new FileOutputStream(archivoXLS);

			// Almacenamos el libro de
			// Excel via ese
			// flujo de datos
			workbook.write(salida);

			// Cerramos el libro para concluir operaciones
			workbook.close();

			// LOGGER.log(Level.INFO, "Archivo creado existosamente en {0}",
			// archivoXLS.getAbsolutePath());

		} catch (FileNotFoundException ex) {
			// Logger.getLogger(Level.ERROR, "Archivo no localizable en sistema
			// de archivos");
			System.out.println("Archivo no localizable en sistema");
		} catch (IOException ex) {
			// org.jboss.logging.Logger.getLogger(name, suffix)(Level.SEVERE,
			// "Error de entrada/salida");
			System.out.println("Error de entrada/salida");
		}
	}

}
