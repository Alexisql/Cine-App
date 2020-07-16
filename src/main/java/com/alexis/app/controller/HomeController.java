package com.alexis.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.management.ServiceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alexis.app.model.Pelicula;
import com.alexis.app.service.IBannerService;
import com.alexis.app.service.IHorariosService;
import com.alexis.app.service.INoticiaService;
import com.alexis.app.service.IPeliculaService;
import com.alexis.app.util.Utileria;

@Controller
public class HomeController {

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@Autowired
	private IPeliculaService servicePeliculas;

	@Autowired
	private IBannerService serviceBanners;

	@Autowired
	private IHorariosService serviceHorarios;

	@Autowired
	private INoticiaService serviceNoticia;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mostrarIndex(Model model) {
		String fecha = dateFormat.format(new Date());
		model.addAttribute("fechaBusqueda", fecha);
		try {
			model.addAttribute("peliculas",
					servicePeliculas.obtenerPeliculasActivasAndFecha("Activa", dateFormat.parse(fecha)));
		} catch (ParseException e) {
			System.out.println("Error al castear la fecha!");
		}
		return "home";
	}

	@RequestMapping(value = "/detalle", method = RequestMethod.GET)
	public String mostrarDetalle(@RequestParam("idMovie") int idPelicula, @RequestParam("fecha") Date fecha,
			Model model) {
		model.addAttribute("pelicula", servicePeliculas.buscarPeliculaPorId(idPelicula));
		model.addAttribute("horarios", serviceHorarios.buscarPorIdPelicula(idPelicula, fecha));
		model.addAttribute("fechaBusqueda", dateFormat.format(fecha));
		return "detalle";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String mostrarDetalle(@RequestParam("fecha") String fecha, Model model) {
		model.addAttribute("fechaBusqueda", fecha);
		try {
			model.addAttribute("peliculas",
					servicePeliculas.obtenerPeliculasActivasAndFecha("Activa", dateFormat.parse(fecha)));
		} catch (ParseException e) {
			System.out.println("Error al castear la fecha!");
		}
		return "home";
	}

	@GetMapping("/acerca")
	public String mostrarAcerca() {
		return "acerca";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@ModelAttribute
	private void obtenerModelGenericos(Model model) {
		model.addAttribute("noticias", serviceNoticia.obtenerUltimasTresNoticias("Activa"));
		model.addAttribute("banners", serviceBanners.obtenerBannersActivos("Activo"));
		model.addAttribute("fechas", Utileria.getNextDays(4));
	}

}
