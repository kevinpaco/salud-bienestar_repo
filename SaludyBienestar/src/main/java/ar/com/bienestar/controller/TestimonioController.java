package ar.com.bienestar.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ar.com.bienestar.model.Testimonio;
import ar.com.bienestar.model.Usuario;
import ar.com.bienestar.service.TestimonioService;
import ar.com.bienestar.service.UsuarioService;

@Controller
@RequestMapping("/salud-bienestar")
//@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class TestimonioController {
	
	private int idUsu;

	@Autowired
	TestimonioService testService;
	
	@Autowired
	UsuarioService userService;
	
	@GetMapping("/testimonios")
	public String listTestimonios(@RequestParam(name="id",required=false)int usuId,Model model){
	   
		this.idUsu=usuId;
		model.addAttribute("testimonios", this.testService.listTestimonios(null));
		model.addAttribute("usuId", usuId);
		return "testimonios";
	}
	
	@GetMapping("/testimonio/{id}")
	public String getTestimonio(@PathVariable("id")int usuId,Model model) {
		model.addAttribute("usuId", usuId);
		model.addAttribute("test",new Testimonio());
		return "testimonioForm";
	}
	
	@PostMapping("/testimonio/guardar")
	public String setTestimonio(@ModelAttribute("testimonio")Testimonio testimonio,@RequestParam("id")int userId,Model model) {
	
		Usuario usu  = this.userService.buscarUsuario(userId);
		testimonio.setFecha(LocalDate.now());
		testimonio.setUsuario(usu);
		Testimonio test = this.testService.guardarTestimonio(testimonio);
		System.out.println("se guardo testimonio: "+test.getUsuario().getApellido()+" - "+test.getComentario());
		
		return "redirect:/salud-bienestar/testimonios?id="+userId;
	}
	
	@GetMapping("/gestion/testimonios/{id}")
	public String listarTestimonios(@PathVariable("id")int id,@Param("email")String email,Model model){
		
		List<Testimonio> listTest = this.testService.listTestimonios(email);
		if(listTest.size()==0 || listTest==null) {
			model.addAttribute("existe",0);
			model.addAttribute("testimonios",listTest);
		}else {
			model.addAttribute("existe",1);
			model.addAttribute("testimonios", listTest);
		}
	   model.addAttribute("userId",id);
		return "gestionTestimonios";
	}
	
	@GetMapping("/testimonio/eliminar/{id}")
	public String eliminarTestimonio(@PathVariable("id")int id){
		
		Testimonio eliminado = this.testService.eliminarTestimonio(id);
		return "redirect:/salud-bienestar/gestion/testimonios/"+this.idUsu;
	}
	
	
}
