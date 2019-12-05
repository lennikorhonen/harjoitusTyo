<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="scripts/main.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Insert title here</title>
</head>
<body>
<form id="tiedot">
	<table class="table">
		<thead>
			<tr>
				<th colspan="5" class="oikealle"><span id="takaisin">Takaisin listaukseen</span></th>
			</tr>
			<tr>
				<th>PeliId</th>
				<th>Nimi</th>
				<th>Koko</th>
				<th>Versio</th>
				<th>Julkaisuvuosi</th>
				<th>Jakelualusta</th>
				<th>Pelityyppi</th>			
				<th>Hallinta</th>
			</tr>
		</thead>
			<tr>
				<td><input type="text" name="peliId" id="peliId"></td>
				<td><input type="text" name="nimi" id="nimi"></td>
				<td><input type="text" name="koko" id="koko"></td>
				<td><input type="text" name="versio" id="versio"></td>
				<td><input type="text" name="julkaisuVuosi" id="julkaisuVuosi"></td>
				<td><input type="text" name="jakeluAlusta" id="jakeluAlusta"></td>
				<td><input type="text" name="peliTyyppi" id="peliTyyppi"></td>			
				<td><input type="submit" value="Tallenna" id="tallenna"></td>
			</tr>
		<tbody>
		</tbody>
	</table>
</form>
<span id="ilmo"></span>
<script>
$(document).ready(function(){
	
	$("#peliId").focus();
	
	//Haetaan muutettavan pelin tiedot
	var peliId = requestURLParam("peliId"); //Funktio löytyy scripts/main.js 	    
	$.getJSON(backPath+"ListaaPeli", {peliId: peliId}, function(result){	//Tiedot kulkevat palvelimelta GET-metodilla JSON-muodossa	
		$("#peliId").val(result.peliId);	
		$("#nimi").val(result.nimi);
		$("#koko").val(result.koko);
		$("#versio").val(result.versio);
		$("#julkaisuVuosi").val(result.julkaisuVuosi);
		$("#jakeluAlusta").val(result.jakeluAlusta);
		$("#peliTyyppi").val(result.peliTyyppi);		
    });
	
	$("#takaisin").click(function(){
		document.location="listaaPelit.jsp";
	});
		
	$("#tiedot").validate({						
		rules: {
			peliId:  {
				required: true,
				number: true,
				minlength: 1		
			},	
			nimi:  {
				required: true,
				minlength: 2				
			},
			koko:  {
				required: false,
				number: true,
				minlength: 1
			},	
			versio:  {
				required: false,
				minlength: 2,
			},
			julkaisuVuosi: {
				required: false,
				number: true,
				minlength: 4,
				maxlength: 4,
				min: 1900,
				max: new Date().getFullYear()+1 //Peli voi olla ensivuonna julkaistava
			},
			jakeluAlusta: {
				required: false,
				minlength: 2
			},
			peliTyyppi: {
				required: false,
				minlength: 2
			}
		},
		messages: {
			peliId: {
				required: "Puuttuu",
				number: "Ei kelpaa",
				minlength: "Liian lyhyt",
				min: "Liian pieni",
				max: "Liian suuri"
			},
			nimi: {     
				required: "Puuttuu",
				minlength: "Liian lyhyt"			
			},
			koko: {
				minlength: "Liian lyhyt",
				number: "Ei kelpaa"
			},
			versio: {
				minlength: "Liian lyhyt"
			},
			julkaisuVuosi: {
				number: "Ei kelpaa",
				minlength: "Liian lyhyt",
				maxlength: "Liian pitkä",
				min: "Liian pieni",
				max: "Liian suuri"
			},
			jakeluAlusta: {
				minlength: "Liian lyhyt"
			},
			peliTyyppi: {
				minlength: "Liian lyhyt"
			}
		},			
		submitHandler: function (form) {	
			vieTiedot();
		}		
	});   
});
function vieTiedot(){
	$.post(backPath+"MuutaPeli", $("#tiedot").serialize(), function(result) {  //Tiedot kulkevat palvelimelle POST-metodilla
        if(result==0){
        	$("#ilmo").jsp("Tietojen päivitys epäonnistui.");
        }else if(result==1){
			document.location="listaaPelit.jsp?peliId=" + $("peliId").val();
		}
    });
}
</script>
</body>
</html>