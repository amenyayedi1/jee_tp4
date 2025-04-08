package modele.domaine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProduitEditionController
 */
@WebServlet("/ProduitEditionController")
public class ProduitEditionController extends HttpServlet {
	private ProduitMetierImpl produitMetier = new ProduitMetierImpl();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduitEditionController() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
		// TODO Auto-generated method stub
		
		

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String code = request.getParameter("code");
	        String designation = request.getParameter("designation");
	        double prix = Double.parseDouble(request.getParameter("prix"));

	        Produit produit = new Produit(code, designation, prix);
	        produitMetier.ajouterProduit(produit);

	        response.sendRedirect("listProduits.jsp");
	}

}
