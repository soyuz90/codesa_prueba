package com.codesa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.codesa.bussines.RolService;
import com.codesa.bussines.UsuarioService;
import com.codesa.model.dao.Rol;
import com.codesa.model.dao.Usuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private  UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;
	
	/*
	 * Metodo encargado de mostar el formulario de gestion de usuarios
	 * @return 
	 */
	@GetMapping("/")
	public String busqueda(Model model) {	
		List<Usuario> lista = new ArrayList<>();
		List<Rol> roles = rolService.listarTodos();
		Usuario usuario = new Usuario();
		model.addAttribute("listaUsuarios", lista);
		model.addAttribute("usuario", usuario);	
		model.addAttribute("roles", roles);			
		return "/usuario/formBuscar";
	}
	
	/*
	 * Metodo encargado buscar usuarios concidentes al texto nombre
	 * @param String nombre
	 * @return listado de usuarios
	 */
	@PostMapping("/buscar")
	public String mostrarTabla(String nombre, Model model) {
		List<Usuario> lista = usuarioService.friltarPorNombre(nombre);
		List<Rol> roles = rolService.listarTodos();
		Usuario usuario = new Usuario();
		model.addAttribute("listaUsuarios", lista);
		model.addAttribute("usuario", usuario);	
		model.addAttribute("roles", roles);			
		return "/usuario/formBuscar::tabla-usuarios";
	}
	
	/*
	 * Metodo encargado de mostar el formulario de creacion / edicion de usuarios
	 * @return 
	 */
	@GetMapping("/crear")
	public String mostrarFormularioCreacion(Model model) {
		List<Rol> roles = rolService.listarTodos();
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);	
		model.addAttribute("roles", roles);	
		return "/usuario/formBuscar::info-usuario";
	}
	
	/*
	 * Metodo encargado registar usuarios en base de datos.
	 * @param Usuario usuario
	 * @return 
	 */
	@PostMapping("/save") 	
	public String guardar(Usuario usuario,Model model) {
		usuarioService.guardar(usuario);		
		List<Usuario> lista = usuarioService.listarTodos();
		model.addAttribute("listaUsuarios", lista);
		return "/usuario/formBuscar::tabla-usuarios";	
	}
	
	
	/*
	 * Metodo encargado hallar usuarios por id
	 * @param Integer id
	 * @return 
	 */	
	@GetMapping("/edit/{id}")
	public ModelAndView  editar(@PathVariable("id") Integer id,Model model) {	
		List<Rol> roles = rolService.listarTodos();
		Usuario usuario = usuarioService.buscarPorId(id);
		model.addAttribute("usuario", usuario);
		model.addAttribute("roles", roles);	
		//model.addAttribute("estado", usuario.getActivo().equalsIgnoreCase("M"));	
		return new ModelAndView("/usuario/formBuscar::info-usuario");		
	}
	
	
	/*
	 * Metodo encargado de eliminar usuario por id
	 * @param Integer id
	 * @return 
	 */	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Integer id,Model model) {
		usuarioService.eliminar(id);
		List<Usuario> lista = usuarioService.listarTodos();
		model.addAttribute("listaUsuarios", lista);
		return "/usuario/formBuscar::tabla-usuarios";		
	}

	/*
	 * Metodo encargado de validar la existencia de nombre de usuario
	 * @param Integer id
	 * @return 
	 */	
	@GetMapping("/validar/{nombre}") 
	public String validarNombre(@PathVariable("nombre")String nombre) {			 
		return usuarioService.validarNombre(nombre);
	}
}
