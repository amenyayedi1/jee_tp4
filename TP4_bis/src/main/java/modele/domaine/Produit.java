// modele/domaine/Produit.java
package modele.domaine;

public class Produit {
    private int id;
    private String code;
    private String designation;
    private double prix;
	public Produit( String code, String designation, double prix) {
		
		this.code = code;
		this.designation = designation;
		this.prix = prix;
	}

    // Constructeurs, getters et setters
}
