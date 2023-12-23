package ar.com.bienestar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.bienestar.model.Ingrediente;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente,Integer> {

}
