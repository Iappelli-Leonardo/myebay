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

/**
 * Servlet implementation class ExecuteVisualizzaDettagliAcquistoServlet
 */
@WebServlet("/user/ExecuteVisualizzaDettagliAcquistoServlet")
public class ExecuteVisualizzaDettagliAcquistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;

			// se nell'url della request è presente SUCCESS significa che devo mandare un
			// messaggio di avvenuta operazione in pagina
			Utente utenteExample = (Utente)httpRequest.getSession().getAttribute("userInfo");
			Acquisto example = new Acquisto(utenteExample);
			request.setAttribute("acquisti_list_attribute",
					MyServiceFactory.getAcquistoServiceInstance().findByExample(example));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/home").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/acquisto/dettagliAcquisto.jsp").forward(request, response);
	}

}
