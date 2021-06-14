/*
 * Archivo js. del formulario de gestion de usuarios.
 * 
 */
 
$(document).ready(function() {   
    deshabilitar();  
   
});


function filtrar(){
	var textoBusqueda = $("#textoBusqueda").val();
	$.ajax({
		type: "POST",
		url: "/usuario/buscar/",
		data: {"textoBusqueda":textoBusqueda},
		success: function(response) {			
			$("#tabla").html( response );
			$("#save").attr("disabled",true);
		}
	});	
}


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

function enviar(){  	
	$('#formCrear').submit();
}



function deshabilitar(){  	 
	$("#save").attr("disabled",true);
	$("#edit").attr("disabled",true);
	$("#eliminar").attr("disabled",true);
}


function borrar(){  	
	$("#textoBusqueda").val("");	
	$('#tabla').find("tr:gt(0)").remove();
	$("#nombre").val("");	
	$('#activo1').prop('checked', false); 
	$('#activo2').prop('checked', false); 
	$('#rol').prop('selectedIndex',0);
}




function validar(){ 
	var nombre = $("#nombre").val()
	var activo1 = !$('#activo1').prop('checked');
	var activo2 = !$('#activo2').prop('checked');
	
	
	 if(nombre == "" || (activo1 && activo2)){		 
		 $("#mensaje").html("<div class='alert alert-warning' id='mensaje'>Nombre y/o Activo son Requeridos</div>");
		 return false;
	 }
	
	
	$.ajax({
		type: "GET",
		url: "/usuario/validar/"+nombre,		
		success: function(response) {
			if(response=="fail"){				
				$("#mensaje").html("<div class='alert alert-danger' id='mensaje'>Ya existe un usuario con ese" +
						           " nombre, por favor ingrese uno distinto.</div>");
			}else{
				enviar();
			}	
		}
	});	
}

function eliminar(){ 
	var id = $("#id").val();	
	$.ajax({
		type: "GET",
		url: "/usuario/delete/"+id,
		success: function(response) {			
			
		}
	});	
}
