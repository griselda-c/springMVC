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

<script src="<c:url value="/resources/js/autocompleate.js"/>"></script>


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


<script src="<c:url value="/resources/js/paginacion.js"/>"></script>
<script src="<c:url value="/resources/js/tablaclientes.js"/>"></script>


<title>Cliente</title>
</head>
<body>

	<div id="botones">
		<a id="agregar_btn" href="agregarCliente.html">Agregar cliente</a> <a
			id="exportar_btn" href="exportarClientes.html">Exportar clientes</a>
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



	<div id="clientes">
		<table border="1" id="tabla" class="table">
			<caption>Clientes</caption>
			<thead>
				<tr>
					<th>Id <span data-label='id' class="num icon-sort-alt-down"></span>
					</th>
					<th>Nombre y apellido <span data-type="text"
						data-label='nomCli' class="text icon-sort-alt-down"></span></th>
					<th>Localidad <span data-type="text" data-label='localidad'
						class="text icon-sort-alt-down"></span></th>
					<th>Compra Max <span data-label="compraMaxima"
						class="num icon-sort-alt-down"></span></th>
					<th>Cliente Contacto <span data-label="codCliContacto.id"
						class="num icon-sort-alt-down"></span></th>
					<th>Editar</th>
					<th>Eliminar</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<c:if test="${not empty clientes }">
					<c:forEach var="cliente" items="${clientes }">
						<tr class="ocultar">
							<td data-label="ID"><c:out value="${cliente.id }"></c:out></td>
							<td data-label="Nom. y apellido"><c:out
									value="${cliente.nomCli }"></c:out></td>
							<td data-label="Localidad"><c:out
									value="${cliente.localidad }"></c:out></td>
							<td data-label="Compra Max"><c:out
									value="${cliente.compraMaxima }"></c:out></td>
							<td data-label="contacto"><c:out
									value="${cliente.codCliContacto.id }"></c:out></td>
							<td data-label="Editar"><a
								href="editarCliente.html?clienteId=${cliente.id }"><img
									alt="editar"
									src="${pageContext.request.contextPath }/resources/img/edit-icon.png"
									width="30" height="25"></a></td>
							<td data-label="borrar"><a
								onclick="eliminarCliente('${cliente.id }')"><img
									alt="borrar"
									src="${pageContext.request.contextPath }/resources/img/eliminar-icono.png"
									width="30" height="25"></a></td>
						</tr>

					</c:forEach>

				</c:if>
			</tbody>
		</table>

		<div id="dialog-confirm" title="¿Esta seguro de borrar el cliente?">
			<p>
				<span class="ui-icon ui-icon-alert"></span>
				El cliente se borrará permanentemente y no se podrá recuperar.
				¿Esta usted seguro de borrarlo?
			</p>
		</div>


		<div id="tfoot">
			<ul class="paginacion">
				<!-- <li><a href="#">Último</a></li>
				<li><a href="#">Siguiente</a></li>
				<li><a href="#">2</a></li>
				<li><a class="activo"  href="#">1</a></li>-->

			</ul>

		</div>
	</div>

	<%--<nav aria-label="Page navigation example">
	<ul class="pagination">
		<li class="page-item"><a class="page-link" href="#">Previous</a></li>
		<li class="page-item"><a class="page-link" href="#">1</a></li>
		<li class="page-item"><a class="page-link" href="#">2</a></li>
		<li class="page-item"><a class="page-link" href="#">3</a></li>
		<li class="page-item"><a class="page-link" href="#">Next</a></li>
	</ul>
	</nav>--%>
	<input id="totalResultados" type="hidden" value='${totalResultados}' />
	
	<%--<div id="probando"></div> --%>
</body>