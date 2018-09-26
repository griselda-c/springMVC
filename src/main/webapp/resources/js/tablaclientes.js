/**
 * 
 */

$(document).ready(function(){
	$('#editClienteForm').submit(function(e){
		e.preventDefault();
		viaAjax();
	})
})




function viaAjax(){
	$.post({
		url: "http://localhost:8080/mvc/serviceEditarCliente.json",
		data: $("#editClienteForm").serialize(),
		type:'POST',
		error:function(error){
			console.log("ERROR "+error);
		},
		success: function(resp){
			jQuery.noConflict();
			$('#modalSuccess').modal('show');
		},
	});
}

function borrar(id) {
	$.ajax({
		url : 'http://localhost:8080/mvc/eliminarCliente.html',
		type : 'POST',
		data : {
			clienteId : id
		},
		error : function(e) {
			console.log("ERROR: ", e);
			alert('Errorrrrr ' + e);
		},
		success : function(response) {
			listado();

		}
	});
}



  
  
  
  function eliminarCliente(id){	
	  console.log("jquery eliminar cliente "+id);
	  $('#dialog-confirm').dialog({
		  resizable:false,
	  	  height:"auto",
	  	  width: 400,
	  	  modal:true,
	  	  buttons:{
	  		  "Eliminar Cliente": function (){
	  		    $(this).dialog("close");
	  			$.ajax({
	  				url : 'http://localhost:8080/mvc/eliminarCliente.html',
	  				type : 'POST',
	  				data : {
	  					clienteId : id
	  				},
	  				error : function(e) {
	  					console.log("ERROR: ", e);
	  				},
	  				success: function(res){
	  					location.href = 'http://localhost:8080/mvc/clientes.html'
	  				}
	  				
	  			});

	  		  },
	  		  Cancel:function(){
	  			  $(this).dialog("close");
	  		  }
	  	  },
		  
	  });
	  
  }