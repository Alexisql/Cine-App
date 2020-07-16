package com.alexis.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alexis.app.model.Detalle;
import com.alexis.app.model.Pelicula;
import com.alexis.app.service.IDetalleService;
import com.alexis.app.service.IPeliculaService;
import com.alexis.app.util.Utileria;

@Controller
@RequestMapping(value = "/peliculas")
public class PeliculaController {

	@Autowired
	private IPeliculaService peliculaService;

	@Autowired
	private IDetalleService detalleService;

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("peliculas", peliculaService.obtenerPeliculas());
		return "peliculas/listPeliculas";
	}

	@GetMapping("/create")
	public String crearPelicula(@ModelAttribute Pelicula pelicula, Model model) {
		return "peliculas/formPelicula";
	}

	@PostMapping("/save")
	public String guardarPelicula(Pelicula pelicula, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "peliculas/formPelicula";
		}

		if (!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart, request);
			if (nombreImagen != null) {
				pelicula.setImagen(nombreImagen);
			}
		}
		detalleService.guardarDetalle(pelicula.getDetalle());
		peliculaService.guardarPelicula(pelicula);
		attributes.addFlashAttribute("msg", "Registro Guardado");
		return "redirect:/peliculas/index";
	}

	@GetMapping("/edit/{id}")
	public String editarPelicula(@PathVariable("id") int idPelicula, Model model) {
		model.addAttribute("pelicula", peliculaService.buscarPeliculaPorId(idPelicula));
		return "peliculas/formPelicula";
	}
	
	@GetMapping("eliminar/{id}")
	public String eliminarPelicula(@PathVariable("id") int idPelicula, RedirectAttributes attributes) {
		Pelicula pelicula = peliculaService.buscarPeliculaPorId(idPelicula);
		peliculaService.eliminarPelicula(idPelicula);
		detalleService.eliminarDetalle(pelicula.getDetalle().getId());
		attributes.addFlashAttribute("msg", "Pelicula Eliminada!");
		return "redirect:/peliculas/index";
	}
	
	@ModelAttribute("generos")
	public List<String> listaGeneros(){
		return peliculaService.obtenerGeneros();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
