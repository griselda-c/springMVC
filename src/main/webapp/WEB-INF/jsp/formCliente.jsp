<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%-- <div id="modal">
	<form:form method="POST" modelAttribute="clienteE">
		<h2>Editar Cliente</h2>

		 <form:label path="nomCli">Nombre </form:label>
		<form:input path="nomCli" name="nomCli" />

		<form:label path="localidad">Localidad</form:label>
		<form:input path="localidad" name="localidad" />

		<form:label path="compraMaxima">Compra Max </form:label>
		<form:input path="compraMaxima" name="compraMaxima" />
		<div class="ui-widget">
			<form:label path="codCliContacto.nomCli">Cliente Contacto </form:label>
			<form:input path="codCliContacto.nomCli" id="buscadorCliente"
				class="nomCli" />
		</div>

		<input type="submit" value="Editar Cliente" id="btn_agregar" />
	</form:form>

</div> --%>

<form:form method="POST" action="registrarCliente.html"
		modelAttribute="cliente">
		<h2>${titulo}</h2>

		<form:label path="nomCli">Nombre </form:label>
		<form:input path="nomCli" name="nomCli" />

		<form:label path="localidad">Localidad</form:label>
		<form:input path="localidad" name="localidad" />

		<form:label path="compraMaxima">Compra Max </form:label>
		<form:input path="compraMaxima" name="compraMaxima" />
		<div class="ui-widget">
			<form:label path="codCliContacto.nomCli">Cliente Contacto </form:label>
			<form:input path="codCliContacto.nomCli" id="buscadorCliente"
				class="nomCli" />
		</div>

		<input type="submit" value="Agregar Cliente" id="btn_agregar" />
	</form:form>
	
	
	