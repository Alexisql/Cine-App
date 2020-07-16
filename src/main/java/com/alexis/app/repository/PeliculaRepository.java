package com.alexis.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexis.app.model.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
	public List<Pelicula> findByEstatus(String estatus);
	public List<Pelicula> findByEstatusAndFechaEstreno(String estatus, Date fecha);
}
