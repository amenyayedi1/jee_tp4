package controleur;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.domaine.User;
import modele.metier.UserMetierImpl;
import modele.metier.UserMetierInterface;

@WebServlet("/UserEditionController")
public class UserEditionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// D�clarer un objet "m�tier"
	UserMetierInterface metier = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// instancier le m�tier
		metier = new UserMetierImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// tableau pour stocker les erreurs �ventuelles
		ArrayList erreurs = new ArrayList();

		// R�cup�rer les param�tres du formulaire
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String id = request.getParameter("id");

		// Contr�ler les valeurs saisies
		if (login == null || login.equals(""))
			erreurs.add("Veuillez remplir le champ login");

		if (password == null || password.equals(""))
			erreurs.add("Veuillez remplir le champ password");

		if (nom == null || nom.equals(""))
			erreurs.add("Veuillez remplir le champ nom");

		if (prenom == null || prenom.equals(""))
			erreurs.add("Veuillez remplir le champ prenom");

		if (erreurs.size() != 0) {
			// en cas d'existence de champs nulls
			request.setAttribute("err", erreurs);
			// reenvoyer les param�tres du formulaire s'ils existent sous forme
			// d'un objet 'User' pass� comme attribut nomm� "user"
			User uf = new User(0, nom, prenom, login, password);
			request.setAttribute("user", uf);

			// retourner au formulaire
			request.getRequestDispatcher("UserForm.jsp").forward(request, response);
		} else // La saisie est correcte
		{

			// lire � partir de la session ( port�e session)
			HttpSession session = request.getSession(true);
			// R�cup�rer le tableau des users de la session
			List<User> usersStore = (ArrayList<User>) session.getAttribute("listOfUsers");

			// Mode Ajout
			if (id != null && id.equals("0")) {

				// Cr�er une instance "User" en lui passant une valeur pour l'id et les champs
				// r�cup�r�s
				User u = new User(0, nom, prenom, login, password);

				// Ajouter le nouvel objet dans la base de donn�es
				metier.addUser(u);
			}
			// Mode Edition
			else {
				// Modifier l'objet en question
				User u = new User(Integer.parseInt(id), nom, prenom, login, password);
				metier.updateUser(u);

			}

			// Charger la liste des utilisateurs;
			usersStore = metier.listUsers();

			// passer le tableau dans le session
			session.setAttribute("listOfUsers", usersStore);
			// passer � la vue de liste des utilisateurs
			request.getRequestDispatcher("UserList.jsp").forward(request, response);

		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// R�cup�rer les param�tres
		String id = request.getParameter("id");
		String mode = request.getParameter("mode");

		// lire le tableau des utilisateurs stock� dans la session
		HttpSession session = request.getSession(true);
		// R�cup�rer le tableau des users de la session
		List<User> usersStore = (ArrayList<User>) session.getAttribute("listOfUsers");

		// g�rer le mode "Edition"
		if (mode != null && mode.equals("Edition")) {
			
			User u = metier.getUserById(Integer.parseInt(id));
			// passer l'objet trouv� comme attribut dans la requ�te
			request.setAttribute("user", u);
			// passer au formulaire
			request.getRequestDispatcher("UserForm.jsp").forward(request, response);
		}
		// g�rer le mode "Suppression"
		if (mode != null && mode.equals("Suppression")) {

			int index = Integer.parseInt(id);
			
			// Supprimer dans la base de donn�es
			metier.deleteUser(index);

			// Charger la liste des utilisateurs;
			usersStore = metier.listUsers();
			// Mettre � jour dans le session
			session.setAttribute("listOfUsers", usersStore);
			// passer � la liste
			request.getRequestDispatcher("UserList.jsp").forward(request, response);
		}

	}

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (u == null) {
			request.getRequestDispatcher("UserConnexion.jsp").forward(request, response);
		} 
		else {
			super.service(request, response);
		}
	}
}