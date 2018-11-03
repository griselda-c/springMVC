package com.ar.mvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ar.mvc.model.Cliente;
import com.ar.mvc.model.Producto;
import com.ar.mvc.servicios.OfertaService;
import com.ar.mvc.servicios.ProductoService;

@Controller
public class ProductoController {
	/*
	 * @RequestMapping("irHola") public ModelAndView redireccionar(){
	 * ModelAndView mv = new ModelAndView(); //hace referencia al archivo
	 * hola.jsp mv.setViewName("hola"); mv.addObject("mensaje",
	 * "Hola soy un mensaje desde un controller"); return mv; }
	 * 
	 */

	private ProductoService productoService;
	private OfertaService ofertaService;

	@Autowired(required = true)
	@Qualifier("productoService")
	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	@Autowired(required = true)
	@Qualifier("ofertaService")
	public void setOfertaService(OfertaService ofertaService) {
		this.ofertaService = ofertaService;
	}

	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, orderDateEditor);
	}

	public List<Producto> productos = new ArrayList<Producto>();

	@RequestMapping("irMenu")
	public ModelAndView irMenu() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("menu");
		return mv;
	}

	@RequestMapping("/irRegistrarProducto")
	public ModelAndView irFormulario() {
		/*
		 * ModelAndView object with the name "command" because the Spring
		 * framework expects an object with the name "command" if you are using
		 * <form:form> tags in your JSP file.
		 */
		ModelAndView model = new ModelAndView("formularioProducto", "producto", new Producto());
		return model;

	}

	@RequestMapping("/abrirModal")
	public ModelAndView abrirModal(@RequestParam("productoId") String productoID) {
		Producto producto = productoService.getProductoById(productoID);
		ModelAndView model = new ModelAndView("formularioEditProducto", "productoEdit", producto);
		model.addObject("mensaje", producto.getNombreProducto());
		return model;

	}
	/*
	@RequestMapping(value = "/productos")
	public @ResponseBody ModelAndView productos() {
		ModelAndView model = new ModelAndView("productos");
		return model;
	}*/

	/*
	 * @RequestMapping(value="/registrarProducto",
	 * method={RequestMethod.GET,RequestMethod.POST}) //@ResponseBody public
	 * String agregarProducto(Producto producto, ModelMap model){
	 * model.addAttribute("command", new Producto());
	 * //model.addAttribute("nombreProducto", producto.getNombreProducto());
	 * //model.addAttribute("stockMinimo", producto.getStockMinimo());
	 * //model.addAttribute("precio", producto.getOferta().getPrecio());
	 * //model.addAttribute("fechaInicio",
	 * producto.getOferta().getFechaInicio());
	 * //productoService.addProducto(producto); model.addAttribute("productos",
	 * productoService.listProductos());
	 * 
	 * //model.addAttribute("productos", productos);
	 * 
	 * return "formularioProducto" ; //return producto; }
	 */

	/*
	 * ResponseBody indica que lo que retorna no es una vista si no una
	 * respuesta que espera el cliente //this method response to POST request //
	 * receives json data sent by client --> map it to Producto object // return
	 * Producto object as json
	 */
	@RequestMapping(value = "/registrarProducto", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Producto agregarProducto(@ModelAttribute Producto producto) {
		productoService.addProducto(producto);
		return producto;
	}

	@RequestMapping(value = "/eliminarProducto", method = RequestMethod.POST)
	public @ResponseBody String eliminarProducto(String nombreProducto) {
		productoService.removeProducto(nombreProducto);
		return nombreProducto;
	}

	@RequestMapping(value = "/productos")
	public @ResponseBody ModelAndView listarProductos(ModelMap model) {
		List<Producto>productos = productoService.listProductos();
		model.put("listado", productos);
		model.put("totalResultados", productos.size());
		return new ModelAndView("productos", model);
	}
	
	@RequestMapping(value = "/listadoProductos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Producto> listadoProductos() {
		List<Producto> productos = productoService.listProductos();
		return productos;
	}

	/*
	 * @RequestMapping(value = "/editarProducto",
	 * method={RequestMethod.GET,RequestMethod.POST}) public ModelAndView
	 * editarProducto(@ModelAttribute("productoEdit") Producto productoEdit) {
	 * productoEdit.getOferta().setProducto(productoEdit);
	 * productoService.updateProducto(productoEdit); return irFormulario(); }
	 */

	@RequestMapping(value = "/editarProducto", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Producto editarProducto(@ModelAttribute("productoEdit")  Producto productoEdit) {
		productoEdit.getOferta().setProducto(productoEdit);
		productoService.updateProducto(productoEdit);
		return productoEdit;
	}

}