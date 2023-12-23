package ar.com.bienestar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.com.bienestar.exception.ModelException;
import ar.com.bienestar.model.Ingrediente;
import ar.com.bienestar.model.Receta;
import ar.com.bienestar.service.IngredienteService;

@Controller
@RequestMapping("/salud-bienestar")
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class IngredienteController {
	
	@Autowired
	IngredienteService ingSer;
	
	@GetMapping("/ingredientes")
	public ResponseEntity<?> listIngredientes(){
	   Map<String,Object> response = new HashMap<String,Object>();
	   try {
		     List<Ingrediente> listIng= this.ingSer.listarIngredientes();
		     response.put("Objeto", listIng);
		     response.put("msg","Se obtubo todos los ingredientes");		
	} catch (ModelException e) {
		// TODO: handle exception
		 response.put("Error: ", e.getMessage());
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	   return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@PostMapping("/ingrediente")
	public ResponseEntity<?> guardarIngredientes(@RequestBody Ingrediente ingrediente){
	   Map<String,Object> response = new HashMap<String,Object>();
	   try {
		     response.put("Objeto", this.ingSer.guardarIngrediente(ingrediente));
		     response.put("msg","Se guardo el ingrediente");
	} catch (ModelException e) {
		// TODO: handle exception
		 response.put("Error: ", e.getMessage());
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	   return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}

	@DeleteMapping("/ingrediente/eliminar/{id}")
	public ResponseEntity<?> EliminarIngredientes(@PathVariable("id")int id){
	   Map<String,Object> response = new HashMap<String,Object>();
	   try {
		     response.put("Objeto", this.ingSer.eliminarIngrediente(id));
		     response.put("msg","Se elimino el ingrediente");		
	} catch (ModelException e) {
		// TODO: handle exception
		 response.put("Error: ", e.getMessage());
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	   return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
