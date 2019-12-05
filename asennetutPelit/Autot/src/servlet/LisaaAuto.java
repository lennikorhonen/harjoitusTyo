package servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import model.Auto;

@WebServlet("/LisaaAuto")
public class LisaaAuto extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public LisaaAuto() {
        super();
        System.out.println("LisaaAuto.LisaaAuto()");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LisaaAuto.doGet()");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LisaaAuto.doPost()");
		PrintWriter out = response.getWriter(  );
	    response.setContentType("text/html"); 
		String sessionId=request.getParameter("sessionId");
		Dao dao = new Dao();
		if(dao.etsiIstunto(sessionId)) { //Jos tietoja l‰hett‰v‰n sivuston sessionid lˆytyy tietokannasta
			Auto auto = new Auto();
			auto.setRekNo(request.getParameter("rekNo"));
			auto.setMerkki(request.getParameter("merkki"));
			auto.setMalli(request.getParameter("malli"));			
		    try {
		    	auto.setVuosi(Integer.parseInt(request.getParameter("vuosi")));				
				if(dao.lisaaAuto(auto)){
					out.println(1);  //Auton lis‰‰minen onnistui
				}else{
					out.println(0);  //Auton lis‰‰minen ep‰onnistui
				}
			} catch (Exception e) {
				out.println(0);  //Auton lis‰‰minen ep‰onnistui, koska vuosi ei ollut luku
			}
		}else {
			out.println(0);  //Auton lis‰‰minen ep‰onnistui, koska session ei ollut voimassa
		}
		
	}

}










