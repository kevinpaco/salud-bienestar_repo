package ar.com.bienestar.service;

import java.util.List;

import ar.com.bienestar.model.Receta;

public interface RecetaService {

	public List<Receta> listRescetas();
	public Receta guardarReceta(Receta receta);
	public Receta buscarReceta(int id);
	public Receta eliminarReceta(int id);
	public Receta modificarReceta(Receta receta,int id);
}
