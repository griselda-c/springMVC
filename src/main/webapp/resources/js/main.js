/**
 * 
 */
$(function() {

});

$(document).ready(function() {

	$("#fechaInicio").datepicker({dateFormat: 'dd-mm-yy'});
	//$('#editFechaInicio').datepicker();
	$("#fechaFin").datepicker({dateFormat: 'dd-mm-yy'});
	$("#editFechaFin").datepicker({dateFormat: 'dd-mm-yy'});
	// $("#fechaFin").datepicker({ dateFormat: 'dd-mm-yy'});
	$('#editFechaInicio').datepicker({dateFormat:'dd-mm-yy'});
	mostrarOcultar();

	$('#form').submit(function(e) {

		e.preventDefault();// estamos evitando que se envie nuestro formulario
		// de manera normal
		viaAjax();
	});

	$('#editForm').submit(function(e) {
		e.preventDefault();
		editarProducto();

	});

});

function searchViaAjax() {

	/*
	 * contentType: tipo de datos que envio data: dato que envio dataType: tipo
	 * de dato que espero recibir del Controller url: peticion que realizo al
	 * controller
	 * 
	 */

	var data_string = $('#form').serialize();
	// alert('Datos serializados: '+data_string); // funciona

	$.ajax({
		// contentType : "application/x-www-form-urlencoded",
		/*
		 * beforeSend: function (){ alert("beforeSend"); },
		 */
		url : "http://localhost:8080/mvc/registrarProducto.json",
		headers : {
			Accept : "text/plain; charset=utf-8",
			"Content-Type" : "application/json"
		},
		type : "POST",
		data : data_string, // recupera la información del formulario y lo
		// convierte en json
		// FUNDAMENTAL PARA QUE FUNCIONE, VA SIN EL THIS
		dataType : "json", // el tipo de información que se
		// espera de respuesta

		timeout : 100000,
		// mimeType: 'application/json',
		success : function(resp) {
			// console.log("SUCCESS: ", resp);
			// var response = JSON.stringify(resp);
			display(resp);
			alert("ok");
		},
		error : function(data, status, er) {
			console.log("ERROR: ", er);
			alert('Error ' + er);
			display(data);
		},

		done : function(e) {
			console.log("DONE");
			enableSearchButton(true);
			alert('ok');
		}
	});

}

function viaPost() {
	var data_string = $('#form').serialize();
	alert('Datos serializados: ' + data_string); // funciona
	$.post({

		url : "http://localhost:8080/mvc/registrarProducto.json",
		accept : "application/json; charset=utf-8",
		contentType : "application/json",

		type : "POST",
		data : data_string, // recupera la información del formulario y lo
		// convierte en json
		// FUNDAMENTAL PARA QUE FUNCIONE, VA SIN EL THIS
		// contentType : "application/json", //contentType es el tipo de
		// informacion q se envia
		// Así, para enviar parámetros mediante
		// el método POST, es obligatorio incluir
		// la cabecera Content-Type
		dataType : 'json',
		timeout : 100000,
		success : function(resp) {
			console.log("SUCCESS: ", resp);
			alert("respuesta" + resp);
			// display(resp)
			// listarProductos();
		},
		error : function(e) {
			console.log("ERROR: ", e);
			alert('Error ' + e);
			// display(e);
		},
		done : function(e) {
			console.log("DONE");
			enableSearchButton(true);
			alert('done');
		}
	});

}

function viaAjax() {
	var data_string = $('#form').serialize();
	var fechaInicio = $('#fechaInicio').val();
	$.post({
		url : "http://localhost:8080/mvc/registrarProducto.json",
		data : $('#form').serialize(),
		type : "POST",
		error: function(e){
			console.log("ERROR " +e);
		},
		/*
		 * beforeSend: function(xhr) { xhr.setRequestHeader("Accept",
		 * "application/json"); xhr.setRequestHeader("Content-Type",
		 * "application/json"); },
		 */
		success : function(resp) {
			console.log("success "+resp);
			borrarInputs();
			//listarProductos();
			location.href = 'http://localhost:8080/mvc/productos.html'
		}
	});

}

function display(producto) {

	$('#tabla').last().append(
			'<tr>' + '<td>' + producto.nombreProducto + '</td>' + '<td>'
					+ producto.stockMinimo + '</td>' + '<td>'
					+ producto.oferta.precio + '</td>' + '<td>'
					+ producto.oferta.fechaInicio + '</td>' + '<td>'
					+ producto.oferta.fechaFin + '</td>' + '</tr>'

	);
}

function mostrarOcultar() {

	$('#ocultar-mostrar').click(function() {
		if ($(this).text() == "Mostrar productos") {
			$(this).text("Ocultar productos")
			$('#tabla').show();
		} else {
			$(this).text("Mostrar productos")// aprete ocultar productos
			$('#tabla').hide();
		}
	});

}

function eliminarProducto(nombre) {
	$.ajax({
		url : 'http://localhost:8080/mvc/eliminarProducto.html',
		type : 'POST',
		data : {
			nombreProducto : nombre
		},
		error : function(e) {
			console.log("ERROR: ", e);
			alert('Error ' + e);
		},
		success : function(response) {
			if (confirm("¿estas seguro de borrar este registro")) {
				//listarProductos();
				listado();
			} else {
				return false;
			}
			;

		}
	});
}

function listarProductos() {
	$.post({
		url : "http://localhost:8080/mvc/productos.json",
		type : "POST",
		error : function(e) {
			alert("error " + e);
		},
		success : function(resp) {
			$("#tbody").html(resp);
		}
	});

}
/*
function abrirModal(nombre, stockMinimo, precio, fechaInicial, fechaFinal) {
	jQuery.noConflict();
	$('#exampleModalLabel').text("Editar el producto " + nombre);
	$('#editNombreProducto').val(nombre);
	$('#editStockMinimo').val(stockMinimo);
	$('#editPrecio').val(precio);
	$('#editFechaInicio').val(fechaInicial);
	$('#editFechaFin').val(fechaFinal);
	//$('#myModal').modal('show');
	formularioEditarProducto();
}*/



function editarProducto() {
	$.post({
		url : "http://localhost:8080/mvc/editarProducto.json",
		data : $('#editForm').serialize(),
		type : "POST",
		
		error : function(error) {
			alert("error " + error);
			console.log(error)
		},
		success : function(resp) {
			jQuery.noConflict();
			console.log(resp);
			$('#modalSuccess').modal('show');
		}
	});

}

function borrarInputs() {
	$(':text').val("");
}

function alertClose(){
	$('#alerta').hide();
}
