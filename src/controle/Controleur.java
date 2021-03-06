package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import consommation.Appel;
import metier.*;
import meserreurs.*;

/**
 * Servlet implementation class Controleur
 */
@WebServlet("/Controleur")
public class Controleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_TYPE = "action";
	private static final String LISTER_RADHERENT = "listerAdherent";
	private static final String AJOUTER_ADHERENT = "ajouterAdherent";
	private static final String INSERER_ADHERENT = "insererAdherent";
	private static final String ERROR_KEY = "messageErreur";
	private static final String ERROR_PAGE = "/erreur.jsp";
	private static final String RECHERCHER_LISTE_OEUVRE = "chercherListeOeuvre";
	private static final String RECHERCHER_OEUVRE = "rechercherOeuvre";
	private static final String SUPPRIMER_OEUVRE ="supprimerOeuvre";
	private static final String MODIFIER_OEUVRE ="modifierOeuvre";
	private static final String MODIFICATION_OEUVRE="modificationOeuvre";
	private static final String INSERER_OEUVRE = "insererOeuvre";
	private static final String AJOUTER_OEUVRE = "ajouterOeuvre";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controleur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processusTraiteRequete(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processusTraiteRequete(request, response);
	}

	protected void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionName = request.getParameter(ACTION_TYPE);
		String destinationPage = ERROR_PAGE;
		String reponse;
		// execute l'action
		if (LISTER_RADHERENT.equals(actionName)) {
			String ressource = "/Adherents";
			try {

				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource);
				Gson gson = new Gson();
				List<Adherent> json = gson.fromJson(reponse, List.class);
				request.setAttribute("mesAdherents", json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}

			destinationPage = "/listerAdherent.jsp";


		} else if (AJOUTER_ADHERENT.equals(actionName)) {

			destinationPage = "/ajouterAdherent.jsp";
		} else if (INSERER_ADHERENT.equals(actionName)) {
			try {
				Adherent unAdherent = new Adherent();
				unAdherent.setNomAdherent(request.getParameter("txtnom"));
				unAdherent.setPrenomAdherent(request.getParameter("txtprenom"));
				unAdherent.setVilleAdherent(request.getParameter("txtville"));
				String ressource = "/Adherents/ajout/" + unAdherent;
				Appel unAppel = new Appel();
				reponse = unAppel.postJson(ressource, unAdherent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("MesErreurs", e.getMessage());
				destinationPage = "/erreur.jsp";
			}
			destinationPage = "/index.jsp";
		}
		 else if (RECHERCHER_LISTE_OEUVRE.equals(actionName)) {

			String ressource = "/Oeuvres";
			try {

				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource);
				Gson gson = new Gson();
				List<Oeuvrevente> json = gson.fromJson(reponse, List.class);
				request.setAttribute("mesOeuvres", json);
				destinationPage = "/rechercherOeuvre.jsp";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}

		} else if (RECHERCHER_OEUVRE.equals(actionName)) {
			Oeuvrevente uneOeuvre = null;

			if (request.getParameter("id") != null) {
				try {
					// Oeuvres/{Id}")

					Gson gson = new Gson();
					int idoeuvre = gson.fromJson(request.getParameter("id"), Integer.class);
					String ressource = "/Oeuvres/" + idoeuvre;
					Appel unAppel = new Appel();
					reponse = unAppel.appelJson(ressource);
					Oeuvrevente json = gson.fromJson(reponse, Oeuvrevente.class);
					request.setAttribute("uneOeuvre", json);
					destinationPage = "/afficherOeuvre.jsp";

				} catch (Exception e) {

					destinationPage = "/erreur.jsp";
					request.setAttribute("MesErreurs", e.getMessage());
				}
			}
		} else if (SUPPRIMER_OEUVRE.equals(actionName)){
			try {

				String Id = request.getParameter("id");
				System.out.println(Id);
				String ressource = "/Oeuvres/supprimerOeuvre/" + Id;
				Appel unAppel = new Appel();
				unAppel.deleteJson(ressource);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("MesErreurs", e.getMessage());
				destinationPage = "/erreur.jsp";
			}
			destinationPage = "/index.jsp";

		} else if(MODIFIER_OEUVRE.equals(actionName)){
			try{
				Gson gson = new Gson();
				int idoeuvre = gson.fromJson(request.getParameter("id"), Integer.class);
				String ressource = "/Oeuvres/" + idoeuvre;
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource);
				Oeuvrevente json = gson.fromJson(reponse, Oeuvrevente.class);
				request.setAttribute("uneOeuvre", json);

			} catch (Exception e) {

			destinationPage = "/erreur.jsp";
			request.setAttribute("MesErreurs", e.getMessage());
		}
			destinationPage = "/modifierOeuvre.jsp";
		} else if(MODIFICATION_OEUVRE.equals(actionName)){

			try {
				Oeuvrevente uneOeuvre = new Oeuvrevente();
				uneOeuvre.setTitreOeuvrevente(request.getParameter("txttitre"));
				uneOeuvre.setEtatOeuvrevente(request.getParameter("txtetat"));
				uneOeuvre.setIdOeuvrevente(Integer.parseInt(request.getParameter("id")));
				uneOeuvre.setPrixOeuvrevente(Float.parseFloat(request.getParameter("txtprix")));
				String ressource = "/Oeuvres/Modifier/" + uneOeuvre;
				Appel unAppel = new Appel();
				reponse = unAppel.putJson(ressource, uneOeuvre);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("MesErreurs", e.getMessage());
				destinationPage = "/erreur.jsp";
			}
			destinationPage = "/index.jsp";
		}
		//TODO : ajouter Propriétaire
		else if (INSERER_OEUVRE.equals(actionName)) {
			try {
				Oeuvrevente uneOeuvre = new Oeuvrevente();
				System.out.println(request.getParameter("txttitre"));
				uneOeuvre.setTitreOeuvrevente(request.getParameter("txttitre"));
				uneOeuvre.setEtatOeuvrevente(request.getParameter("txtetat"));
				uneOeuvre.setPrixOeuvrevente(Float.parseFloat(request.getParameter("txtprix")));
				String ressource = "/Oeuvres/ajout/" + uneOeuvre;
				Appel unAppel = new Appel();
				reponse = unAppel.postJson(ressource, uneOeuvre);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("MesErreurs", e.getMessage());
				destinationPage = "/erreur.jsp";
			}
			destinationPage = "/index.jsp";
		} else if (AJOUTER_OEUVRE.equals(actionName)) {

			destinationPage = "/ajouterOeuvre.jsp";
		}






		else {
				String messageErreur = "[" + actionName + "] n'est pas une action valide.";
				request.setAttribute(ERROR_KEY, messageErreur);
			}
			// Redirection vers la page jsp appropriee
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
			dispatcher.forward(request, response);

		}

	}

