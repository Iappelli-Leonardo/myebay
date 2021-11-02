package it.prova.myebay.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteCercaAnnuncioServlet
 */
@WebServlet("/ExecuteCercaAnnuncioServlet")
public class ExecuteCercaAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String testoAnnuncioParam = request.getParameter("testo");
		String prezzoParam = request.getParameter("prezzo");
		String[] categoriaInputParam = request.getParameterValues("categoriaInput");
		try {
			
			Annuncio example = UtilityForm.createAnnuncioFromParams(testoAnnuncioParam, prezzoParam);
			
			if (categoriaInputParam != null) {
				for (String categoriaItem : categoriaInputParam) {
					example.getCategorie().add(MyServiceFactory.getCategoriaServiceInstance()
							.caricaSingoloElemento(Long.parseLong(categoriaItem)));
				}
			}
			
			request.setAttribute("annuncio_list_attribute", MyServiceFactory.getAnnuncioServiceInstance().findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/home").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/list.jsp").forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String testoAnnuncioParam = request.getParameter("testo");
		String prezzoParam = request.getParameter("prezzo");
		
		try {
			
			Annuncio example = UtilityForm.createAnnuncioFromParams(testoAnnuncioParam, prezzoParam);
			
			request.setAttribute("annuncio_list_attribute", MyServiceFactory.getAnnuncioServiceInstance().findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/home").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/list.jsp").forward(request, response);
	}

}
