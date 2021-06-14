package com.codesa.bussines.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codesa.repository.RolRepository;
import com.codesa.bussines.RolService;
import com.codesa.model.dao.Rol;

@Service
public class RolServiceImpl implements RolService{
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public List<Rol> listarTodos() {		
		return (List<Rol>) rolRepository.findAll();
	}

	@Override
	public void guardar(Rol rol) {
		rolRepository.save(rol);
	}

	@Override
	public Rol buscarPorId(Integer id) {
		return rolRepository.findById(id).orElse(null);
	}

	@Override
	public void Rol(Integer id) {
		rolRepository.deleteById(id);
	}

}
