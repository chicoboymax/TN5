/**
 * Nom du programme : TN5
 * Fichier : Case.java
 * 
 * @author Maxime Drouin
 */


public class Case {

	private int ligne;
	private int colonne;
	private int valeur;

	
	/*********************************************************************************/
	/* Constructeur de Case
	 * 
	 * @param ligne - La ligne de la case
	 * @param colonne - La colonne de la case
	 * @param valeur - La valeur de la case
	 */
	/*********************************************************************************/
	public Case(int ligne, int colonne, int valeur) {
		this.setLigne(ligne);
		this.setColonne(colonne);
		this.setValeur(valeur);
	}

	/*********************************************************************************/
	/* Getter pour la ligne
	 * @return ligne - La ligne 
	 */
	/*********************************************************************************/
	public int getLigne() {
		return ligne;
	}

	/*********************************************************************************/
	/*
	 * Setter pour la ligne
	 * @param ligne - La ligne 
	 */
	/*********************************************************************************/
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	/*********************************************************************************/
	/*
	 * Getter pour la colonne
	 * @return colonne - La colonne 
	 */
	/*********************************************************************************/
	public int getColonne() {
		return colonne;
	}

	/*********************************************************************************/
	/*
	 * Setter pour la colonne
	 * @param colonne - La colonne 
	 */
	/*********************************************************************************/
	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	/*********************************************************************************/
	/*
	 * Getter pour la valeur
	 * @return valeur - La valeur 
	 */
	/*********************************************************************************/
	public int getValeur() {
		return valeur;
	}

	/*********************************************************************************/
	/*
	 * Setter pour la valeur
	 * @param valeur - La valeur 
	 */
	/*********************************************************************************/
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

}
