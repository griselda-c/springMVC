<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>  --%>
<!-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
<%-- <script src="<c:url value="/resources/js/main.js"/>"></script> --%>

<jsp:include page="cabecera.jsp"></jsp:include>


<title>Formulario producto</title>
</head>

<body>
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Ingresar un
					nuevo Producto</h5>
			</div>

			<div id="formularioProducto" class="modal-body">
				<form:form method="POST" id="form" modelAttribute="producto">
					<div>
						<form:label path="nombreProducto">Nombre del producto</form:label>
						<form:input path="nombreProducto" name="nombreProducto"
							id="nombreProducto" class="form-control" />

						<form:label path="stockMinimo">Stock Minimo</form:label>
						<form:input path="stockMinimo" name="stockMinimo" id="stockMinimo"
							class="form-control" />

						<form:label path="oferta.precio">Precio</form:label>
						<br />
						<form:input path="oferta.precio" name="oferta.precio" id="precio"
							class="form-control" />

						<form:label path="oferta.fechaInicio">Fecha desde </form:label>
						<form:input path="oferta.fechaInicio" name="oferta.fechaInicio"
							id="fechaInicio" class="form-control"></form:input>

						<form:label path="oferta.fechaFin">Fecha hasta </form:label>
						<form:input path="oferta.fechaFin" name="oferta.fechaFin"
							id="fechaFin" class="form-control"></form:input>

						<div class="modal-footer">
							<input type="submit" value="Registrar" id="btn-registrar"
								class="btn btn-primary">
						</div>
						<!-- PONER UN BOTONCITO PARA VER SI ES OFERTA. SI ES OFERTA CON JAVASCRIPT AGREGAR FECHADESDE FECHAHASTA -->
					</div>
				</form:form>
			</div>
		</div>
	</div>


<%-- 	<a href="#" id="ocultar-mostrar">Ocultar productos</a>

	<div id="tablaproductos">
		<table border="1" id="tabla" class="table">
			<caption>Productos</caption>
			
			<thead>
				<tr>
					<th>Nombre Producto</th>
					<th>Stock Minimo</th>
					<th>Precio</th>
					<th>Fecha desde</th>
					<th>Fecha hasta</th>
					<th>Eliminar</th>
					<th>Editar</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<jsp:include page="productos.jsp" />
			</tbody>
		</table>
	</div> --%>
	<%-- <div id="resultado"><jsp:include page="editModal.jsp"></jsp:include></div> --%>
</body>
</html>