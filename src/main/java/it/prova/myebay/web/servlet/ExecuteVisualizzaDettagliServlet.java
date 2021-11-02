package it.prova.myebay.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/ExecuteVisualizzaDettagliServlet")
public class ExecuteVisualizzaDettagliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			String idAnnuncioParam = request.getParameter("idAnnuncio");
			if (!NumberUtils.isCreatable(idAnnuncioParam)) {
				// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
				request.setAttribute("errorMessage", "Attenzione si è verificato un errore. (id)");
				request.getRequestDispatcher("home").forward(request, response);
				return;
			}

			try {
				Annuncio annuncioInstance = MyServiceFactory.getAnnuncioServiceInstance()
						.caricaSingoloElemento(Long.parseLong(idAnnuncioParam));

				if (annuncioInstance == null) {
					request.setAttribute("errorMessage", "Elemento non trovato.");
					request.getRequestDispatcher("ExecuteListRegistaServlet?operationResult=NOT_FOUND").forward(request,
							response);
					return;
				}

				request.setAttribute("show_annuncio_attr", annuncioInstance);
			} catch (Exception e) {
				// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
				e.printStackTrace();
				request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
				request.getRequestDispatcher("home").forward(request, response);
				return;
			}

			request.getRequestDispatcher("/show.jsp").forward(request, response);
		}

	}

