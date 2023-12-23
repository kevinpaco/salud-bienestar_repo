package ar.com.bienestar.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.bienestar.model.Testimonio;
import ar.com.bienestar.model.Usuario;
import ar.com.bienestar.repository.TestimonioRepository;
import ar.com.bienestar.repository.UsuarioRepository;
import ar.com.bienestar.service.TestimonioService;
import ar.com.bienestar.service.UsuarioService;

@Service
public class TestimonioServiceImp implements TestimonioService{

	@Autowired
	TestimonioRepository testRepository;
	
	@Autowired
	UsuarioService usuSer;
	
	@Override
	public List<Testimonio> listTestimonios(String email) {
		// TODO Auto-generated method stub
		if(email==null)		
	     	return testRepository.findAll();
		else {
			List<Usuario> list=usuSer.listarUsuarios(email);
			System.out.println("service: "+list.size());
			if(list.size()==0)
				return new ArrayList<>();
			else
			    return list.get(0).getTestimonios(); 
		}
	}

	@Override
	public Testimonio guardarTestimonio(Testimonio testimonio) {
		// TODO Auto-generated method stub
		this.testRepository.save(testimonio);
		return testimonio;
	}

	@Override
	public Testimonio buscarTestimonio(int id) {
		// TODO Auto-generated method stub
		
		return testRepository.findById(id).get();
	}

	@Override
	public Testimonio actualizarTestimonio(Testimonio testimonio, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Testimonio eliminarTestimonio(int id) {
		// TODO Auto-generated method stub
		Testimonio test = testRepository.findById(id).get();
		Usuario usu= this.usuSer.buscarUsuario(test.getUsuario().getId());
		usu.getTestimonios().remove(test);
		this.usuSer.guardarUsuario(usu);
		testRepository.deleteById(id);
		return test;
	}

}
