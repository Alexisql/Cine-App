package com.alexis.app.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexis.app.model.Noticia;
import com.alexis.app.repository.NoticiaRepository;
import com.alexis.app.service.INoticiaService;

@Service
public class NoticiaServiceImp implements INoticiaService {

	@Autowired
	private NoticiaRepository noticiaRepo;

	@Override
	public void guardarNoticia(Noticia noticia) {
		noticiaRepo.save(noticia);
	}

	@Override
	public List<Noticia> obtenerNoticias() {
		return noticiaRepo.findAll();
	}

	@Override
	public void eliminarNoticiaPorId(int idNoticia) {
		noticiaRepo.deleteById(idNoticia);

	}

	@Override
	public Noticia buscarNoticiaPorId(int idNoticia) {
		Optional<Noticia> optional = noticiaRepo.findById(idNoticia);
		if (optional.isPresent())
			return optional.get();
		return null;
	}

	@Override
	public List<Noticia> obtenerUltimasTresNoticias(String estatus) {
		return noticiaRepo.findTop3ByEstatusOrderByIdDesc(estatus);
	}

	@Override
	public List<Noticia> obtenerNoticiasActivas(String estatus) {
		return noticiaRepo.findByEstatus(estatus);
	}

}
