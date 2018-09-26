/**
 * 
 */

function listado() {
	$.ajax({
		url : "http://localhost:8080/mvc/listadoProductos.json",
		type : "GET",
		dataType : "json",
		error : function(e) {
			alert("error " + e);
			console.log(e);
		},
		success : function(resp) {
			paginacion.length = resp.length;
			paginacion.nroPag = Math.ceil(resp.length / $('#selector').val());
			buscador();
			//display(resp);
			tablaFunciones();
			onchangeSelector();
			ordenar();
			calcularNroPag(resp.length);
			build_nav(1);
			//keys(resp);
		}
	});

}

function display(response) {
	//El tr tiene clase ocultar para hacer el efecto del nav
	$("#tbody").empty();

	$.each(response, function(i, item) {
		var botones = '<td data-label="Editar">'
				+ '<a href="abrirModal.html?productoId='+item.nombreProducto+'">'
				+ '<img alt="editar" src="resources/img/edit-icon.png"'
				+ 'width="30" height="25" />' + '</a> </td>';
				
		var botonEliminar = '<td data-label="eliminar">'
			+ '<a onclick="eliminarProducto('+item.nombreProducto+')">'
			+ '<img alt="eliminar" src="resources/img/eliminar-icono.png"'
			+ 'width="30" height="25" />' + '</a> </td>'

		var contenido = '<td data-label="Nombre">' + item.nombreProducto
				+ '</td>' + '<td data-label="stock min.">' + item.stockMinimo
				+ '</td>' + '<td data-label="precio">' + item.oferta.precio
				+ '</td>' + '<td data-label="fecha inic">'
				+ item.oferta.fechaInicio + '<td data-label="fecha fin">'
				+ item.oferta.fechaFin

		contenido = '<tr class="ocultar">' + contenido + '</td>' + botones + botonEliminar + '</tr>';
		;

		$('#tabla').last().append(

		contenido);

	});
}

