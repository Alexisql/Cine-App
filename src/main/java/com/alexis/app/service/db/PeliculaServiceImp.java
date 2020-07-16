package com.alexis.app.service.db;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexis.app.model.Pelicula;
import com.alexis.app.repository.PeliculaRepository;
import com.alexis.app.service.IPeliculaService;

@Service
public class PeliculaServiceImp implements IPeliculaService {

	@Autowired
	private PeliculaRepository peliculaRepo;

	public PeliculaServiceImp() {
		obtenerGeneros();
	}

	@Override
	public List<Pelicula> obtenerPeliculas() {
		return peliculaRepo.findAll();
	}

	@Override
	public Pelicula buscarPeliculaPorId(int idPelicula) {
		Optional<Pelicula> option = peliculaRepo.findById(idPelicula);
		if (option.isPresent())
			return option.get();
		return null;
	}

	@Override
	public void guardarPelicula(Pelicula pelicula) {
		peliculaRepo.save(pelicula);
	}

	@Override
	public List<String> obtenerGeneros() {
		List<String> generos = new LinkedList<>();
		generos.add("Accion");
		generos.add("Aventura");
		generos.add("Clasicas");
		generos.add("Comedia Romantica");
		generos.add("Drama");
		generos.add("Terror");
		generos.add("Thriller");
		generos.add("Infantil");
		generos.add("Accion y Aventura");
		generos.add("Romantica");
		return generos;
	}

	@Override
	public void eliminarPelicula(int idPelicula) {
		peliculaRepo.deleteById(idPelicula);
	}

	@Override
	public List<Pelicula> obtenerPeliculasActivas(String estatus) {
		return peliculaRepo.findByEstatus(estatus);
	}

	@Override
	public List<Pelicula> obtenerPeliculasActivasAndFecha(String estatus, Date fecha) {
		return peliculaRepo.findByEstatusAndFechaEstreno(estatus, fecha);
	}

}
