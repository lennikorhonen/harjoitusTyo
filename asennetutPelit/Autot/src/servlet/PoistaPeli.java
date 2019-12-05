package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao2;


@WebServlet("/PoistaPeli")
public class PoistaPeli extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PoistaPeli() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("PoistaPeli.doPost()");
		Dao2 dao2 = new Dao2();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		System.out.println("PoistaPeli.doPost()if lause");
		String peliId = request.getParameter("peliId");
		if(dao2.poistaPeli(peliId)) {
			System.out.println("Pelin poisto onnistui");
			out.println(1); //Pelin poisto onnistui
		}else {
			System.out.println("Pelin postio epäonnistui");
			out.println(0); //Pelin poisto epäonnistui
		}
	}
}
