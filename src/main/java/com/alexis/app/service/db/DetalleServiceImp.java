package com.alexis.app.service.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexis.app.model.Detalle;
import com.alexis.app.repository.DetalleRepository;
import com.alexis.app.service.IDetalleService;

@Service
public class DetalleServiceImp implements IDetalleService {

	@Autowired
	private DetalleRepository detalleRepo;

	@Override
	public void guardarDetalle(Detalle detalle) {
		detalleRepo.save(detalle);
	}

	@Override
	public Detalle buscarDetallePorId(int idDetalle) {
		Detalle detalle = null;
		Optional<Detalle> option = detalleRepo.findById(idDetalle);
		if (option.isPresent()) {
			detalle = option.get();
		}
		return detalle;
	}

	@Override
	public void eliminarDetalle(int idDetalle) {
		detalleRepo.deleteById(idDetalle);
	}

}
