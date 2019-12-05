<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lähetä viesti</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
<div id="lahetaViesti">
	<label>Nimi:</label><input type="text" id="nimi"><br/>
	<input type="button" id="laheta" value="Lähetä viesti">
	<label>&nbsp;</label><span id="ilmo"></span>
</div>
<div id="result"></div>
	<script>
		$(document).ready(function() {
			$("#laheta").click(function() {
				if($("#nimi").val() != "") {
					$.post("VastaanotaViesti",{nimi: $("#nimi").val()},function() {
						$("#result").html("Viesti lähetetty");
					});
				}
			});
		});
	</script>
</body>
</html>