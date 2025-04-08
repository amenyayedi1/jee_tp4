// modele/metier/ProduitMetierImpl.java
package modele.metier;

import modele.domaine.Produit;
import modele.dao.ProduitDaoInterface;
import modele.dao.ProduitDaoImpl;

public class ProduitMetierImpl {
    private ProduitDaoInterface produitDao = new ProduitDaoImpl();

    public void ajouterProduit(Produit produit) {
        produitDao.insertProduit(produit);
    }
}