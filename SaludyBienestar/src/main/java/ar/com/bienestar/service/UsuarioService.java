package ar.com.bienestar.service;

import java.util.List;

import ar.com.bienestar.model.Usuario;

public interface UsuarioService {
  
	public Usuario guardarUsuario(Usuario usuario);
	public List<Usuario> listarUsuarios(String email);
	public Usuario actualizarUsuario(Usuario usuario,int id);
	public Usuario buscarPorCodigoUnico(String codigoUnico);
	public Usuario EliminarUsuario(int id);
	public Usuario buscarUsuario(int id);
}
