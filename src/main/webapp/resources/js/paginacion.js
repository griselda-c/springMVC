/**
 * 
 */

$(document).ready(function() {

	var total = $("#totalResultados").val();
	var xpag = $('#selector').val();
	var nroPag = Math.ceil(total / xpag);

	listado();
});

var paginacion = {
	listado : [], // array para filtrar buscados
	pagina : 1,
	activo : null,  //pagina activa
	nroPag : 0,
	length : 0, // length original para rearmar la tabla despues del filtrado
	botones: 3, //cantidades de botones de la paginacion

};

/* necesita el length de listado */

function build_nav(desde) {
	var i;
	//paginacion.nroPag = Math.ceil(length / $('#selector').val()); // cambia si
																	// cambia el
																	// selector
																	// val
	var nroPag = paginacion.nroPag;
	var pagina = paginacion.pagina; // pagina actual
	var hasta = nroPag;
	
	if(hasta > paginacion.botones && (desde+2) < nroPag){
		hasta = desde+2;
	}
	
	
	$("ul.paginacion").empty();

	$("ul.paginacion")
			.prepend("<li><a id='primero'>" + "Primero" + "</a></li>");
	$("ul.paginacion").prepend(
			"<li><a id='anterior' data-page=" + "anterior" + ">" + "Anterior"
					+ "</a></li>");
	
	if(desde !=1){
		page = desde - paginacion.botones;
		if(page < 1)
			page = 1
		$("ul.paginacion").prepend(
				"<li><a data-page=" + (page) + " id='volver'>"
						+ "..." + "</a></li>");
	}
	

	for (i = desde; i <= hasta; i++) {
		$("ul.paginacion").prepend(
				"<li><a data-page=" + i + " class='nav'>" + i + "</a></li>");
	}
	
	if(hasta < paginacion.nroPag){
		hasta = hasta +1;
		$("ul.paginacion").prepend(
				"<li><a data-page=" + hasta + " id='agregar'>"
						+ "..." + "</a></li>");
	}
	
		
	
	$("ul.paginacion").prepend(
			"<li><a data-page=" + "siguiente" + " id='siguiente'>"
					+ "Siguiente" + "</a></li>");
	$("ul.paginacion").prepend(
			"<li><a data-page=" + paginacion.nroPag + " id='ultimo'>" + "\u00DAltimo" + "</a></li>");

	cambioDePagina();
	agregarClassActivo($("a[data-page='" + pagina + "']"));
	anteriorSiguienteDisabled();
	navegarSig_Anterior_Extremos();
	// onchangeSelector(listado);

}

/*
 * function paginacion(nroPag) { build_nav(nroPag); }
 */

function navegarSig_Anterior_Extremos() {
	irPrimero();
	irUltimo();
	irSiguiente();
	irAnterior();
	irAgregar();
	irvolver();
}


/*function display(response) {
	$("#tbody").empty();

	$.each(response, function(i, item) {
		var botones = '<td data-label="Editar">'
				+ '<a href="editarCliente.html?clienteId=nada">'
				+ '<img alt="editar" src="resources/img/edit-icon.png"'
				+ 'width="30" height="25" />' + '</a> </td>'

		var contenido = '<td data-label="ID">' + item.id + '</td>'
				+ '<td data-label="Nom. y apellido">' + item.nomCli + '</td>'
				+ '<td data-label="Localidad">' + item.localidad + '</td>'
				+ '<td data-label="Compra Max">' + item.compraMaxima
		if (item.codCliContacto == null) {

			contenido = '<tr class="ocultar">' + contenido + '</td>'
					+ '<td data-label="contacto">' + "" + '</td>' + botones
					+ '</tr>';
		} else {
			contenido = '<tr class="ocultar">' + contenido + '</td>'
					+ '<td data-label="contacto">' + item.codCliContacto.id
					+ '</td>' + botones + '</tr>';

		};
		
		$('#tabla').last().append(contenido);

		
		 * for (i = 0; i < 5; i++) $('#tabla').last().append(contenido);
		 

	});

}*/

function listado() {
	console.log("listado");
	$.ajax({
		url : "http://localhost:8080/mvc/listado.json",
		type : "GET",
		dataType : "json",
		error : function(e) {
			alert("error " + e);
			console.log(e);
		},
		success : function(resp) {
			// paginacion.listado = resp;
			paginacion.length = resp.length;
			paginacion.nroPag = Math.ceil(resp.length / $('#selector').val());
			/*
			 * mostrarCantidad(paginacion.listado); ordenar(resp);
			 */
			buscador();
			/*display(resp);*/
			tablaFunciones();
			onchangeSelector();
			ordenar();
			calcularNroPag();
			build_nav(1);
		}
	});

}


