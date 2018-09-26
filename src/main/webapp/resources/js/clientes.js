/**
 * 
 */

$(document).ready(function() {

});

function listarClientes() {
	$.ajax({
		url : "http://localhost:8080/mvc/clientes.html",
		type : "POST",
		error : function(e) {
			alert("error " + e);
		},
		success : function(resp) {
			$("#tbody").html(resp);
			console.log("DONE");
		}
	});

}

function openEdit() {
	var modal = document.getElementById('modal');
	modal.style.display = "block";
}

