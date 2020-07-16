package com.alexis.app.service;

import java.util.Date;
import java.util.List;

import com.alexis.app.model.Horario;

public interface IHorariosService {
	public List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha);
	public void guardarHorario(Horario horario);
	public List<Horario> obtenerHorarios();
	public void eliminarHorarioPorId(int idHorario);
	public Horario buscarHorarioPorId(int idHorario);
}
