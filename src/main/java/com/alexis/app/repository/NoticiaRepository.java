package com.alexis.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexis.app.model.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {
	public List<Noticia> findTop3ByEstatusOrderByIdDesc(String estatus);
	public List<Noticia> findByEstatus(String estatus);
}
