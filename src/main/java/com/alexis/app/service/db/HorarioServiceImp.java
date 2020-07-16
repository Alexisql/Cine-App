package com.alexis.app.service.db;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexis.app.model.Horario;
import com.alexis.app.repository.HorarioRepository;
import com.alexis.app.service.IHorariosService;

@Service
public class HorarioServiceImp implements IHorariosService {

	@Autowired
	private HorarioRepository horarioRepo;

	@Override
	public List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha) {
		return horarioRepo.findByPelicula_IdAndFechaOrderByHora(idPelicula, fecha);
	}

	@Override
	public void guardarHorario(Horario horario) {
		horarioRepo.save(horario);
	}

	@Override
	public List<Horario> obtenerHorarios() {
		return horarioRepo.findAll();
	}

	@Override
	public void eliminarHorarioPorId(int idHorario) {
		horarioRepo.deleteById(idHorario);
	}

	@Override
	public Horario buscarHorarioPorId(int idHorario) {
		Optional<Horario> optional = horarioRepo.findById(idHorario);
		if (optional.isPresent())
			return optional.get();
		return null;
	}

}
