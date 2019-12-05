package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao2;
import model.Pelit;

@WebServlet("/LisaaPeli")
public class LisaaPeli extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LisaaPeli() {
		super();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LisaaPeli.doPost()");
		//Paluu viesti l‰hetet‰‰n html sivulle, siin‰ tarvitaan seuraavat rivit
		//jotta viestin l‰hetys onnistuu.
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		//Dao olio luonti
		Dao2 dao2 = new Dao2();
		Pelit peli = new Pelit();

		//JSP sivulta l‰hetetyn viestin parametrit, ensimm‰isen‰ otetaan
		//Talteen nimi parametri Java muuttujaan
		peli.setNimi(request.getParameter("nimi"));
		peli.setVersio(request.getParameter("versio"));
		peli.setJakeluAlusta(request.getParameter("jakeluAlusta"));
		peli.setPeliTyyppi(request.getParameter("peliTyyppi"));
		
		//Tyyppimuunnos on tehty poikkeuksenhallinnassa sill‰
		//K‰ytt‰j‰h‰n voisi antaa tejstuˆ numeroiden sijaan
		try {
			//Integer.parseInt( yritet‰‰n tyyppimuunnosta integeriksi
			peli.setPeliId(Integer.parseInt(request.getParameter("peliId")));
			peli.setKoko(Double.parseDouble(request.getParameter("koko")));
			peli.setJulkaisuVuosi(Integer.parseInt(request.getParameter("julkaisuVuosi")));
			if(dao2.lisaaPeli(peli)) {
				// 1 tarkoittaa true arvoa
				out.println(1); //Pelin lis‰‰minen onnistui
			}else {
				// 0 tarkoittaa false arvoa
				out.println(0); //Pelin lis‰‰minen ep‰onnistui
			}
		}catch (Exception e) {
			out.println(0); //Pelin lis‰‰minen ep‰onnistui, koska koko tai julkaisuvuosi ei ollut luku
		}
	}

}
