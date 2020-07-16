package com.alexis.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alexis.app.model.Horario;
import com.alexis.app.model.Pelicula;
import com.alexis.app.service.IHorariosService;
import com.alexis.app.service.IPeliculaService;

@Controller
@RequestMapping(value = "/horarios")
public class HorariosController {

	@Autowired
	private IPeliculaService servicePeliculas;
	
	@Autowired
	private IHorariosService serviceHorario;

	@GetMapping("/index")
	public String mostarHorarios(Model model) {
		model.addAttribute("horarios", serviceHorario.obtenerHorarios());
		return "horarios/listHorarios";
	}
	
	@GetMapping("/edit/{id}")
	public String editarHorarios(@PathVariable("id") int idHorario, Model model) {
		model.addAttribute("horario", serviceHorario.buscarHorarioPorId(idHorario));
		return "horarios/formHorario";
	}
	
	@GetMapping("/delete/{id}")
	public String editarHorarios(@PathVariable("id") int idHorario, RedirectAttributes attributes) {
		serviceHorario.eliminarHorarioPorId(idHorario);
		attributes.addFlashAttribute("msg", "Horario Eliminado!");
		return "redirect:/horarios/index";
	}
	
	@GetMapping(value = "/create")
	public String crear(@ModelAttribute Horario horario, Model model) {
		return "horarios/formHorario";
	}

	@PostMapping(value = "/save")
	public String guardar(@ModelAttribute Horario horario, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "horarios/formHorario";
		}
		serviceHorario.guardarHorario(horario);
		attributes.addFlashAttribute("msg", "Horario Guardado!");
		return "redirect:/horarios/index";
	}

	@InitBinder("horario")
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@ModelAttribute
	public void modelGenericos(Model model) {
		model.addAttribute("peliculas", servicePeliculas.obtenerPeliculasActivas("Activa"));
	}

}