function calcularNroPag(){
	if(paginacion.listado.length > 0)
		length = paginacion.listado.length // si no hay objetos filtrados
	else
		length = paginacion.length // todos los objetos
	
	paginacion.nroPag = Math.ceil(length / $('#selector').val());
}

function onchangeSelector() {
	$('#selector').data('before', $('#selector').val());
	$('#selector').change(function() {
		ocultarContenido($('#selector').data('before'));
		mostrarCantidad();
		$('#selector').data('before', $('#selector').val());
		calcularNroPag();
		build_nav(1);
	});
}




/*
 * function onchangeSelector(listado){ $("#selector").on('focus', function () {
 * $(this).data('before',$(this).val()); }).on('change', function () {
 * ocultarContenido($('#selector').data('before')); mostrarContenido();
 * build_nav(listado); }); }
 */
function tablaFunciones() {
	mostrarCantidad();
	// ordenar(listado);
	// onchangeSelector(listado);
}

function mostrarContenido() {
	var pagina = paginacion.pagina;
	var cantidad = $('#selector').val();
	var contenido = ((pagina - 1) * cantidad) + 1;
	var hasta = parseInt(contenido) + parseInt(cantidad);
	// var tr = $('tr');

	if ($('#search').val().length < 1) {// si no se esta filtrando algo
		for (var i = contenido; i < hasta; i++) {
			$('tr:eq(' + i + ')').removeClass('ocultar');
		}
	} else {// si es el resultado de una filtracion
		contenido = contenido - 1;
		if (hasta > paginacion.listado.length)
			hasta = paginacion.listado.length
		else
			hasta = hasta - 1;

		for (var i = contenido; i < hasta; i++) {
			tr = paginacion.listado[i] + 1;
			$('tr:eq(' + tr + ')').removeClass('ocultar');
		}

	}
}

function ocultarContenido(cantidad) {
	var pagina = paginacion.pagina;
	// var cantidad = $('#selector').val();
	var contenido = ((pagina - 1) * cantidad) + 1;
	var hasta = parseInt(contenido) + parseInt(cantidad);
	var tr = $('tr');
	
	if ($('#search').val() == "") {
		for (var i = contenido; i <= hasta; i++) {
			tr.eq(i).addClass('ocultar');
		}
	} else {
		contenido = contenido - 1;
		if (hasta > paginacion.listado.length){
			hasta = paginacion.listado.length}
		
		hasta = hasta - 1;
		for (var i = contenido; i <= hasta; i++) {
			tr = paginacion.listado[i] + 1;
			console.log("oculte index "
					+ $('tbody tr').index($('tr:eq(' + tr + ')')));
			$('tr:eq(' + tr + ')').addClass('ocultar');
		}
	}
}



/* Es llamado cuando cambia el select y cuando se incia la pagina listado() */
/*
 * vuelve a construir el nav de navegacion calcula lo que se va a ver en cada
 * pagina y lo muestra
 */
function mostrarCantidad() {
	// ocultarContenido($('#selector').val());
	mostrarContenido(); /* lista de listado a mostrar */
}

function abrirPagina(i) {
	var data = i.data("page");
	ocultarContenido($('#selector').val());
	paginacion.pagina = data;
	mostrarContenido(); // lista de listado a mostrar
	agregarClassActivo(i);
	anteriorSiguienteDisabled();
}

function cambioDePagina() {
	$('a.nav').click(function(e) {
		e.preventDefault();
		abrirPagina($(this));
	});
}

function agregarClassActivo(element) {
	if (paginacion.activo == null) {
		element.addClass("activo");
		paginacion.activo = element;
	} else {
		paginacion.activo.removeClass("activo");
		element.addClass("activo");
		paginacion.activo = element;
	}

}

function irSiguiente() {
	$('#siguiente').click(function() {
		var siguiente = parseInt(paginacion.pagina) + 1;
		if (paginacion.nroPag < siguiente) // solo un control más, xq el boton
											// se deshabilita
			siguiente = 1
		
		if(paginacion.pagina % paginacion.botones == 0){
			build_nav(siguiente);
		}
		
		abrirPagina($("a[data-page='" + siguiente + "']"));
	});

}

