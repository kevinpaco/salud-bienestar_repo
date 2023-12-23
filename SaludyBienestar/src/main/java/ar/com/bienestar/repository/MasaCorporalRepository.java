package ar.com.bienestar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.bienestar.model.IndiceMasaCorporal;

@Repository
public interface MasaCorporalRepository extends JpaRepository<IndiceMasaCorporal, Integer> {

}
