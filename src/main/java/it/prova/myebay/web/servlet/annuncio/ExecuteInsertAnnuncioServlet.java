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

@WebServlet("/ExecuteInsertAnnuncioServlet")
public class ExecuteInsertAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		// estraggo input
		String testoAnnuncioParam = request.getParameter("testoAnnuncio");
		String prezzoParam = request.getParameter("prezzo");
		String[] CategorieParam = request.getParameterValues("categoriaItem");
		
		Annuncio annuncioInstance = UtilityForm.createAnnuncioFromParams(testoAnnuncioParam, prezzoParam, CategorieParam);
		Utente utenteExample = (Utente)httpRequest.getSession().getAttribute("userInfo");
		
		//ora il mio annuncio ha un testo, un prezzo, una data e un booleano tramite il metodo.
		
		annuncioInstance.setUtenteInserimento(utenteExample);
		
		try {
			// se la validazione non risulta ok
			if (!UtilityForm.validateAnnuncioBean(annuncioInstance)) {
				request.setAttribute("insert_annuncio_attr", annuncioInstance);
			
				// questo mi serve per la select di ruoli in pagina
			 	request.setAttribute("categorie_list_attribute",
						MyServiceFactory.getCategoriaServiceInstance().listAllElements());
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/annuncio/insert.jsp").forward(request, response);
				return;
			}
		
			// occupiamoci delle operazioni di business
			MyServiceFactory.getAnnuncioServiceInstance().inserisciNuovoConCategorie(annuncioInstance, CategorieParam);
	
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/annuncio/list.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		// uso il sendRedirect con parametro per evitare il problema del double save on
		// refresh
		response.sendRedirect("/annuncio/list.jsp");
		
	}

}
