package ar.com.bienestar.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.bienestar.model.Receta;
import ar.com.bienestar.repository.RecetaRepository;
import ar.com.bienestar.service.RecetaService;

@Service
public class RecetaServiceImp implements RecetaService {
	
	@Autowired
	RecetaRepository recetaRep;

	@Override
	public List<Receta> listRescetas() {
		// TODO Auto-generated method stub
		return this.recetaRep.findAll();
	}

	@Override
	public Receta guardarReceta(Receta receta) {
		// TODO Auto-generated method stub
		return this.recetaRep.save(receta);
	}

	@Override
	public Receta buscarReceta(int id) {
		// TODO Auto-generated method stub
		return this.recetaRep.findById(id).get();
	}
	
	@Override
	public Receta modificarReceta(Receta receta, int id) {
		
		Receta rese = this.recetaRep.findById(id).get();
		rese.setNombre(receta.getNombre());
		rese.setCategoria(receta.getCategoria());
		rese.setPreparacion(receta.getPreparacion());
		rese.setImagen(receta.getImagen());
		rese.setIngredientes(receta.getIngredientes());
		this.recetaRep.save(rese);
		return rese;
	}

	@Override
	public Receta eliminarReceta(int id) {
		// TODO Auto-generated method stub
		Receta res = this.recetaRep.findById(id).get();
		this.recetaRep.deleteById(id);
		return res;
	}

}
