package com.alexis.app.service;

import java.util.Date;
import java.util.List;

import com.alexis.app.model.Pelicula;

public interface IPeliculaService {
	public List<Pelicula> obtenerPeliculas();
	public Pelicula buscarPeliculaPorId(int idPelicula);
	public void guardarPelicula(Pelicula pelicula);
	public List<String> obtenerGeneros();
	public void eliminarPelicula(int idPelicula);
	public List<Pelicula> obtenerPeliculasActivas(String estatus);
	public List<Pelicula> obtenerPeliculasActivasAndFecha(String estatus, Date fecha);
}
