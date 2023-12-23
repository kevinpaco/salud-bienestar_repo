package ar.com.bienestar.controller;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.com.bienestar.exception.ModelException;
import ar.com.bienestar.model.Ingrediente;
import ar.com.bienestar.model.Receta;
import ar.com.bienestar.model.Usuario;
import ar.com.bienestar.service.IngredienteService;
import ar.com.bienestar.service.RecetaService;
import ar.com.bienestar.service.UsuarioService;
import ar.com.bienestar.service.imp.UsuarioServiceImp;
import ar.com.bienestar.util.Componenete;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/salud-bienestar")
//@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class RecetaController {
	
	private int idUser=0;
	private int idRes=0;
	
	@Autowired
	RecetaService resSer;
	
	@Autowired
	IngredienteService ingSer;
	
	@Autowired
	UsuarioServiceImp usuSer;
	
	@GetMapping("/recetas")
	public String getResetas(@RequestParam(name="id")int usuId,Model model) {
		
		model.addAttribute("recetas", this.resSer.listRescetas());
		idUser=usuId;
		model.addAttribute("usuId", usuId);
		return "recetas";
	}
	
	@GetMapping("/receta/{id}")
	public String getReseta(@PathVariable("id")int id ,Model model) {
		
		model.addAttribute("dato",this.resSer.buscarReceta(id));
		model.addAttribute("ing",this.resSer.buscarReceta(id).getIngredientes());
		model.addAttribute("usuId", idUser);
		return "receta";
	}
	
	@GetMapping("/gestion/recetas/{id}")
	public String listResetas(@PathVariable("id")int id,Model model) {
		this.idUser=id;
		model.addAttribute("userId", id);
		model.addAttribute("recetas", this.resSer.listRescetas());
		return "gestionRecetas";
	}
	
	//id es del ususario
	@GetMapping("/formulario/receta/{id}")
	public String recetaForm(@PathVariable("id")int id,@Param("ingredient")String ingredient,Model model) {
	   
		model.addAttribute("userId", this.idUser);
        model.addAttribute("receta", new Receta());
        model.addAttribute("ingrediente",new Ingrediente());
        model.addAttribute("ings",Componenete.getIngredientes());
        return "formReceta";
	}
	
	@GetMapping("/guardar/ingrediente/{id}")
	public String getGuardarIngredientePage(@PathVariable("id")int id,@Param("ingredient")String ingredient,Model model) {
		if(ingredient!=null) {
		      Ingrediente ing = new Ingrediente();
		      ing.setNombre(ingredient);
	  
	          this.ingSer.guardarIngrediente(ing);
		}		
		return "redirect:/salud-bienestar/formulario/receta/"+id;
	}
	
	@PostMapping("/guardar/receta")
	public String crearReceta(@Validated @ModelAttribute("receta") Receta receta,BindingResult bindingResult
			,Model model){
		
		  if(bindingResult.hasErrors()) {
			  model.addAttribute("receta",receta);
			  return "formReceta";
		  }else {
			  Receta recet = this.resSer.guardarReceta(receta);
			  
			  for(Ingrediente in: Componenete.getIngredientes()) {
			  this.ingSer.modificarIngrediente(in.getId(), recet);
			  }
			  Componenete.ingredientes= new ArrayList<Ingrediente>();
		  }
	     return "redirect:/salud-bienestar/gestion/recetas";
	}
	
	// Pagina para eliminar ingredientes de nueva receta
			@GetMapping("/eliminar/ingrediente/nuevo/{id}")
			public String eliminarIngredienteNuevo(@PathVariable(value = "id")int id) {
				
				this.ingSer.eliminarIngrediente(id); 
				
				return "redirect:/salud-bienestar/formulario/receta/"+this.idUser;
			}
	
	//Modificar Receta
	@GetMapping("/actualizar/ingrediente/{ingId}")
	public String actualizarIngredientePage(@PathVariable("ingId")int ingId,@Param("ingredient")String ingredient,Model model) {
		if(ingredient!=null) {
		      Ingrediente ing = new Ingrediente();
		      ing.setNombre(ingredient);
	           this.ingSer.guardarIngrediente(ing);
		}
		
	//			Ingrediente ingrediente = new Ingrediente();
	//	ingrediente.setNombre(ing);
		//this.ingredService.guardar(ingrediente);		
		return "redirect:/salud-bienestar/receta/modificar/"+ingId;
	}
	
	//el id es de receta
	@GetMapping("/receta/modificar/{id}") 
	public String actulizarReceta(@PathVariable("id")int id,Model model){
	    this.idRes=id;
		Receta res= this.resSer.buscarReceta(id);
		model.addAttribute("userId", this.idUser);
        model.addAttribute("receta", res);
        model.addAttribute("ingId", res.getId());
        model.addAttribute("ingrediente",new Ingrediente());
        for (Ingrediente ing : Componenete.getIngredientes()) {
		     res.getIngredientes().add(ing);    
		}
        model.addAttribute("ings",res.getIngredientes());
      //  model.addAttribute("ings",Componenete.getIngredientes());
		return "formModificarReceta";
	}
	
	@PostMapping("/actualizar/receta/{id}")
	public String actualizarReceta(@PathVariable("id")int id,@Validated @ModelAttribute("receta") Receta receta,BindingResult bindingResult
			,Model model){
	 System.out.println("img: "+id);
		  if(bindingResult.hasErrors()) {
			  model.addAttribute("receta",receta);
			  return "formModificarReceta";
		  }else {
			  Receta recet = this.resSer.modificarReceta(receta, id);
			  
			  for(Ingrediente in: Componenete.getIngredientes()) {
			  this.ingSer.modificarIngrediente(in.getId(), recet);
			  }
			  Componenete.ingredientes= new ArrayList<Ingrediente>();
		  }
	     return "redirect:/salud-bienestar/gestion/recetas";
	}
	
	// Pagina para eliminar ingredientes de modificar receta
		@GetMapping("/eliminar/ingrediente/{id}")
		public String eliminarIngrediente(@PathVariable(value = "id")int id) {
			
			this.ingSer.eliminarIngrediente(id); 
			
			return "redirect:/salud-bienestar/receta/modificar/"+this.idRes;
		}
	
	@GetMapping("/receta/eliminar/{id}")
	public String eliminarReceta(@PathVariable("id")int id){
		this.resSer.eliminarReceta(id);
		return "redirect:/salud-bienestar/gestion/recetas";
	}

}