function irAnterior() {
	$('#anterior').click(function() {
		var anterior = parseInt(paginacion.pagina) - 1;
		
		if(paginacion.pagina % paginacion.botones == 1){
			build_nav(anterior-(paginacion.botones-1));
		}
		abrirPagina($("a[data-page='" + anterior + "']"));
	});

}

function irPrimero() {
	$('#primero').click(function() {
		build_nav(1);
		abrirPagina($("a[data-page='1']"));
	})

}

function irUltimo() {
	$('#ultimo').click(function() {
		var ultimo = paginacion.nroPag;
		abrirPagina($("a[data-page='" + ultimo + "']"));
		build_nav(ultimo-(paginacion.botones-1));
	});

}

function irAgregar(){
	$('#agregar').click(function(){
		var desde = $(this).data('page');
		abrirPagina($("a[data-page='" + desde + "']"));
		build_nav(desde);///lenght de donde lo saco???
	});
}

function irvolver(){
	$('#volver').click(function(){
		var desde = $(this).data('page');
		abrirPagina($("a[data-page='"+desde+"']"));
		build_nav(desde)
	});
}

function anteriorSiguienteDisabled() {
	if (paginacion.pagina == 1) {
		$('a[data-page="anterior"]').addClass("disabled");
	} else {
		$('a[data-page="anterior"]').removeClass("disabled");
	}

	if (paginacion.pagina == paginacion.nroPag) {
		$('a[data-page="siguiente"]').addClass("disabled");
	} else {
		$('a[data-page="siguiente"]').removeClass("disabled");
	}
}

function siguienteDisabled() {
	if (paginacion.pagina == paginacion.nroPag) {
		$('a[data-page="siguiente"]').addClass("disabled");
	} else {
		$('a[data-page="siguiente"]').removeClass("disabled");
	}
}

function sortlistado2(prop, type, asc, listado) {
	listado = listado.sort(function(a, b) {
		if (asc) {
			if (type) {
				return (a[prop] - b[prop]);
			} else
				return a[prop].toUpperCase() - b[prop].toUpperCase();

		} else {
			if (type) {
				return (b[prop] - a[prop]);
			} else
				return (b[prop].toUpperCase() - a[prop].toUpperCase());
		}

	});
}

function sortTable(thIndex, asc, text) {
	var allRows = $('tr');
	var veces = allRows.length - 2;

	while (veces >= 0) {
		for (i = 0; i < veces; i++) {
			/*
			 * var tabla_tr = document.getElementById("tabla")
			 * .getElementsByTagName("tbody")[0].rows;
			 */
			var row1 = $('tbody tr').eq(i).find('td').eq(thIndex);
			var row2 = $('tbody tr').eq(i + 1).find('td').eq(thIndex);

			var texto1 = row1.text().trim();
			var texto2 = row2.text().trim();

			if (text) {
				texto1 = texto1.toUpperCase();
				texto2 = texto2.toUpperCase();
			} else {
				texto1 = parseInt(texto1);
				texto2 = parseInt(texto2);
			}

			if (asc) {
				if (texto1 > texto2) {
					var tr = row2.parent();
					var tr_prev = tr.prev();
					tr.insertBefore(tr_prev);
					tr.addClass("ocultar");
					tr_prev.addClass("ocultar");

				}
			} else if (texto1 < texto2) {
				var tr = row2.parent();
				var tr_prev = tr.prev();
				tr.insertBefore(tr_prev);
				tr.addClass("ocultar");
				tr_prev.addClass("ocultar");
				// row2.parent().css('background-color','red');
			}

		}
		veces--;

	}
	// row.insertAfter(row.next());
}
/*
 * function sortlistado(prop, type, asc, listado) { return function(z) { if (z !=
 * undefined) { listado.sort(function(a, b) {
 * 
 * if (asc) { if (type) { return ((a[prop][z] < b[prop][z]) ? -1 : (a[prop][z] >
 * b[prop][z]) ? 1 : 0); } else return ((a[prop][z].toUpperCase() < b[prop][z])
 * .toUpperCase() ? -1 : (a[prop][z].toUpperCase() > b[prop][z] .toUpperCase()) ?
 * 1 : 0); } else { if (type) return ((a[prop][z] > b[prop][z]) ? -1 :
 * (a[prop][z] < b[prop][z]) ? 1 : 0);
 * 
 * else { return ((a[prop][z].toUpperCase() > b[prop][z]) .toUpperCase() ? -1 :
 * (a[prop][z].toUpperCase() < b[prop][z]) .toUpperCase() ? 1 : 0); } } }) }
 * else { listado .sort(function(a, b) {
 * 
 * if (asc) { if (type) { return ((a[prop] < b[prop]) ? -1 : (a[prop] > b[prop]) ?
 * 1 : 0); } else return ((a[prop].toUpperCase() < b[prop] .toUpperCase()) ? -1 :
 * (a[prop].toUpperCase() > b[prop] .toUpperCase()) ? 1 : 0); } else { if (type)
 * return ((a[prop] > b[prop]) ? -1 : (a[prop] < b[prop]) ? 1 : 0);
 * 
 * else { return ((a[prop].toUpperCase() > b[prop] .toUpperCase()) ? -1 :
 * (a[prop].toUpperCase() < b[prop] .toUpperCase()) ? 1 : 0); } } }) } } }
 */

