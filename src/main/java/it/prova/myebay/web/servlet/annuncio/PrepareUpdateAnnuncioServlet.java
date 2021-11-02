package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.service.MyServiceFactory;
@WebServlet("/user/PrepareUpdateAnnuncioServlet")

public class PrepareUpdateAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long idAnnuncio = Long.parseLong(request.getParameter("idAnnuncio"));
		
		try {
			
			if(!MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElemento(idAnnuncio).isAperto()) {
				request.setAttribute("errorMessage", "Attenzione l'annuncio è chiuso.");
				request.getRequestDispatcher("ExecuteSearchAnnunciServlet").forward(request, response);
				return;
			}
			
			//metto un bean 'vuoto' in request perché per la pagina risulta necessario
			request.setAttribute("update_annuncio_attr", MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElemento(idAnnuncio));
			// questo mi serve per la select di registi in pagina
			request.setAttribute("categorie_list_attribute",
					MyServiceFactory.getCategoriaServiceInstance().listAllElements());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/user/updateAnnuncio.jsp").forward(request, response);
	}

	

}