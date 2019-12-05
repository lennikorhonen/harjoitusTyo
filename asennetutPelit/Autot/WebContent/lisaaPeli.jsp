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
<form action="LisaaPeli" id="tiedot">
<table>
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
			<th></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><input type="text" name="peliId" id="peliid"></td>
			<td><input type="text" name="nimi" id="nimi"></td>
			<td><input type="text" name="koko" id="koko"></td>
			<td><input type="text" name="versio" id="versio"></td>
			<td><input type="text" name="julkaisuVuosi" id="julkaisuVuosi"></td> 
			<td><input type="text" name="jakeluAlusta" id="jakeluAlusta"></td> 
			<td><input type="text" name="peliTyyppi" id="peliTyyppi"></td> 
			<td><input type="submit" id="tallenna" value="Lisää"></td>
		</tr>
	</tbody>
</table>
</form>
<span id="ilmo"></span>
<script>
$(document).ready(function(){
	
	$("#takaisin").click(function(){
		document.location="listaaPelit.jsp";
	});
		
	$("#tiedot").validate({						
		rules: {
			peliid: {
				required: true,
				number: true,
				minlength: 1,
				min: 1,
				max: 9999
			},
			nimi:  {
				required: true,
				minlength: 1				
			},	
			koko:  {
				required: false,
				number: true,
				minlength: 2			
			},
			versio:  {
				required: false,
				minlength: 1
			},	
			julkaisuVuosi:  {
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
			peliid: {
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
		submitHandler: function(form) {	
			lisaaTiedot();
		}		
	});   
	$("#peliid").focus();
});
function lisaaTiedot(){
	$.post(backPath + "LisaaPeli", $("#tiedot").serialize(), function(result) {  //Tiedot kulkevat palvelimelle POST-metodilla
        if(result==0){
        	$("#ilmo").html("Pelin lisääminen epäonnistui.");
        }else if(result==1){
			document.location="listaaPelit.jsp?peliid=" + $("#peliid").val();
		}
    });	
}
</script>

</body>
</html>