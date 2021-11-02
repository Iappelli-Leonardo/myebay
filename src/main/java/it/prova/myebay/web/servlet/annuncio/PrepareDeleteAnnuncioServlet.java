package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareDeleteAnnuncioServlet
 */
@WebServlet("/user/PrepareDeleteAnnuncioServlet")
public class PrepareDeleteAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idAnnuncio = request.getParameter("idAnnuncio");

		if (!NumberUtils.isCreatable(idAnnuncio)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		try {
			if(!MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElemento(Long.parseLong(idAnnuncio)).isAperto()) {
				request.setAttribute("errorMessage", "Attenzione l'annuncio è chiuso.");
				request.getRequestDispatcher("ExecuteSearchAnnunciServlet").forward(request, response);
				return;
			}
			
			Annuncio annuncioInstance = MyServiceFactory.getAnnuncioServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idAnnuncio));

			if (annuncioInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteGestioneAnnunciServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}

			request.setAttribute("dettagli_annunci_attr", annuncioInstance);
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/user/deleteAnnuncio.jsp").forward(request, response);
	}

}
