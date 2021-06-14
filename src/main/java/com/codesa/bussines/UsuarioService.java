package com.codesa.bussines;

import java.util.List;
import java.util.Optional;

import com.codesa.model.dao.Usuario;



public interface UsuarioService {
	public List<Usuario> friltarPorNombre(String nombre);
	public void guardar(Usuario usuario);
	public Usuario buscarPorId(Integer id);
	public Optional<Usuario> buscarNombre(String nombre);
	public void eliminar(Integer id);	
	String validarNombre(String nombre);
	public List<Usuario> listarTodos();

}
