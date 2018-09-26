<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="<c:url value="/resources/js/main.js"/>"></script>

<title>Formulario producto</title>
</head>

<body>
	<div id="formularioProducto" >
		<form:form  method="POST" id="form"  modelAttribute="producto">

			<form:label path="nombreProducto">Nombre del producto</form:label>
			<form:input path="nombreProducto" name= "nombreProducto" id="nombreProducto"/>
			<br />

			<form:label path="stockMinimo">Stock Minimo</form:label>
			<form:input path="stockMinimo" name="stockMinimo"  id="stockMinimo"/>
			<br />

			<form:label path="oferta.precio">Precio</form:label>
			<br />
			<form:input path="oferta.precio" name="oferta.precio" id="precio"/>
			<br />

			<form:label path="oferta.fechaInicio">Fecha desde </form:label>
			<br />
			<form:input path="oferta.fechaInicio" name="oferta.fechaInicio" id="fechaInicio"></form:input>
			<br />

			<form:label path="oferta.fechaFin">Fecha hasta </form:label>
			<br />
			<form:input path="oferta.fechaFin"  name="oferta.fechaFin" id="fechaFin"></form:input>


			<input type="submit" value="Registrar" id="btn-registrar">

			<!-- PONER UN BOTONCITO PARA VER SI ES OFERTA. SI ES OFERTA CON JAVASCRIPT AGREGAR FECHADESDE FECHAHASTA -->

		</form:form>
	</div>
	
	<a href="#" id="ocultar-mostrar">Mostrar productos</a>

	<div id="tablaproductos">
		<c:if test="${not empty productos}">
			<table border="1" id="tabla">
				<tr>
					<td>Nombre Producto</td>
					<td>Stock Minimo</td>
					<td>Precio</td>
					<td>Fecha desde</td>
					<td>Fecha hasta</td>
				</tr>

				<c:forEach var="producto" items="${productos}">
					<tr>
						<td>${producto.getNombreProducto() }</td>
						<td><c:out value="${producto.stockMinimo}" /></td>
						<td><c:out value="${producto.oferta.precio }"></c:out></td>
						<td><c:out value="${producto.oferta.fechaInicio }"></c:out></td>
						<td><c:out value="${producto.oferta.fechaFin}"></c:out></td>
					</tr>
				</c:forEach>


			</table>
		</c:if>
	</div>
	<div id="resultado"></div>
</body>
</html>