<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.2.js">
	
</script>
<script type="text/javascript"
	src="http://code.jquery.com/ui/1.10.3/jquery-ui.js">
	
</script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />

<link
	href='<c:url value="/resources/css/formularioCliente.css"></c:url>'
	rel="stylesheet" />

<link href='<c:url value="/resources/css/modal.css"></c:url>'
	rel="stylesheet" />

<script src="<c:url value="/resources/js/clientes.js"/>"></script>
<script src="<c:url value="/resources/js/autocompleate.js"/>"></script>


<title>Cliente</title>
</head>
<body>

	<form:form method="POST" action="registrarCliente.html"
		modelAttribute="cliente">
		<h2>${titulo}</h2>

		<form:label path="nomCli">Nombre </form:label>
		<form:input path="nomCli" name="nomCli" required="required" />

		<form:label path="localidad">Localidad</form:label>
		<form:input path="localidad" name="localidad" required="required" />

		<form:label path="compraMaxima">Compra Max </form:label>
		<form:input path="compraMaxima" name="compraMaxima"
			required="required" />
		<div class="ui-widget">
			<form:label path="codCliContacto.nomCli">Cliente Contacto </form:label>
			<form:input path="codCliContacto.nomCli" id="buscadorCliente"
				class="nomCli" />
		</div>

		<div id="form_btn">
			<a href="clientes.html"><button type="button" class="btn"
					id="btn_cancelar">Cancelar</button></a> <input type="submit"
				value="Agregar Cliente" class="btn" id="btn_agregar" />

		</div>

	</form:form>



</body>
</html>