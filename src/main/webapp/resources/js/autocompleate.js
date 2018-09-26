/**
 * 
 */

$(document).ready(function() {
	$(function() {
		$("#buscadorCliente").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : "http://localhost:8080/mvc/buscarCliente.json",
					type : "GET",
					data : {
						nomCli : $('#buscadorCliente').val()
					},

					dataType : "json",

					success : function(resp) {
						response($.map(resp, function(v, i) {
							return {
								label : v.nomCli,
								value : v.nomCli
							};
						}));
					},
					error: function(er){
						console.log("ERROR: ", er);
					}
				});
			}
		});
	});
});