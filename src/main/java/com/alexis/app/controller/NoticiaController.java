package com.alexis.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alexis.app.model.Noticia;
import com.alexis.app.service.INoticiaService;

@Controller
@RequestMapping(value = "/noticias")
public class NoticiaController {

	@Autowired
	private INoticiaService noticiaService;

	@GetMapping("/index")
	public String mostrarListaNoticias() {
		return "noticias/listNoticias";
	}

	@GetMapping("/create")
	public String crearNoticia(@ModelAttribute Noticia noticia) {
		return "noticias/formNoticia";
	}
	
	@GetMapping("/edit/{id}")
	public String editarHorarios(@PathVariable("id") int idNoticia, Model model) {
		model.addAttribute("noticia", noticiaService.buscarNoticiaPorId(idNoticia));
		return "noticias/formNoticia";
	}
	
	@GetMapping("/delete/{id}")
	public String editarHorarios(@PathVariable("id") int idNoticia, RedirectAttributes attributes) {
		noticiaService.eliminarNoticiaPorId(idNoticia);
		attributes.addFlashAttribute("msg", "Noticia Eliminada!");
		return "redirect:/noticias/index";
	}

	@PostMapping("/save")
	public String guardarNoticia(@ModelAttribute Noticia noticia, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "noticias/formNoticia";
		}
		noticiaService.guardarNoticia(noticia);
		attributes.addFlashAttribute("msg", "Noticia Registrada!");
		return "redirect:/noticias/index";
	}

	@ModelAttribute("noticias")
	public List<Noticia> modelGenerico() {
		return noticiaService.obtenerNoticias();
	}

}
