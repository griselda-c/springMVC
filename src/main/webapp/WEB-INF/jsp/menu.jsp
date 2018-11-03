<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- <link href="<c:url value="/resources/css/style.css" />"  rel="stylesheet"/>  --%>
<link href="${pageContext.request.contextPath}/resources/css/menu.css"
	rel="stylesheet">
	
<link href="${pageContext.request.contextPath}/resources/css/fontello.css"
	rel="stylesheet">

<title></title>
</head>
<body>

	<div id="menuInicial">
		<nav>
			<ul>
				<li><a href="#">Clientes<span class="icon-angle-down"></span></a>
					<ul>
						<li><a href="agregarCliente.html">Registrar cliente</a></li>
						<li><a href="clientes.html">Listado de clientes</a>
						<li><a href="#">Exportar clientes</a></li>
						<li><a href="#">Importar clientes</a></li>
					</ul>
				</li>
				
				<li><a href="#">Productos<span class="icon-angle-down"></span></a>
					<ul>
						<li><a href="irRegistrarProducto.html">Registrar producto</a></li>
						<li><a href="productos.html">Listado de productos</a>
						<li><a href="#">Exportar productos</a></li>
						<li><a href="#">Importar productos</a></li>
					</ul>
				</li>

			</ul>

		</nav>

	</div>

	<div id="imagen">
		<img alt="imagen"
			src="${pageContext.request.contextPath }/resources/img/industrial.jpg">
	</div>



</body>
</html>