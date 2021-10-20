<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro de Usuarios</title>

<script>
	function sendData() {
		var valido = validate();
		if (valido) {
			alert("Los datos son válidos")

			var IDcard = document.getElementById("txtIDcard").value.trim();
			var Name = document.getElementById("txtName").value.trim();
			var Email = document.getElementById("txtEmail").value.trim();
			var User = document.getElementById("txtUser").value.trim();
			var Password = document.getElementById("txtPassword").value.trim();

			var Delivery = new XMLHttpRequest();

			var url = '/CreateUser';
			var Parameters = "IDcard=" + IDcard + "&" + "Name=" + Name + "&"
					+ "Email=" + Email + "&" + "User=" + User + "&"
					+ "Password=" + Password;
			Delivery.open('POST', url, true);

			//Send the proper header information along with the request
			Delivery.setRequestHeader('Content-type',
					'application/x-www-form-urlencoded');

			Delivery.onreadystatechange = function() {//Call a function when the state changes.
				if (Delivery.readyState == 4 && Delivery.status == 200) {
					// Lanza una alerta de que se mandaron los datos
					// alert(Delivery.responseText);
					var myDiv = document.getElementById("dvTabla");
					myDiv.innerHTML = "<b>" + Delivery.responseText + "</b>";
				}
			}
			Delivery.send(Parameters);

		} else {
			alert("Los NO datos son válidos")
		}
		return;
	}

	function validate() {
		var txtUser = document.getElementById("txtUser");
		var txtPassword = document.getElementById("txtPassword");
		var txtIDcard = document.getElementById("txtIDcard");

		if (txtUser.value.length == 0 | txtPassword.value.length == 0 | txtIDcard.value.length == 0) {
			alert("La cédula, usuario y contraseña deben ser cambios requeridos")
			return false;
		}

		else {
			return true;
		}
	}
</script>

</head>
<body>
	<h1>Tienda Virtual</h1>

	<!-- El método post, hace que se ejectu get pero no muestra nada en URL -->
	<label for="txtIDcard">Cédula: </label>
	<input type="text" id="txtIDcard" name="IDcard">
	<br>
	<br>
	<label for="txtName">Nombre: </label>
	<input type="text" id="txtName" name="Name">
	<br>
	<br>
	<label for="txtEmail">Email: </label>
	<input type="text" id="txtEmail" name="Email">
	<br>
	<br>
	<label for="txtUser">Usuario: </label>
	<input type="text" id="txtUser" name="User">
	<br>
	<br>
	<label for="txtPassword">Contraseña: </label>
	<input type="password" id="txtPassword" name="Password">

	<br>
	<br>
	<button onclick="sendData()">Validar para guardar</button>

	<br>
	<br>
	<a href="http://localhost:8080/User/Menu.jsp">Menu Usuario</a>
	<br>
	<br>
	<div id="dvTabla"></div>

</body>
</html>