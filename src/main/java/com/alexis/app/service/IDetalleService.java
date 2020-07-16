package com.alexis.app.service;

import com.alexis.app.model.Detalle;

public interface IDetalleService {
	public void guardarDetalle(Detalle detalle);
	public Detalle buscarDetallePorId(int idDetalle);
	public void eliminarDetalle(int idDetalle);
	
}
