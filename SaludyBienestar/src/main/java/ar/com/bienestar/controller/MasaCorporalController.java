package ar.com.bienestar.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.com.bienestar.exception.ModelException;
import ar.com.bienestar.model.IndiceMasaCorporal;
import ar.com.bienestar.model.Usuario;
import ar.com.bienestar.service.MasaCorporalService;
import ar.com.bienestar.service.UsuarioService;
import ar.com.bienestar.util.Componenete;

@Controller
@RequestMapping("/salud-bienestar")
//@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class MasaCorporalController {

	@Autowired
	MasaCorporalService masaSer;
	
	@Autowired
	UsuarioService usuSer;
	
	@GetMapping("/IMC/calculadora/{id}")
	public String calImc(@PathVariable("id")int id,Model model) {
		if(id==0) {			
			return "redirect:/salud-bienestar/usuario/form/0";
		}else {
			Usuario usu= this.usuSer.buscarUsuario(id);
			IndiceMasaCorporal masaCorp=new IndiceMasaCorporal();
			masaCorp.setUsuario(usu);
			model.addAttribute("userId", usu.getId());
			if(usu.getListMasaCorporal().size()==0) {
				System.out.println("usu: "+masaCorp.getUsuario().getNombre());
				model.addAttribute("dato",masaCorp);
				model.addAttribute("usuarioId",usu.getId());
			}else {
				System.out.println("usu: "+masaCorp.getUsuario().getNombre());
				model.addAttribute("dato",masaCorp);
				model.addAttribute("usuarioId",usu.getId());
				model.addAttribute("resul",usu.getListMasaCorporal().get(usu.getListMasaCorporal().size()-1).getEstado());
				List<IndiceMasaCorporal> imcs = usu.getListMasaCorporal();
				List<IndiceMasaCorporal> imcsInvertida = new ArrayList<>();
				for (int i = imcs.size() - 1; i >= 0; i--) {
				    imcsInvertida.add(imcs.get(i));
				}
				model.addAttribute("masa",imcsInvertida);
			}			
		   return "calculadoraImc";
		}
	}
	
	@PostMapping("/IMC/{usuarioId}")
	public String guardarIMC(@ModelAttribute("dato") IndiceMasaCorporal masa,@PathVariable("usuarioId")int usuarioId,
			BindingResult bindingResult,Model model){
		double peso=0;
		LocalDate fechaHoy= LocalDate.now();
	    if(bindingResult.hasErrors()) {
	    	 model.addAttribute("dato", masa);
	    	 return "calculadoraImc";
	    }else {
	        peso= Double.parseDouble(masa.getEstado());	        
	        Usuario usu= this.usuSer.buscarUsuario(usuarioId);
	        masa.setFechaImc(fechaHoy);
	        masa.setEstado(Componenete.calculoIMC(usu, peso));
	        this.masaSer.guardarIndice(masa, usuarioId, peso);
	    	return "redirect:/salud-bienestar/IMC/calculadora/"+usuarioId;
	    }
	}
	
	///PESO IDEAL
	@GetMapping("/IMC/peso-ideal/{id}")
	public String pesoIdeal(@PathVariable("id")int id,Model model) {
		Usuario usu=this.usuSer.buscarUsuario(id);
		int edad=Componenete.calcularEdad(usu.getFechaNacimiento());
		System.out.println("edad: "+edad);
		System.out.println("Peso Ideal: "+Componenete.calcularPesoIdeal(usu.getEstatura(),edad));
		model.addAttribute("pesoIdeal",Componenete.calcularPesoIdeal(usu.getEstatura(),edad));
		model.addAttribute("estatura", usu.getEstatura());
		model.addAttribute("edad", edad);
		model.addAttribute("usuId", usu.getId());
		return "pesoIdeal";
	} 
	 ///activa el modal 
	 @GetMapping("/modal-content")
	    public String getModalContent(Model model) {
	        // Puedes agregar lógica aquí para pasar datos al modal si es necesario
	        return "modal"; // Esto renderizará la plantilla Thymeleaf modal-content.html
	    }
}
