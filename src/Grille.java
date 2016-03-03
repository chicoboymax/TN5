/**
 * Nom du programme : TN5
 * Fichier : Grille.java
 * 
 * @author Maxime Drouin
 */

import java.util.ArrayList;

public class Grille {

	private int[][] grid;
	private int taille;


	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public Grille(ArrayList<Case> al) {
		this.setTaille(al.size());
		this.grid = new int[taille][taille];
		for (Case i : al) {
			int ligne = i.getLigne();
			int colonne = i.getColonne();
			int valeur = i.getValeur();
			grid[ligne][colonne] = valeur;

		}
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public static ArrayList<Case> grilleToArrayList(int[][] grille) {
		ArrayList<Case> al = new ArrayList<>();
		for (int ligne = 0; ligne < grille.length; ligne++) {
			for (int colonne = 0; colonne < grille[ligne].length; colonne++) {
				Case placement = new Case(ligne, colonne,
						grille[ligne][colonne]);
				al.add(placement);
			}
		}
		return al;
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public boolean estSurLigne(int valeur, int ligne) {
		boolean surLigne = false;
		for (int i = 0; i < grid[ligne].length; i++) {
			if (grid[ligne][i] == valeur) {
				surLigne = true;
			}
		}
		if (surLigne == true) {
			System.out.println("La valeur est sur la ligne.");
		}
		return surLigne;

	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public boolean estSurColonne(int valeur, int colonne) {
		boolean surColonne = false;
		for (int i = 0; i < grid[colonne].length; i++) {
			if (grid[i][colonne] == valeur) {
				surColonne = true;
			}
		}
		if (surColonne == true) {
			System.out.println("La valeur est sur la colonne.");
		}
		return surColonne;
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public boolean estDansLeBloc(int valeur, int ligne, int colonne) {
		return grid[ligne][colonne] == valeur;
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public boolean validerPlacement(int valeur, int ligne, int colonne) {
		if (estSurLigne(valeur, ligne) || estSurColonne(valeur, colonne)
				|| estDansLeBloc(valeur, ligne, colonne)) {
			return false;
		} else {
			return true;
		}
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public void imprimerGrille() {
		final String ligne = "------------------------\n";
		StringBuilder sb = new StringBuilder();
		sb.append(ligne);
		for (int i = 0; i < grid.length; i++) {
			if (i == 3 || i == 6) {
				sb.append(ligne);
			}
			if (i < 9) {
				sb.append("|");
			}
			for (int j = 0; j < grid[i].length; j += 3) {
				for (int kj = j; kj < j + 3; kj += 1) {
					if (grid[i][kj] != 0) {
						sb.append(grid[i][kj]);
					} else {
						sb.append(" ");
						sb.append(" ");
					}
				}
				if (j < 9) {
					sb.append("| ");
				}
			}
			if (i < 9) {
				sb.append("\n");
			}
			if (i == 8) {
				sb.append(ligne);
			}
		}

		System.out.println(sb);
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/

	public void fairePlacement(int valeur, int ligne, int colonne) {
		if (validerPlacement(valeur, ligne, colonne)) {
			this.grid[ligne][colonne] = valeur;
		}
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/

	public int getTaille() {
		return taille;
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public void setTaille(int taille) {
		this.taille = taille;
	}

}