package ar.com.bienestar.service;

import java.util.List;

import ar.com.bienestar.model.Testimonio;

public interface TestimonioService {

	public List<Testimonio> listTestimonios(String email);
	public Testimonio guardarTestimonio(Testimonio testimonio);
	public Testimonio buscarTestimonio(int id);
	public Testimonio actualizarTestimonio(Testimonio testimonio,int id);
	public Testimonio eliminarTestimonio(int id);
}
