package com.codesa.model.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "usuario")
@NamedQuery(name = "Usuario.findAllByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre LIKE :nombre ")
@NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre LIKE :nombre ")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario",unique = true, nullable = false)
	private Integer id;	
	
	@ManyToOne
	@JoinColumn(name="id_rol")	
	private Rol rol;
	
	@Column(name = "nombre",unique = true, nullable = false)
	private String nombre;
	
	@Column(name = "activo",unique = true, nullable = false)
	private String activo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}	
	
	
	

}
