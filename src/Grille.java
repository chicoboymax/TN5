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
		this.setTaille(9);
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
				System.out.println("Erreur, la grille comporte déjà la valeur "
						+ valeur + " sur cette ligne.");
			}
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
				System.out.println("Erreur, la grille comporte déjà la valeur "
						+ valeur + " sur cette colonne.");
			}
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
		boolean dansLeBloc = false;
		int x1 = 3 * (ligne / 3);
		int y1 = 3 * (colonne / 3);
		int x2 = x1 + 2;
		int y2 = y1 + 2;
		for (int x = x1; x <= x2; x++) {
			for (int y = y1; y <= y2; y++) {
				if (grid[x][y] == valeur){
					dansLeBloc = true;
				System.out.println("Erreur, la grille comporte déjà la valeur "
						+ valeur + " dans le bloc représentant la case ("
						+ ligne + "," + colonne + ").");
				}
			}

		}
		return dansLeBloc;
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public boolean validerPlacement(int valeur, int ligne, int colonne) {
		boolean placementValide = true;
		if (estDansLeBloc(valeur, ligne, colonne) || estSurLigne(valeur, ligne)
				|| estSurColonne(valeur, colonne)) {
			placementValide = false;
		}
		return placementValide;
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public void imprimerGrille() {
		int[][] grid = this.grid;
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
					if (grid[i][kj] != 0)

						sb.append(grid[i][kj]);
					else

						sb.append(" ");

					sb.append(" ");

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
		imprimerGrille();
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