package com.ar.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ar.mvc.model.Persona;

@Controller
public class RegistrarController {
	
	@RequestMapping("/irRegistrar")
	public ModelAndView redireccionar(){
		ModelAndView mv = new ModelAndView("registro","command",new Persona());
		return mv;
	}
	
	@RequestMapping(value="/registrar", method= {RequestMethod.GET,RequestMethod.POST})
	public String registar(Persona persona, ModelMap model){
		model.addAttribute("nombre", persona.getNombre());
		model.addAttribute("apellido",persona.getApellido());
		return "saludo";	
	}
	
	
	@RequestMapping("/irFormulario")
	public ModelAndView irFormulario(){
		return new ModelAndView("formulario","command",new Persona());
	}
	
	//este el metodo action del formulario
	@RequestMapping(value="/agregar", method={RequestMethod.GET,RequestMethod.POST})
	public String agregar(Persona persona, ModelMap model){
		model.addAttribute("nombre", persona.getNombre());
		model.addAttribute("apellido", persona.getApellido());
		return "exito";
		
	}
	
	
}
