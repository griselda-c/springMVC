/**
 * 
 */
$(function() {

});

$(document).ready(function() {
	// alert("uno");

	$("#fechaInicio").datepicker({ dateFormat: 'dd-mm-yy'});
	$("#fechaFin").datepicker({ dateFormat: 'dd-mm-yy'});

	$('#form').submit(function(e) {
	
		e.preventDefault();// estamos evitando que se envie nuestro formulario
							// de manera normal
		
		// $.post("http://localhost:8080/mvc/registrarProducto.json",$('#form').serialize(),function(producto){
			
			// alert("HELLO");
		/*
		 * $('#tabla').last().append( '<tr>' + '<td>'+
		 * producto.nombreProducto +'</td>' + '<td>'+ producto.stockMinimo +'</td>' + '<td>'+
		 * producto.oferta.precio +'</td>' + '<td>'+
		 * producto.oferta.fechaInicio +'</td>' + '<td>'+
		 * producto.oferta.fechaFin +'</td>' + '</tr>'
		 * 
		 *  );
		 * 
		 * });
		 */
        
		  viaPost();
	     //searchViaAjax();
		// viaJson()
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
			Accept: "text/plain; charset=utf-8",         
		    "Content-Type": "application/json" 
		},
		type : "POST",
		data : data_string, // recupera la información del formulario y lo
							// convierte en json
		                    // FUNDAMENTAL PARA QUE FUNCIONE, VA SIN EL THIS
		dataType: "json",   // el tipo de información que se
											// espera de respuesta
		
		timeout : 100000,
		// mimeType: 'application/json',
		success : function(resp) {
			// console.log("SUCCESS: ", resp);
			// var response = JSON.stringify(resp);
			display(resp);
			alert("ok");
		},
		error : function(data,status,er) {
			console.log("ERROR: ", er);
			alert('Error '+ er);
			display(data);
		},
		
		done : function(e) {
			console.log("DONE");
			enableSearchButton(true);
			alert('ok');
		}
	});

}

function viaJson() {
	$.getJSON('http://localhost:8080/mvc/registrarProducto.html',

	function(producto) {
		alert("Exito");

	}

	)

}

function viaPost(){
	var data_string = $('#form').serialize();
	alert('Datos serializados: '+data_string); // funciona
	$.post({
		
		url : "http://localhost:8080/mvc/registrarProducto.json",
		accept: "application/json; charset=utf-8",         
		contentType: "application/json",
		
		type : "POST",
		data : data_string, // recupera la información del formulario y lo
							// convierte en json
		                   // FUNDAMENTAL PARA QUE FUNCIONE, VA SIN EL THIS
		// contentType : "application/json", //contentType es el tipo de
		// informacion q se envia
		// Así, para enviar parámetros mediante
		// el método POST, es obligatorio incluir
		// la cabecera Content-Type
		dataType :'json',
		timeout : 100000,
		success : function(resp) {
			console.log("SUCCESS: ", resp);
			alert("respuesta" +resp);
			display(resp)
		},
		error : function(e) {
			console.log("ERROR: ", e);
			alert('Error '+ e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
			enableSearchButton(true);
			alert('done');
		}
	});

}

function display(producto) {
    
	$('#tabla').last().append(
			'<tr>' + 
			'<td>' + producto.nombreProducto + '</td>' +
			'<td>' + producto.stockMinimo + '</td>' + 
			'<td>' + producto.oferta.precio + '</td>' + 
			'<td>' + producto.oferta.fechaInicio + '</td>'+
		    '<td>' + producto.oferta.fechaFin + '</td>' + 
		    '</tr>'

	);
	// alert(resp[1]);
	// $('#resultado').html(resp);
}
