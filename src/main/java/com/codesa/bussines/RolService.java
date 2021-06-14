package com.codesa.bussines;

import java.util.List;

import com.codesa.model.dao.Rol;

public interface RolService {
	public List<Rol> listarTodos();
	public void guardar(Rol rol);
	public Rol buscarPorId(Integer id);
	public void Rol(Integer id);

}
