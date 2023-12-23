package ar.com.bienestar.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table
public class IndiceMasaCorporal {
   
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private LocalDate fechaImc;
	@NotEmpty(message="Debe ingresar su peso")
	private String estado;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	public IndiceMasaCorporal() {
		super();
	}

	public IndiceMasaCorporal(LocalDate fechaImc, String estado, Usuario usuario) {
		super();
		this.fechaImc = fechaImc;
		this.estado = estado;
		this.usuario = usuario;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFechaImc() {
		return fechaImc;
	}

	public void setFechaImc(LocalDate fechaImc) {
		this.fechaImc = fechaImc;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
   
	
	
}
