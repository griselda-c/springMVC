
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario producto</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
	integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<!-- <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
	integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
	crossorigin="anonymous"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
	integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
	crossorigin="anonymous"></script>


<script src="<c:url value="/resources/js/main.js"/>"></script>

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" />

</head>

<body>
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Editar el
					producto ${mensaje}</h5>
				<a href="irRegistrarProducto.html">
					<button type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</a>
			</div>
			<div class="modal-body">
				<form:form method="POST" id="editForm" modelAttribute="productoEdit">
					<div class="form-group">
						<form:label path="nombreProducto" class="form-control-label">Nombre del producto:</form:label>
						<form:input path="nombreProducto" class="form-control"
							name="nombreProducto" id="editNombreProducto" readonly="true" />

						<form:label path="stockMinimo" class="form-control-label">StockMinimo:</form:label>
						<form:input path="stockMinimo" class="form-control"
							name="stockMinimo" id="editStockMinimo" pattern="[0-9]{2,6}" />

						<form:label path="oferta.precio" class="form-control-label">Precio</form:label>
						<form:input path="oferta.precio" class="form-control"
							name="oferta.precio" id="editPrecio"
							pattern="^[0-9]+([,][0-9]+)?$" />

						<form:label path="oferta.fechaInicio" class="form-control-label">Fecha desde </form:label>
						<form:input path="oferta.fechaInicio" name="oferta.fechaInicio"
							id="editFechaInicio" class="form-control" />

						<form:label path="oferta.fechaFin" class="form-control-label">Fecha hasta </form:label>
						<form:input path="oferta.fechaFin" class="form-control"
							name="oferta.fechaFin" id="editFechaFin" data-date-format='yy-mm-dd'  ></form:input>


						<form:hidden path="oferta.id" name="oferta.id" />


						<div class="modal-footer">
							<a href="clientes.html"><button type="button"
									class="btn btn-secondary">Cancelar</button></a>
									 <input
								type="submit" value="Editar" class="btn btn-primary" /></input>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="modalSuccess">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Operación exitosa</h5>
					<a href="productos.html">
						<button type="button" class="close" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</a>
				</div>
				<div class="modal-body alert alert-success">
					<p>El producto ${mensaje} fue editado exitosamente</p>
				</div>
			</div>
		</div>
	</div>


</body>