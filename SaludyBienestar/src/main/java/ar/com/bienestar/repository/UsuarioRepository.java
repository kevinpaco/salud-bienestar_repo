package ar.com.bienestar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.bienestar.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Query("SELECT u FROM Usuario u WHERE "+" CONCAT(u.codigoUnico)"+" LIKE %?1%")
	public Usuario findByCodigoUnico(String codigo);

	@Query("SELECT u FROM Usuario u WHERE "+" CONCAT(u.email)"+" LIKE %?1%")
	public List<Usuario> findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u WHERE "+" CONCAT(u.nombre)"+" LIKE %?1%")
	public Usuario findByName(String name);
	
}
