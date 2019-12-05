<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Listaa pelit</title>
	<script src="scripts/main.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
<table id="listaus" class="table">
	<thead>
		<tr>
			<th colspan="2">&nbsp;<span id="logOut">Kirjaudu ulos</span></th>
			<th colspan="3" class="oikealle"><span id="uusiPeli">Lisää uusi peli</span></th>
		</tr>
		<tr>
			<th colspan="2" class="oikealle">Hakusana:</th>
			<th colspan="3"><input type="text" id="hakuSana"><input type="button" id="hae" value="Hae"></th>
		</tr>
		<tr>
			<th>PeliID</th>
			<th>Nimi</th>
			<th>Koko</th>
			<th>Versio</th>			
			<th>Julkaisuvuosi</th>
			<th>Jakelualusta</th>
			<th>Pelityyppi</th>
			<th>Hallinta</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
	<script>
		$(document).ready(function() {
			haeTiedot();
			$("#hakuSana").focus();
			
			//Tässä tulee käyttää document-tason kuuntelijaa, koska poista-luokan elementit on luotu jQuerylla ja suora sidonta sivulle puuttuu
			$(document).on('click','.poista', function(){ //Ajetaan poista-luokan napin painamisella
				var obj = $(this); 	
				var peliId = obj[0].id;
				if(confirm("Poista peli " + peliId + "?")){
					$.post(backPath + "PoistaPeli", {peliId: peliId}, function(result) {  //Tiedot kulkevat palvelimelle POST-metodilla
				        if(result==0){
				        	$("#ilmo").jsp("Pelin poisto epäonnistui.");
				        }else if(result==1){
				        	$("#"+peliId).css("background-color", "red"); //Värjätään poistetun pelin rivi
				        	alert("Pelin "+ peliId + " poisto onnistui.");
							document.location="listaaPelit.jsp";		        	
						}
				    });
				}
			});
		});
		
		$("#hae").click(function(){		
			haeTiedot();
		});
		
		$(document.body).on("keydown", function(event){
			  if(event.which==13){ //Enteriä painettu, ajetaan haku
				  haeTiedot();
			  }
		});
		
		function haeTiedot(){	
			$("#listaus tbody").empty();
			// {hakuSana: $("#hakuSana").val()} lähtee viestinä servletille, parametri nimi tulee olemaan hakuSana:
			$.getJSON(backPath+"ListaaPelit",{hakuSana: $("#hakuSana").val()}, function(result){	
		        $.each(result.peli, function(i, field){  
		        	var htmlStr;
		        	htmlStr+="<tr id='"+field.peliId+"'>"; //Annetaan riville id:ksi pelin ID rivin maalaamista varten 
		        	htmlStr+="<td>"+field.peliId+"</td>";
		        	htmlStr+="<td>"+field.nimi+"</td>";
		        	htmlStr+="<td>"+field.koko+"</td>";
		        	htmlStr+="<td>"+field.versio+"</td>";
		        	htmlStr+="<td>"+field.julkaisuVuosi+"</td>";
		        	htmlStr+="<td>"+field.jakeluAlusta+"</td>";
		        	htmlStr+="<td>"+field.peliTyyppi+"</td>";
		        	
		        	htmlStr+="<td><a href='muutaPeli.jsp?peliId="+field.peliId+"'>Muuta</a>&nbsp;";
		        	htmlStr+="<span class='poista' id='"+field.peliId+"'>Poista</span></td>";
		        	htmlStr+="</tr>";
		        	$("#listaus tbody").append(htmlStr);
		        });
		      	//Jos sivun kutsun yhteydessä on tullut peliId  
		        var peliId=requestURLParam("peliId"); //Funktio löytyy scripts/main.js        
		        if(peliId!=undefined){
			        $('html, body').animate({ //Rullataan peli ID:n mukaisen auton luokse
			            scrollTop: $("#"+peliId).offset().top
			        	}, 500);
			        $("#"+peliId).css("background-color", "gray"); //Värjätään pelin rivi
			        setTimeout(function(){
			        	$("#"+peliId).css("background-color","");  //Poistetaan rivin värjäys aikaviiveellä							
						}, 5000);
		        }
		    });
			$("#uusiPeli").click(function(){
				document.location="lisaaPeli.jsp";
			});
		}
	
	</script>

</body>
</html>