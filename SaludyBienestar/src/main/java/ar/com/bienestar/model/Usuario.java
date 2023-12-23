package ar.com.bienestar.model;

import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GeneratorType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
@Table
public class Usuario {
  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message="Ingrese Nombre!")
	private String nombre;
	@NotEmpty(message="Ingrese Apellido!")
	private String apellido;
	@NotEmpty(message="Ingrese email!")
	@Email
	private String email;
	 @NotNull
	 @DateTimeFormat(iso = ISO.DATE)
	 @Past
	 @NotNull(message = "Ingrese fecha de Nacimiento!")
	private LocalDate fechaNacimiento;
	@NotNull(message="Ingrese Telefono!")
	@Min(value=111111111,message="Debe tener 9 dijitos")
	@Max(value=999999999,message="Debe tener 9 dijitos")
	private Long telefono;
	@NotNull(message="Ingrese Genero!")
	private String sexo;
	@NotNull(message="Ingrese Estatura!")
	@Min(value=10,message="Ingrese Estatura!")
	private int estatura;
	private String rol;
	private String codigoUnico;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,mappedBy = "usuario")
	@JsonBackReference
	private List<Testimonio> testimonios=new ArrayList<Testimonio>();  
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
	private List<IndiceMasaCorporal> listMasaCorporal= new ArrayList<IndiceMasaCorporal>();
	public Usuario() {
		super();
	}

	

	public Usuario(@NotEmpty(message = "Ingrese Nombre!") String nombre,
			@NotEmpty(message = "Ingrese Apellido!") String apellido,
			@NotEmpty(message = "Ingrese email!") @Email String email,
			@NotNull @Past @NotNull(message = "Ingrese fecha de Nacimiento!") LocalDate fechaNacimiento,
			@NotNull(message = "Ingrese Telefono!") @Min(value = 11111111, message = "Debe tener 8 dijitos") @Max(value = 99999999, message = "Debe tener 8 dijitos") Long telefono,
			@NotNull(message = "Ingrese Genero!") String sexo,
			@NotNull(message = "Ingrese Estatura!") @Min(value = 10, message = "Ingrese Estatura!") int estatura,
			String rol, String codigoUnico, List<Testimonio> testimonios, List<IndiceMasaCorporal> listMasaCorporal) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.sexo = sexo;
		this.estatura = estatura;
		this.rol = rol;
		this.codigoUnico = codigoUnico;
		this.testimonios = testimonios;
		this.listMasaCorporal = listMasaCorporal;
	}



	public String getRol() {
		return rol;
	}



	public void setRol(String rol) {
		this.rol = rol;
	}



	public String getCodigoUnico() {
		return codigoUnico;
	}



	public void setCodigoUnico(String codigoUnico) {
		this.codigoUnico = codigoUnico;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getEstatura() {
		return estatura;
	}

	public void setEstatura(int estatura) {
		this.estatura = estatura;
	}

	public List<Testimonio> getTestimonios() {
		return testimonios;
	}

	public void setTestimonios(List<Testimonio> testimonios) {
		this.testimonios = testimonios;
	}

	public List<IndiceMasaCorporal> getListMasaCorporal() {
		return listMasaCorporal;
	}

	public void setListMasaCorporal(List<IndiceMasaCorporal> listMasaCorporal) {
		this.listMasaCorporal = listMasaCorporal;
	}
	
	
}
