package ar.com.bienestar.controller;

import java.util.HashMap;   
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import ar.com.bienestar.exception.ModelException;
import ar.com.bienestar.model.Usuario;
import ar.com.bienestar.service.UsuarioService;
import ar.com.bienestar.util.Componenete;

@Controller
@RequestMapping("/salud-bienestar")
//@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class UsuarioController {

	private int idUser=0;
	
	@Autowired
	private UsuarioService usuService;
	
	@GetMapping("/gestion/usuarios/{id}") 
	public String listUsuarios(@PathVariable("id")int id,@Param("email")String email,Model model){
	    List<Usuario> listUsuarios= this.usuService.listarUsuarios(email);
		model.addAttribute("usus", listUsuarios);		
		if(listUsuarios.size()==0)
			model.addAttribute("existe",0);
		else
			model.addAttribute("existe",1);
		model.addAttribute("userId",id);
		return "gestionUsuarios"; 
	}
	
	@GetMapping("/usuario/form/{id}")
	public String forUsuario(@PathVariable("id")int id, Model model) {
		if(id==0) {
		    model.addAttribute("dato",new Usuario());
		}else {
			model.addAttribute("dato",this.usuService.buscarUsuario(id));
		}
		this.idUser=id;
		model.addAttribute("usuId",0);
		return "formUsuario";
	}
	
	@PostMapping("/usuario/guardar")
	public String guardarUsuario(@Validated @ModelAttribute("dato") Usuario usuario,BindingResult bindingResult,
			Model model){
		 if(bindingResult.hasErrors()) {
			 model.addAttribute("dato",usuario);
			 System.out.println("es: "+usuario.getNombre()); 
			 model.addAttribute("usuId",this.idUser);
			 return "formUsuario";
		 }else { 
			 usuario.setCodigoUnico(Componenete.generarContrasena());
			 Usuario usu=usuService.guardarUsuario(usuario);
			 return "redirect:/salud-bienestar/principal/"+usu.getId();
		 }
	}
	
	@GetMapping("/gestion/usuarios/modificar/{id}") 
	public String modificarUsuario(@PathVariable("id")int id,@Param("idu")String idu,Model model){
		
	//	System.out.println("ussss: "+idu);
		Usuario buscarUsu = this.usuService.buscarUsuario(Integer.parseInt(idu));
		
		model.addAttribute("usuId", id);
		model.addAttribute("dato", buscarUsu);
		return "modificarUsuario"; 
	}
	
	@PostMapping("/gestion/usuario/actualizar/{id}")
	public String actualizarUsuario(@Validated @ModelAttribute("dato") Usuario usuario,@PathVariable("id")int id,BindingResult bindingResult,Model model){
		
		if(bindingResult.hasErrors()) {
			 model.addAttribute("dato",usuario);
			// System.out.println("es: "+usuario.getNombre()); 
			 model.addAttribute("usuId",this.idUser);
			 return "modificarUsuario";
		 }else {
		//	 System.out.println("modddd: "+id);
			 Usuario usu=usuService.actualizarUsuario(usuario, usuario.getId());			 
		
			 return "redirect:/salud-bienestar/gestion/usuarios/"+this.idUser;
		 }
	}
	
	@GetMapping("/gestion/usuario/eliminar/{id}")
	public String eliminarUsuario(@PathVariable("id")int id){
		Usuario user = this.usuService.EliminarUsuario(id);
		
		return "redirect:/salud-bienestar/gestion/usuarios/"+this.idUser;
	}
}
