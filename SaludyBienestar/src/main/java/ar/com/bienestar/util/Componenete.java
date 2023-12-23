package ar.com.bienestar.util;

import java.lang.management.OperatingSystemMXBean;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.bienestar.model.IndiceMasaCorporal;
import ar.com.bienestar.model.Ingrediente;
import ar.com.bienestar.model.Usuario;
import ar.com.bienestar.repository.UsuarioRepository;
import ar.com.bienestar.service.UsuarioService;
import ar.com.bienestar.service.imp.UsuarioServiceImp;

@Component 
public class Componenete {
	
	@Autowired
	private UsuarioRepository userRepo;

	public Usuario TraerUsuario(String name) {
		
		return this.userRepo.findByName(name);
	}
	
	public static List<Ingrediente> ingredientes= new ArrayList<Ingrediente>();

	public static List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public static void setIngredientes(List<Ingrediente> ingredientes) {
		Componenete.ingredientes = ingredientes;
	}
	
	 public static String calculoIMC(Usuario usu,double peso) {
		 DecimalFormat df = new DecimalFormat("#.00");
	    	String estado="";
	    	
	    	double estaturaMtros= usu.getEstatura()/100.0;
	    	double imc=peso/estaturaMtros;
	       System.out.println("met usu: "+estaturaMtros);
	       System.out.println("imc: "+imc);
	    	if(imc<18.5)
	    		estado= "Su IMC es "+df.format(imc)+"-Esta por debajo de su peso ideal";
	    	if(imc>=18.5 && imc<=25)
	    		estado= "Su IMC es "+df.format(imc)+"-Esta en su peso ideal";
	    	if(imc>25)
	    	    estado= "Su IMC es "+df.format(imc)+"-Tiene sobrepeso";
	    	return estado;
	    }
	 
	 public static String generarContrasena() {
		 final String caracteres="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		 SecureRandom random = new SecureRandom();
		 StringBuilder contrasena=new StringBuilder();
		 
		 for(int i=0;i<5;i++) {
			 int index = random.nextInt(caracteres.length());
			 contrasena.append(caracteres.charAt(index));
		 }
		 
		 return contrasena.toString();
	 }
	 
	 public static int calcularEdad(LocalDate fechaNacimiento) {
		 int edad = LocalDate.now().getYear()-fechaNacimiento.getYear();
		 
		 return edad;
	 }
	 
	 public static double calcularPesoIdeal(int estatura,int edad) {
		 double pesoIdeal;
		 pesoIdeal= estatura-100+((edad/10)*0.9);
		 return pesoIdeal;
	 }
	 
	 public static Comparator<IndiceMasaCorporal> ordenarUltimoPrimero() {
		    return (imc1, imc2) -> imc2.getFechaImc().compareTo(imc1.getFechaImc());
		}
	 public static boolean contieneArrobaYCom(String email) {
		    return email.contains("@") && email.contains(".com");
		}
}
