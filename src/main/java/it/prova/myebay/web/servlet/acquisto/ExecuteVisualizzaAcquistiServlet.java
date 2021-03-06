package it.prova.myebay.web.servlet.acquisto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/user/ExecuteVisualizzaAcquistiServlet")
public class ExecuteVisualizzaAcquistiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		try {
			
			Utente utenteExample = (Utente)httpRequest.getSession().getAttribute("userInfo");
			
			Acquisto acquistoInstance = new Acquisto(utenteExample);
			
			request.setAttribute("acquisto_list_attr", MyServiceFactory.getAcquistoServiceInstance().findByExample(acquistoInstance));
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/acquisto/visualizzaAcquisto.jsp").forward(request, response);
	}

}
