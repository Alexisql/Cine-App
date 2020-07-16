package com.alexis.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexis.app.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
	public List<Horario> findByPelicula_IdAndFechaOrderByHora(int idPelicula, Date fecha);
}
