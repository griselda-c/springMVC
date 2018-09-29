
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.2.js">
	
</script>
<script type="text/javascript"
	src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />

<script src='<c:url value="resources/js/main.js"></c:url>'></script>
<script src="<c:url value="/resources/js/autocompleate.js"/>"></script>
<script src="<c:url value="/resources/js/paginacion.js"/>"></script>
<script src="<c:url value="/resources/js/tablaproductos.js"/>"></script>


<link
	href='<c:url value="/resources/css/formularioCliente.css"></c:url>'
	rel="stylesheet" />

<link href='<c:url value="/resources/css/table.css"></c:url>'
	rel="stylesheet" />
<link href='<c:url value="/resources/css/modal.css"></c:url>'
	rel="stylesheet" />

<link
	href="${pageContext.request.contextPath}/resources/css/fontello.css"
	rel="stylesheet">


<title>Productos</title>
</head>
<body>
	<div id="botones">
		<a id="agregar_btn" href="irRegistrarProducto.html">Agregar
			producto</a> <a id="exportar_btn" href="exportarClientes.html">Exportar
			productos</a>
	</div>

	<div id="buscador">
		<span class="icon-search"></span> <input id="search"></input>
		<div id="selectPag">
			mostrar <select id="selector">
				<option value="2">2</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
			</select> resultados

		</div>

	</div>

	<div id="tablaproductos">
		<table border="1" id="tabla" class="table">
			<caption>Productos</caption>

			<thead>
				<tr>
					<th>Nombre Producto <span data-type="text"
						data-label='nombreProducto' class="text icon-sort-alt-down"></span></th>
					<th>Stock Minimo <span data-label='stockMinimo'
						class="num icon-sort-alt-down"></span></th>
					<th>Precio <span data-label="oferta,precio"
						class="num icon-sort-alt-down"></span></th>
					<th>Fecha desde <span data-label='oferta,fechaInicio'
						class="num icon-sort-alt-down"></span></th>
					<th>Fecha hasta <span data-label='oferta,fechaFin'
						class="num icon-sort-alt-down"></span></th>
					<th>Editar</th>
					<th>Eliminar</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:if test="${not empty listado}">
					<c:forEach var="producto" items="${listado}">
						<tr class="ocultar">
							<td data-label="Nombre Prod."><c:out
									value="${producto.nombreProducto }"></c:out></td>
							<td data-label="Stock min."><c:out
									value="${producto.stockMinimo}" /></td>
							<td data-label="Precio"><c:out
									value="${producto.oferta.precio }"></c:out></td>
							<td data-label="Fecha inicio"><c:out
									value="${producto.oferta.fechaInicio }"></c:out></td>
							<td data-label="Fecha hasta"><c:out
									value="${producto.oferta.fechaFin}"></c:out></td>


							<td data-label="Editar">
								<%--<a 
				onclick="abrirModal('${producto.nombreProducto }','${producto.stockMinimo }','${producto.oferta.precio }',
				'${producto.oferta.fechaInicio }','${producto.oferta.fechaFin }','${producto}')"><img
					alt="editar"
					src="${pageContext.request.contextPath }/resources/img/edit-icon.png"
					width="30" height="25"></a> --%> <a
								href="abrirModal.html?productoId=${producto.nombreProducto }"><img
									alt="editar"
									src="${pageContext.request.contextPath }/resources/img/edit-icon.png"
									width="30" height="25"></a>
							</td>
							<td data-label="Eliminar"><a
								onclick="eliminarProducto('${producto.nombreProducto }')"><img
									alt="eliminar"
									src="${pageContext.request.contextPath}/resources/img/eliminar-icono.png"></a></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		
		<div id="dialog-confirm" title="¿Esta seguro de borrar el producto?">
			<p>
				<span class="ui-icon ui-icon-alert"></span>
				El producto se borrará permanentemente y no se podrá recuperar.
				¿Esta usted seguro de borrarlo?
			</p>
		</div>

		<div id="tfoot">
			<ul class="paginacion">

			</ul>

		</div>

	</div>
	
	<input id="totalResultados" type="hidden" value='${totalResultados}' />

</body>
</html>