<%
//Jos kirjautumista ei ole tapahtunut l�hetet��n k�ytt�j� kirjautumissivulle
if(session.getAttribute("kayttaja")==null){	
	session.setAttribute("targetPage", request.getRequestURL());
	response.sendRedirect("index.jsp");
	return;
}
//Estet��n Back-napin toiminta uloskirjautumisen j�lkeen
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>