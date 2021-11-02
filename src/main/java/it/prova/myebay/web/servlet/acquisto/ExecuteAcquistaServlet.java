package it.prova.myebay.web.servlet.acquisto;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteAcquistaServlet
 */
@WebServlet("/user/ExecuteAcquistaServlet")
public class ExecuteAcquistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		
		
		String idAnnuncioParam = request.getParameter("idAnnuncio");
		String prezzoParam = request.getParameter("prezzoAnnuncio");
		Utente utenteInstance = (Utente) httpRequest.getSession().getAttribute("userInfo");
		
	try {
		Annuncio annuncioInstance = MyServiceFactory.getAnnuncioServiceInstance()
				.caricaSingoloElemento(Long.parseLong(idAnnuncioParam));
		Integer prezzo = Integer.parseInt(prezzoParam);
		
		
		if (utenteInstance.getCreditoResiduo() - prezzo < 0) {
			request.setAttribute("dettagli_annunci_attr", annuncioInstance);
			request.setAttribute("errorMessage","Credito Insufficiente.");
			request.getRequestDispatcher("/annuncio/show.jsp").forward(request, response);
			return;
		}
		
		utenteInstance.setCreditoResiduo(utenteInstance.getCreditoResiduo() - prezzo);
		annuncioInstance.setAperto(false);
		
		Acquisto acquistoInstance = new Acquisto(annuncioInstance.getTestoAnnuncio(), new Date(),
				annuncioInstance.getPrezzo(), annuncioInstance.getUtenteInserimento());
		
		acquistoInstance.setUtenteAcquirente(utenteInstance);
		
		MyServiceFactory.getAcquistoServiceInstance().inserisciNuovo(acquistoInstance);
		MyServiceFactory.getUtenteServiceInstance().aggiorna(utenteInstance);
		
		response.sendRedirect("ExecuteVisualizzaAcquistiServlet");
		
	} catch (Exception e) {
		e.printStackTrace();
		request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
		request.getRequestDispatcher("/home").forward(request, response);
		return;
	}

	}

}
