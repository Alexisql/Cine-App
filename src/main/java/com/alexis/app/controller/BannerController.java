package com.alexis.app.controller;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alexis.app.model.Banner;
import com.alexis.app.service.IBannerService;
import com.alexis.app.util.Utileria;

@Controller
@RequestMapping("/banners")
public class BannerController {

	@Autowired
	private IBannerService bannerService;

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("banners", bannerService.obtenerBanners());
		return "banners/listBanners";
	}

	@GetMapping("/create")
	public String crear(@ModelAttribute Banner banner) {
		return "banners/formBanner";
	}
	
	@GetMapping("/edit/{id}")
	public String editarHorarios(@PathVariable("id") int idBanner, Model model) {
		model.addAttribute("banner", bannerService.buscarBannerPorId(idBanner));
		return "banners/formBanner";
	}
	
	@GetMapping("/delete/{id}")
	public String editarHorarios(@PathVariable("id") int idBanner, RedirectAttributes attributes) {
		bannerService.eliminarBanerPorId(idBanner);
		attributes.addFlashAttribute("msg", "Banner Eliminado!");
		return "redirect:/banners/index";
	}

	@PostMapping("/save")
	public String guardar(@ModelAttribute Banner banner, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {

		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "banners/formBanner";
		}

		if (!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart, request);
			if (nombreImagen != null) {
				banner.setArchivo(nombreImagen);
			}
		}
		bannerService.insertarBanner(banner);
		attributes.addFlashAttribute("msg", "Banner Guardado!");
		return "redirect:/banners/index";
	}
}
