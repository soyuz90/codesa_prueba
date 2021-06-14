package com.codesa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@PostMapping("/buscar")
	public String mostrarTabla(String textoBusqueda, Model model) {
		List<Usuario> lista = usuarioService.friltarPorNombre(textoBusqueda);
		List<Rol> roles = rolService.listarTodos();
		Usuario usuario = new Usuario();
		model.addAttribute("listaUsuarios", lista);
		model.addAttribute("usuario", usuario);	
		model.addAttribute("roles", roles);			
		return "/usuario/formBuscar::tabla-usuarios";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		List<Rol> roles = rolService.listarTodos();
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);	
		model.addAttribute("roles", roles);	
		return "/usuario/formBuscar::info-usuario";
	}
	
	@PostMapping("/save") 
	public String guardar(@ModelAttribute Usuario usuario , RedirectAttributes redirectAttrs,Model model) {
		model.addAttribute("mensaje", "Hola desde guardar!!!");
		model.addAttribute("usuario", usuario);	
		usuarioService.guardar(usuario);	
		return "redirect:/usuario/listar";
	}
	
	@GetMapping("/listar")
	public String mostrarTabla( Model model) {
		List<Usuario> lista = usuarioService.listarTodos();
		List<Rol> roles = rolService.listarTodos();
		Usuario usuario = new Usuario();
		model.addAttribute("listaUsuarios", lista);
		model.addAttribute("usuario", usuario);	
		model.addAttribute("roles", roles);			
		return "/usuario/formBuscar::tabla-usuarios";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView  editar(@PathVariable("id") Integer id,Model model) {	
		List<Rol> roles = rolService.listarTodos();
		Usuario usuario = usuarioService.buscarPorId(id);
		model.addAttribute("usuario", usuario);
		model.addAttribute("roles", roles);	
		model.addAttribute("estado", usuario.getActivo().equalsIgnoreCase("s"));	
		return new ModelAndView("/usuario/formBuscar::info-usuario");
		
	}
	
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Integer id) {
		usuarioService.eliminar(id);	
		return "redirect:/usuario/listar";
	}

	@GetMapping("/validar/{nombre}") 
	public String validarNombre(@PathVariable("nombre")String nombre) {			 
		return usuarioService.validarNombre(nombre);
	}
}
