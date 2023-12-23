package ar.com.bienestar.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.bienestar.exception.ModelException;
import ar.com.bienestar.model.Usuario;
import ar.com.bienestar.repository.UsuarioRepository;
import ar.com.bienestar.service.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService{

	@Autowired
	private UsuarioRepository usuRepository;
	
	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
      usuario.setRol("normal");		
	  this.usuRepository.save(usuario);
	  return usuario;
	}

	@Override
	public List<Usuario> listarUsuarios(String email){
		// TODO Auto-generated method stub
		if(email==null) {
			return this.usuRepository.findAll();	
		}else {
			return usuRepository.findByEmail(email);
		}
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario, int id) {
		// TODO Auto-generated method stub
		Usuario usu = usuRepository.findById(id).get();
		usu.setNombre(usuario.getNombre());
		usu.setApellido(usuario.getApellido());
		usu.setEmail(usuario.getEmail());
		usu.setEstatura(usuario.getEstatura());
		usu.setFechaNacimiento(usuario.getFechaNacimiento());
		usu.setTelefono(usuario.getTelefono());
		usu.setSexo(usuario.getSexo());
		usuRepository.save(usu);
		return usu;
	}
	
	public Usuario buscarPorCodigoUnico(String codigoUnico) {
		Usuario user = this.usuRepository.findByCodigoUnico(codigoUnico);
		
		return user;
	}
	
	public Usuario buscarUsuario(int id) {
		
		return usuRepository.findById(id).get();
	}

	@Override
	public Usuario EliminarUsuario(int id) {
		// TODO Auto-generated method stub
		Usuario usu = usuRepository.findById(id).get();
		 usuRepository.deleteById(id);
		return usu;
	}

}
