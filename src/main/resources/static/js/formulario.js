/*
 * Archivo js. del formulario de gestion de usuarios.
 * 
 */
 
$(document).ready(function() {   
    deshabilitar();  
});


/*
 * Metodo encargado invocar el metodo de busqueda de usuarios
 * @param String nombre
 * @return html
 */
function filtrar(){
	var nombre = $("#textoBusqueda").val();
	$.ajax({
		type: "POST",
		url: "/usuario/buscar/",
		data: {"nombre":nombre},
		success: function(response) {			
			$("#tabla").html( response );
			$("#save").attr("disabled",true);
		}
	});	
}

/*
 * Metodo encargado invocar el metodo para la presentacion del fomulario de creacion / edicion
 * de usuarios * 
 * @return html
 */
function crear(){ 
	$.ajax({
		type: "GET",
		url: "/usuario/crear/",
		success: function(response) {			
			$("#info-usuario").html( response );
			$("#edit").attr("disabled",true);
			$("#eliminar").attr("disabled",true);
		}
	});	
}

/*
 * Metodo encargado obtener los datos del usuario sobre el que se realiza
 * click en la tabla de usuarios.
 * @param String nombre 
 * @return html
 */
$( "body" ).on( "click", "table tr ", function() {
var row = this;
var id = this.cells[0].firstChild.data;

	$.ajax({
		type: "GET",
		url: "/usuario/edit/"+id,
		success: function(response) {			
			$("#info-usuario").html( response );
			$("#save").attr("disabled",true);
		}
	});

});

/*
 * Metodo encargado de crear nuevos usuarios 
 * @return html
 */
function guardar(){
	var nombre = $("#nombre").val();
	if(validarCampos(nombre) && validarNombre(nombre)){		
		var formData = $("#formCrear").serialize();
		$.ajax({
			type: "POST",
			url: "/usuario/save",
			data: formData,			
			success: function(response) {			
				$("#tabla").html(response);
				limpiarFormularioCreacion();
			}
		});	
	}
}

/*
 * Metodo encargado de invovar el metodo de editar  usuarios
 * @return html
 */
function editar(){
	var nombre = $("#nombre").val();
	if(validarCampos(nombre) ){		
		var formData = $("#formCrear").serialize();
		$.ajax({
			type: "POST",
			url: "/usuario/save",
			data: formData,			
			success: function(response) {			
				$("#tabla").html(response);
				limpiarFormularioCreacion();
			}
		});	
	}
}

/*
 * Metodo encargado de deshabilitar, los botones del formulario 
 * de Informacion del Usuario
 * @return html
 */
function deshabilitar(){  	 
	$("#save").attr("disabled",true);
	$("#edit").attr("disabled",true);
	$("#eliminar").attr("disabled",true);
}

/*
 * Metodo encargado de realizar la limpieza del todo el formulario de gestion de usuarios
 * @return 
 */
function limpiarFormulario(){  	
	$("#textoBusqueda").val("");	
	$('#tabla').find("tr:gt(0)").remove();
	limpiarFormularioCreacion();
}



/*
 * Metodo encargado de validar campos del formulario de gestion de usuarios
 * @param String nombre 
 * @return 
 */
function validarCampos(nombre){ 	
	var activo1 = !$('#activo1').prop('checked');
	var activo2 = !$('#activo2').prop('checked');
	
	 if(nombre == "" || (activo1 && activo2)){		 
		 $("#mensaje").html("<div class='alert alert-warning' id='mensaje'>Nombre y/o Activo son Requeridos</div>");
		 return false;
	 }else{
		 return true;
	 }
}

/*
 * Metodo encargado de validar que el nombre del usuario a crea NO exista en base de datos
 * @param String nombre 
 * @return 
 */
function validarNombre(nombre){
	let valido = true;
	$.ajax({
		type: "GET",
		url: "/usuario/validar/"+nombre,		
		success: function(response) {
			if(response=="fail"){				
				$("#mensaje").html("<div class='alert alert-danger' id='mensaje'>Ya existe un usuario con ese" +
						           " nombre, por favor ingrese uno distinto.</div>");
				return false;
			}
		}
	});	
	return valido;
}

/*
 * Metodo encargado de eleminar usuarios de la base de datos.
 * @return 
 */
function borrar(){ 
	var id = $("#id").val();	
	$.ajax({
		type: "GET",
		url: "/usuario/delete/"+id,
		success: function(response) {			
			$("#tabla").html(response);
			limpiarFormularioCreacion();
			deshabilitar();
		}
	});	
}

/*
 * Metodo encargado de realizar la limpieza del todo el formulario de Informacion del Usuarios
 * @return 
 */
function limpiarFormularioCreacion(){
	$("#nombre").val("");	
	$('#activo1').prop('checked', false); 
	$('#activo2').prop('checked', false); 
	$('#rol').prop('selectedIndex',0);	
}
