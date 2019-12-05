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

@WebServlet("/MuutaPeli")
public class MuutaPeli extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MuutaPeli() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MuutaPeli.doPost()");
		
		String nimi = request.getParameter("nimi");
		String versio = request.getParameter("versio");
		String jakeluAlusta = request.getParameter("jakeluAlusta");
		String peliTyyppi = request.getParameter("peliTyyppi");

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		Dao2 dao2 = new Dao2();
		
		try {
			int peliId = Integer.parseInt(request.getParameter("peliId"));
			int julkaisuVuosi = Integer.parseInt(request.getParameter("julkaisuVuosi"));
			double koko = Double.parseDouble(request.getParameter("koko"));
			Pelit peli = new Pelit(peliId, nimi, koko, versio, julkaisuVuosi, jakeluAlusta, peliTyyppi);
		
			if(dao2.muutaPeli(peli)) {
				out.println(1);  //Tietojen päivitys onnistui	
			}else {
				out.println(0);//Tietojen päivitys epäonnistui	
			}   
		} catch (Exception e) {
			out.println(0);//Tietojen päivitys epäonnistui, koska vuosi ei ollut luku
		}
	}

}
