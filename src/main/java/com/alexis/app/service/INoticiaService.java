package com.alexis.app.service;

import java.util.List;

import com.alexis.app.model.Noticia;

public interface INoticiaService {
	public void guardarNoticia(Noticia noticia);
	public List<Noticia> obtenerNoticias();
	public void eliminarNoticiaPorId(int idNoticia);
	public Noticia buscarNoticiaPorId(int idNoticia);
	public List<Noticia> obtenerUltimasTresNoticias(String estatus);
	public List<Noticia> obtenerNoticiasActivas(String estatus);
	
}
