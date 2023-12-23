package ar.com.bienestar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.bienestar.model.Receta;

@Repository
public interface RecetaRepository extends JpaRepository<Receta,Integer> {

}
