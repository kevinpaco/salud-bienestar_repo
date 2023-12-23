package ar.com.bienestar.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.bienestar.model.IndiceMasaCorporal;
import ar.com.bienestar.model.Usuario;
import ar.com.bienestar.repository.MasaCorporalRepository;
import ar.com.bienestar.service.MasaCorporalService;
import ar.com.bienestar.service.UsuarioService;
import ar.com.bienestar.util.Componenete;

@Service
public class MasaCorporalServiceImp implements MasaCorporalService{

	@Autowired
	MasaCorporalRepository masaRepo;
	
	@Autowired
	UsuarioService usuSer;
	
	@Override
	public List<IndiceMasaCorporal> listMasaCorporal() {
		// TODO Auto-generated method stub
		return this.masaRepo.findAll();
	}

	@Override
	public IndiceMasaCorporal guardarIndice(IndiceMasaCorporal masaCorporal,int idUsu,double peso) {
		// TODO Auto-generated method stub
		Usuario usu= this.usuSer.buscarUsuario(idUsu);
		masaCorporal.setUsuario(usu);
		masaCorporal.setEstado(Componenete.calculoIMC(usu, peso));
		this.masaRepo.save(masaCorporal);
		return masaCorporal;
	}

	@Override
	public IndiceMasaCorporal buscarMasaCorporal(int id) {
		// TODO Auto-generated method stub
		return this.masaRepo.findById(id).get();
	}

	@Override
	public IndiceMasaCorporal eliminarMaraCorporal(int id) {
		// TODO Auto-generated method stub
		IndiceMasaCorporal masa= this.masaRepo.findById(id).get();
		Usuario user = this.usuSer.buscarUsuario(masa.getUsuario().getId());
		user.getListMasaCorporal().remove(masa);
		this.usuSer.guardarUsuario(user);
		this.masaRepo.deleteById(id);
		return masa;
	}

	
}