/* ascendente return ((x < y) ? -1 : ((x > y) ? 1 : 0)); */

function ordenar() {
	$('th span').click(function(e) {
		var data = $(this).data("label").split(","); // ordenar por ...
		var asc = (!$(this).attr('asc')); // asc o des
		var text = ($(this).attr('data-type'));// es texto o numero

		$('th span').each(function() {
			$(this).removeAttr('asc');
		});

		if (asc) {
			$(this).attr('asc', 'asc');
			$(this).removeClass('icon-sort-alt-down');
			$(this).addClass('icon-sort-alt-up');
		} else {
			$(this).removeClass('icon-sort-alt-up');
			$(this).addClass('icon-sort-alt-down');
		}

		// sortlistado(data[0], text, asc, listado)(data[1]);
		sortTable($(this).parent().index(), asc, text);

		mostrarCantidad();
	});

}

function buscador() {
	$('#search').keyup(function() {
		var filter = $('#search').val().toUpperCase();
		var allTr = $('tr').length;
		var allTh = $('th').length - 1;

		var cantidad = 0;
		paginacion.listado = [];

		if (filter != "") {

			for (var i = 0; i <= allTr; i++) {

				for (var j = 0; j <= allTh; j++) {
					tr = $('tbody tr').eq(i);
					td = tr.find('td').eq(j);
					if (td) {
						if ((td.text().toUpperCase().indexOf(filter) > -1)) {
							cantidad++;
							paginacion.listado.push(i);
							if (cantidad <= $('#selector').val())
								tr.removeClass("ocultar");
							break;
						} else {
							tr.addClass("ocultar");
						}
					}
				}
			}
		} else {
			paginacion.listado = [];
			ocultarContenido(cantidad + $('#selector')); // no oculta los tr
			// que necesito
			cantidad = paginacion.length;
			mostrarCantidad();
		}
		calcularNroPag();
		build_nav(1);
		/*
		 * var filtrados = []; if (filter != "") { filtrados =
		 * filtrados.concat(filtros("nomCli", filter)); filtrados =
		 * filtrados.concat(filtros("localidad", filter)); } else filtrados =
		 * paginacion.listado;
		 * 
		 * tablaFunciones(filtrados);
		 */
		// onchangeSelector(filtrados);
		// llamados("buscador ");
	})

}
/*
 * function filtrar() { var listado = paginacion.listado; var filtrados = [];
 * var variables = [ "nomCli", "localidad" ] var filter = $('#search').val(); if
 * (listado.length > 0) { for ( var aux in variables) { filtrados =
 * filtrados.concat(filtros(aux, filter)); } } return filtrados; }
 */
/*
 * function filtros(n, filter) { var listado = paginacion.listado; var filtrados =
 * []; var exp = RegExp(filter, 'gi');
 * 
 * $.each(listado, function(i, item) { console.log("item " + item[n]); //
 * /filter/g if (item[n].match(exp)) { filtrados.push(item); }
 * 
 * }); return filtrados }
 */
var veces = 0;

function llamados(desde) {

	console.log(desde + veces++);

	$('tbody tr').click(function() {
		alert($('tbody tr').index(this));
	})
}

function keys(listado) {
	var myObj = listado[0];
	var lista = variables(myObj, []);
	console.log("lista obtenida " + lista);
}
/*
 * function variables(myObj, lista) { for (i = 0; i < Object.keys(myObj).length;
 * ++i) { if (typeof myObj[Object.keys(myObj)[i]] === 'object') {
 * variables(myObj[Object.keys(myObj)[i]], lista); } else //
 * lista.push(Object.keys(myObj)[i]) console.log(filtros(Object.keys(myObj)[i],
 * "acondicionador")); }
 * 
 * return lista; }
 */