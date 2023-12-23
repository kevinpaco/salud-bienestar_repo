package ar.com.bienestar.service;

import java.util.List;

import ar.com.bienestar.model.Ingrediente;
import ar.com.bienestar.model.Receta;

public interface IngredienteService {
 
	public List<Ingrediente> listarIngredientes();
	public Ingrediente guardarIngrediente(Ingrediente ingrediente);
	public Ingrediente buscarIngrediente(int id);
	public Ingrediente eliminarIngrediente(int id);
	public void modificarIngrediente(int id,Receta receta);
}
