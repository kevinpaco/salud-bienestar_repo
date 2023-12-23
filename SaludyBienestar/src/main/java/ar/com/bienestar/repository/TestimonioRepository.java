package ar.com.bienestar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.bienestar.model.Testimonio;

@Repository
public interface TestimonioRepository extends JpaRepository<Testimonio, Integer>{

}
