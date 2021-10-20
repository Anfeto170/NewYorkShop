<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro de Clientes</title>

<script>
	function sendData() {
		var valido = validate();
		if (valido) {
			alert("Los datos son válidos")

			var IDcard = document.getElementById("txtIDcard").value.trim();
			var Name = document.getElementById("txtName").value.trim();
			var Address = document.getElementById("txtAddress").value.trim();
			var Phone = document.getElementById("txtPhone").value.trim();
			var Email = document.getElementById("txtEmail").value.trim();

			var Delivery = new XMLHttpRequest();

			var url = '/CreateCostumer';
			var Parameters = "IDcard=" + IDcard + "&" + "Name=" + Name + "&"
					+ "Address=" + Address + "&" + "Phone=" + Phone + "&"
					+ "Email=" + Email;
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
		var txtIDcard = document.getElementById("txtIDcard");
		var txtName = document.getElementById("txtName");
		var txtPhone = document.getElementById("txtPhone");

		if (txtIDcard.value.length == 0 | txtName.value.length == 0
				| txtPhone.value.length == 0) {
			alert("El IDcard, nombre y teléfono deben ser campos requeridos")
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

	<!-- El método post, hace que se ejecute get pero no muestra nada en URL -->
	<!-- Linea de código de prueba -->

	<label for="txtIDcard">ID Card: </label>
	<input type="text" id="txtIDcard" name="IDcard">
	<br>
	<br>
	<label for="txtName">Nombre: </label>
	<input type="text" id="txtName" name="Name">
	<br>
	<br>
	<label for="txtAddress">Dirección: </label>
	<input type="text" id="txtAddress" name="Address">
	<br>
	<br>
	<label for="txtPhone">Teléfono: </label>
	<input type="text" id="txtPhone" name="Phone">
	<br>
	<br>
	<label for="txtEmail">Email: </label>
	<input type="text" id="txtEmail" name="Email">


	<br>
	<br>
	<button onclick="sendData()">Validar para guardar</button>

	<br>
	<br>
	<a href="http://localhost:8080/Costumer/Menu.jsp">Menu Cliente</a>

	<br>
	<br>
	<div id="dvTabla"></div>

</body>
</html>