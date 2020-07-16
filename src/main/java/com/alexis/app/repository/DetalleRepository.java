package com.alexis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexis.app.model.Detalle;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Integer> {

}
