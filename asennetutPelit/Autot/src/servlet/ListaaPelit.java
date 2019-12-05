package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.Dao2;
import model.Pelit;

/**
 * Servlet implementation class ListaaPelit
 */
@WebServlet("/ListaaPelit")
public class ListaaPelit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaaPelit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Talletetaan GET viestin mukana tullut parametri (viesti). Viesti on parametrina request oliossa
		String hakusana = request.getParameter("hakuSana");
		Dao2 dao2 = new Dao2();
		// Tarkistetaan, ett‰ onko k‰ytt‰j‰ antanut hakusanaa, jos ei ole antanut annetaan sen arvoksi tyhj‰ merkki
		if (hakusana == null) {
			hakusana = "";
		}
		// Nyt voidaan sitten kutsua Dao2 luokan metodia, jotta p‰‰st‰‰n ajamaan haku tietokantaan
		ArrayList<Pelit> peli = dao2.listaaKaikki(hakusana);
		//Muutetaan ArrayList tietorakenne JSON tietorakenteeksi. Saa k‰tev‰sti l‰hetetty‰ yli Internetin
		String peliJSON = new JSONObject().put("peli", peli).toString();
		
		//Ryhdyt‰‰n luomaan tarvittavia olioita, jotta voidaan l‰hett‰‰ viesti html sivulle(JSP)
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		// L‰hetet‰‰n viestin mukana
		out.println(peliJSON);
	}

}
