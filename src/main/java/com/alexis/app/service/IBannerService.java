package com.alexis.app.service;

import java.util.List;

import com.alexis.app.model.Banner;

public interface IBannerService {

	public void insertarBanner(Banner banner); 
	public List<Banner> obtenerBanners();
	public List<Banner> obtenerBannersActivos(String estatus);
	public Banner buscarBannerPorId(int idBanner);
	public void eliminarBanerPorId(int idBanner);
	
}
