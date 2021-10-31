package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.service.MyServiceFactory;

@WebServlet("/PrepareSearchAnnuncioServlet")
public class PrepareSearchAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public PrepareSearchAnnuncioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// questo mi serve per la select di registi in pagina
			request.setAttribute("annuncio_list_attribute",
					MyServiceFactory.getAnnuncioServiceInstance().listAllElements());
			request.setAttribute("categoria_list_attribute",
					MyServiceFactory.getCategoriaServiceInstance().listAllElements());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/ricerca.jsp").forward(request, response);
	}

}
