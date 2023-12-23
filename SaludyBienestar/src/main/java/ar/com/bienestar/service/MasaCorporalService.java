package ar.com.bienestar.service;

import java.util.List;

import ar.com.bienestar.model.IndiceMasaCorporal;

public interface MasaCorporalService {
 
	public List<IndiceMasaCorporal> listMasaCorporal();
	public IndiceMasaCorporal guardarIndice(IndiceMasaCorporal masaCorporal,int idUsu,double peso);
	public IndiceMasaCorporal buscarMasaCorporal(int id);
	public IndiceMasaCorporal eliminarMaraCorporal(int id);
}
