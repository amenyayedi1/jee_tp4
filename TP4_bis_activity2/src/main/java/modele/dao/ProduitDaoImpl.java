// modele/dao/ProduitDaoImpl.java
package modele.dao;

import modele.domaine.Produit;
import java.sql.*;

public class ProduitDaoImpl implements ProduitDaoInterface {
    private Connection connection;

    public ProduitDaoImpl() {
        connection = DBConnexion.getConnection();
    }

    @Override
    public void insertProduit(Produit produit) {
        // Méthode pour insérer un produit dans la base de données
    }
}
