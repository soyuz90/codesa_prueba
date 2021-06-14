package com.codesa.bussines.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codesa.bussines.UsuarioService;
import com.codesa.model.dao.Usuario;
import com.codesa.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository  usuarioRepository;

	@Override
	public List<Usuario> friltarPorNombre(String nombre) {
		return usuarioRepository.findAllByNombre("%"+nombre+"%");		
	}

	@Override
	public void guardar(Usuario usuario) {
			usuarioRepository.save(usuario);
	}

	@Override
	public Usuario buscarPorId(Integer id) {		
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Integer id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	public Optional<Usuario> buscarNombre(String nombre) {		
		return usuarioRepository.findByNombre(nombre);
	}

	@Override
	public String validarNombre(String nombre) {
		return buscarNombre(nombre).isPresent()? "/usuario/mensajeFail": "/usuario/mensajeOk";
	}

	@Override
	public List<Usuario> listarTodos() {		
		return (List<Usuario>) usuarioRepository.findAll();
	}
	
	
	
}
