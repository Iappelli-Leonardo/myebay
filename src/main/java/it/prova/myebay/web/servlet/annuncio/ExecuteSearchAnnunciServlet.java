package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;

@WebServlet("/ExecuteSearchAnnunciServlet")
public class ExecuteSearchAnnunciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String testoAnnuncioParam = request.getParameter("testo");
		String prezzoParam = request.getParameter("prezzo");
		//String[] categoriaInputParam = request.getParameterValues("categoriaInput");

		try {
			
			Annuncio example = UtilityForm.createAnnuncioFromParams(testoAnnuncioParam, prezzoParam);
			request.setAttribute("annuncio_list_attribute", MyServiceFactory.getAnnuncioServiceInstance().findByExample(example));
			System.out.println(MyServiceFactory.getAnnuncioServiceInstance().findByExample(example).size());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/home").forward(request, response);
			return;
		}
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String testoAnnuncioParam = request.getParameter("testo");
		String prezzoParam = request.getParameter("prezzo");
		//String[] categoriaInputParam = request.getParameterValues("categoriaInput");
		
		
		
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			
			Utente utenteExample = (Utente)httpRequest.getSession().getAttribute("userInfo");
			
			Annuncio example = UtilityForm.createAnnuncioFromParams(testoAnnuncioParam, prezzoParam);
			
			example.setUtenteInserimento(utenteExample);
			
			request.setAttribute("annuncio_list_attribute", MyServiceFactory.getAnnuncioServiceInstance().findByExample(example));
			System.out.println(MyServiceFactory.getAnnuncioServiceInstance().findByExample(example).size());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/home").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/list.jsp").forward(request, response);
	}

}