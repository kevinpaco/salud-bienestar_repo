package ar.com.bienestar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.bienestar.model.Usuario;
import ar.com.bienestar.service.UsuarioService;

@Controller
@RequestMapping("/salud-bienestar")
public class InicioController {
	
	private int idUser=0;
	private String redirigir;
	
	@Autowired
	UsuarioService userSer;
  ///SOLUCIONAR AQUI
	@GetMapping("/inicio")
	public String inicioWeb(Model model) {
     
		return "redirect:/salud-bienestar/principal/0";
	}
	
	@GetMapping("/principal/{id}")
	public String inicioWeb2(@PathVariable("id")int usuId,Model model) {
	     try {
		   if(usuId == 0)
	    	   System.out.println("es null");
	       else 
	    	   System.out.println("no es null: "+usuId);
	           idUser=usuId;
			   Usuario usu = this.userSer.buscarUsuario(usuId);	
			   if(usu.getCodigoUnico()!=null) {
				   model.addAttribute("cod", usu.getCodigoUnico());
			   }else
				   model.addAttribute("cod", "0");
	           model.addAttribute("userId",idUser);
			return "index";
	     } catch (Exception e) {
				// TODO: handle exception
	    	 
	    	 model.addAttribute("userId",idUser); 
	    	 return "index";
			}
	}
	
	@GetMapping("/loggin")
	public String loginWeb(@RequestParam(name="log",required=false)String op, Model model) {
		String codUnique="";
		this.redirigir=op;
		model.addAttribute("opc", op);
		//model.addAttribute("userId",0);
		model.addAttribute("userId",this.idUser);
		model.addAttribute("codUnique", new Usuario());
		return "login";
	}
	
	@PostMapping("/confirmar")
	public String confirmWeb(@ModelAttribute("codUnique")Usuario codUnique,Model model) {
		Usuario user = this.userSer.buscarPorCodigoUnico(codUnique.getCodigoUnico());
	
		if(!codUnique.getCodigoUnico().equals(user.getCodigoUnico()) ) {
			model.addAttribute("existe", 0);
			return "login";
		}else {
		    return "redirect:/salud-bienestar"+redirigir+user.getId();
		}
	}
}
