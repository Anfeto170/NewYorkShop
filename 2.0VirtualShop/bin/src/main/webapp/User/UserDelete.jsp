<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Eliminar Usuario</title>
<script>
	function sendData() {
		var valido = validate();
		if (valido) {
			alert("Los datos son válidos")

			var IDcard = document.getElementById("txtIDcard").value.trim();

			var Delivery = new XMLHttpRequest();

			var url = '/DeleteUser';
			var Parameters = "IDcard=" + IDcard;
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
			alert("Los datos son inválidos")
		}
		return;
	}

	function validate() {

		var txtIDcard = document.getElementById("txtIDcard");

		if (txtIDcard.value.length == 0) {
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
	<h1>Seccion de eliminar usuarios</h1>
	<label for="txtIDcard">Cédula del usuario que se va a eliminar:
	</label>
	<input type="text" id="txtIDcard" name="IDcard">
	<br>

	<br>
	<button onclick="sendData()">Eliminar Usuario</button>
	<br>
	<a href="http://localhost:8080/User/Menu.jsp">Menu Usuario</a>
	<br>
	<br>
	<div id="dvTabla"></div>

</body>
</html>
