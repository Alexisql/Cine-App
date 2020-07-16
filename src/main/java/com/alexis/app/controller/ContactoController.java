package com.alexis.app.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alexis.app.model.Contacto;
import com.alexis.app.service.IPeliculaService;


@Controller
public class ContactoController { 
	
	@Autowired
	private IPeliculaService servicePeliculas;

	@GetMapping("/contacto")
	public String mostrarFormulario(@ModelAttribute("instanciaContacto") Contacto contacto, Model model) {
		model.addAttribute("generos", servicePeliculas.obtenerGeneros());
		model.addAttribute("tipos", tipoNotificaciones());
		return "formContacto";
	}
	
	@PostMapping("/contacto")
	public String guardar(@ModelAttribute("instanciaContacto") Contacto contacto, RedirectAttributes attributes) {
		attributes.addFlashAttribute("mensaje", "Gracias por tu participacion");
		System.out.println(contacto);
		return "redirect:/contacto";
	}
	
	private List<String> tipoNotificaciones(){
		List<String> tipos = new LinkedList<>();
		tipos.add("Estrenos");
		tipos.add("Promociones");
		tipos.add("Noticias");
		tipos.add("Preventas");
		return tipos;
	}
	
}
