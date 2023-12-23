package ar.com.bienestar.service.imp;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.bienestar.model.Ingrediente;
import ar.com.bienestar.model.Receta;
import ar.com.bienestar.model.Usuario;
import ar.com.bienestar.repository.IngredienteRepository;
import ar.com.bienestar.service.IngredienteService;
import ar.com.bienestar.service.RecetaService;
import ar.com.bienestar.service.UsuarioService;
import ar.com.bienestar.util.Componenete;

@Service
public class IngredienteServiceImp implements IngredienteService{
	
	@Autowired
	IngredienteRepository ingRepo;
	
	@Autowired
	RecetaService recSer;

	@Override
	public List<Ingrediente> listarIngredientes() {
		// TODO Auto-generated method stub
		return this.ingRepo.findAll();
	}

	@Override
	public Ingrediente guardarIngrediente(Ingrediente ingrediente) {
		// TODO Auto-generated method stub
		this.ingRepo.save(ingrediente);
		Componenete.ingredientes.add(ingrediente);
		return ingrediente;
	}

	@Override
	public Ingrediente buscarIngrediente(int id) {
		// TODO Auto-generated method stub
		
		return this.ingRepo.findById(id).get();
	}

	@Override
	public Ingrediente eliminarIngrediente(int id) {
		// TODO Auto-generated method stub
		Ingrediente ing = this.ingRepo.findById(id).get();
		if(ing.getReceta()!=null) {
		    Receta res = this.recSer.buscarReceta(ing.getReceta().getId());
		    res.getIngredientes().remove(ing);
		    this.recSer.guardarReceta(res);
		    this.ingRepo.deleteById(id);
		}else {
			for (int i=0;i<=Componenete.getIngredientes().size();i++) {
				 if(Componenete.ingredientes.get(i).getNombre().equals(ing.getNombre()))
			               Componenete.getIngredientes().remove(i);		  
			}
			
			System.out.println("se elimino: "+ing.getNombre()+" tama: "+Componenete.ingredientes.size());
			this.ingRepo.deleteById(id);
		}
		return ing;
	}

	@Override
	public void modificarIngrediente(int id, Receta receta) {
		// TODO Auto-generated method stub
		Ingrediente ing =this.buscarIngrediente(id);
		ing.setReceta(receta);
		this.ingRepo.save(ing);
	}

}
