package com.alexis.app.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexis.app.model.Banner;
import com.alexis.app.repository.BannerRepository;
import com.alexis.app.service.IBannerService;

@Service
public class BannerServiceImpl implements IBannerService {

	@Autowired
	private BannerRepository bannerRepo;

	@Override
	public void insertarBanner(Banner banner) {
		bannerRepo.save(banner);
	}

	@Override
	public List<Banner> obtenerBanners() {
		return bannerRepo.findAll();
	}

	@Override
	public Banner buscarBannerPorId(int idBanner) {
		Optional<Banner> optional = bannerRepo.findById(idBanner);
		if (optional.isPresent())
			return optional.get();
		return null;
	}

	@Override
	public void eliminarBanerPorId(int idBanner) {
		bannerRepo.deleteById(idBanner);
	}

	@Override
	public List<Banner> obtenerBannersActivos(String estatus) {
		return bannerRepo.findByEstatusOrderByIdDesc(estatus);
	}

}
