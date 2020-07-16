package com.alexis.app.prueba;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexis.app.model.Noticia;
import com.alexis.app.model.Pelicula;
import com.alexis.app.repository.DetalleRepository;
import com.alexis.app.repository.NoticiaRepository;
import com.alexis.app.repository.PeliculaRepository;

public class main {
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		DetalleRepository repoPeli =  context.getBean("detalleRepository", DetalleRepository.class);
		//PeliculaRepository repoPeli =  context.getBean("peliculaRepository", PeliculaRepository.class);
		//NoticiaRepository repoNoti =  context.getBean("noticiaRepository", NoticiaRepository.class);
		//repoNoti.deleteAll();

		repoPeli.deleteById(10);

		
		context.close();
	}
}
