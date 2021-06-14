package com.codesa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codesa.model.dao.Usuario;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	List<Usuario> findAllByNombre(@Param("nombre") String nombre);	
	Optional<Usuario> findByNombre(@Param("nombre") String nombre);
	
}
