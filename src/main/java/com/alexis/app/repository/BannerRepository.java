package com.alexis.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexis.app.model.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {
	public List<Banner> findByEstatusOrderByIdDesc(String estatus);
}
