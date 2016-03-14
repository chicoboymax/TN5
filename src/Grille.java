/**
 * Nom du programme : TN5
 * Fichier : Grille.java
 * 
 * @author Maxime Drouin
 */

import java.util.ArrayList;

public class Grille {

	private int[][] grid;

	final int TAILLE = 9;

	/*********************************************************************************/
	/*
	 * Constructeur de grille.
	 * 
	 * @param al - Un ArrayList contenant les coordonnées et valeurs des cases.
	 */
	/*********************************************************************************/
	public Grille(ArrayList<Case> al) {
		this.grid = new int[TAILLE][TAILLE];
		// Pour chaque entrée de l'arrayList assigne les valeurs de la case dans
		// la grille.
		for (Case i : al) {
			int ligne = i.getLigne();
			int colonne = i.getColonne();
			int valeur = i.getValeur();
			grid[ligne][colonne] = valeur;
		}
	}

	/*********************************************************************************/
	/*
	 * Permet de convertir une grille de Sudoku en ArrayList.
	 * 
	 * @param grille - Une grille de sudoku.
	 * 
	 * @return al - Un ArrayList contenant les coordonnées et valeurs des cases.
	 */
	/*********************************************************************************/
	public static ArrayList<Case> grilleToArrayList(int[][] grille) {
		ArrayList<Case> al = new ArrayList<>();
		// Pour chaque case de la grille créer une instance de case et l'ajouter
		// à l'ArrayList.
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
	 * Vérifie si la valeur est déjà présente sur la ligne de la grille.
	 * 
	 * @param valeur - La valeur que l'on veut vérifier.
	 * 
	 * @param ligne - La ligne sur laquelle on veut effectuer la vérification.
	 * 
	 * @return boolean - Return true si la valeur est sur la ligne.
	 */
	/*********************************************************************************/
	public boolean estSurLigne(int valeur, int ligne) {
		boolean surLigne = false;
		for (int i = 0; i < grid[ligne].length; i++) {
			if (grid[ligne][i] == valeur) {
				surLigne = true;
				ligne += 1;
				System.out.println("Erreur, la grille comporte déjà la valeur "
						+ valeur + " sur la ligne " + ligne);
			}
		}
		return surLigne;
	}

	/*********************************************************************************/
	/*
	 * Vérifie si la valeur est déjà présente sur la colonne de la grille.
	 * 
	 * @param valeur - La valeur que l'on veut vérifier.
	 * 
	 * @param colonne - La colonne sur laquelle on veut effectuer la
	 * vérification.
	 * 
	 * @return boolean - Return true si la valeur est sur la colonne.
	 */
	/*********************************************************************************/
	public boolean estSurColonne(int valeur, int colonne) {
		boolean surColonne = false;
		for (int i = 0; i < grid[colonne].length; i++) {
			if (grid[i][colonne] == valeur) {
				surColonne = true;
				colonne += 1;
				System.out.println("Erreur, la grille comporte déjà la valeur "
						+ valeur + " sur la colonne " + colonne);
			}
		}
		return surColonne;
	}

	/*********************************************************************************/
	/*
	 * Vérifie si la valeur est déjà présente dans le bloc de la grille.
	 * 
	 * @param valeur - La valeur que l'on veut vérifier.
	 * 
	 * @param ligne - La ligne sur laquelle est située la case.
	 * 
	 * @param colonne - La colonne sur laquelle est située la case.
	 * 
	 * @return boolean - Return true si la valeur est sur la ligne.
	 */
	/*********************************************************************************/
	public boolean estDansLeBloc(int valeur, int ligne, int colonne) {
		boolean dansLeBloc = false;
		// Détermine le début de la région en divisant les valeurs des lignes et
		// colonnes par 3 et ensuite en les multipliants par 3.
		int x1 = 3 * (ligne / 3);
		int y1 = 3 * (colonne / 3);
		// Détermine la fin des régions en ajoutant 2 aux valeurs obtenues.
		int x2 = x1 + 2;
		int y2 = y1 + 2;
		// Vérifier si la valeur est dans la région.
		for (int x = x1; x <= x2; x++) {
			for (int y = y1; y <= y2; y++) {
				if (grid[x][y] == valeur) {
					dansLeBloc = true;
					ligne += 1;
					colonne += 1;
					System.out
							.println("Erreur, la grille comporte déjà la valeur "
									+ valeur
									+ " dans le bloc représentant la case ("
									+ ligne + "," + colonne + ").");
				}
			}

		}
		return dansLeBloc;
	}

	/*********************************************************************************/
	/*
	 * Permet de valider le placement en appellant toutes les méthodes de
	 * vérification.
	 * 
	 * @param valeur - La valeur à vérifier.
	 * 
	 * @param ligne - La ligne sur laquelle est située la case.
	 * 
	 * @param colonne - La colonne sur laquelle est située la case.
	 * 
	 * @return boolean - Retourne false si le placement n'est pas valide.
	 */
	/*********************************************************************************/
	public boolean validerPlacement(int valeur, int ligne, int colonne) {
		boolean placementValide = true;
		// Si une des trois validation est vrai le placement n'est pas valide
		if (estDansLeBloc(valeur, ligne, colonne) || estSurLigne(valeur, ligne)
				|| estSurColonne(valeur, colonne)) {
			placementValide = false;
		}
		return placementValide;
	}

	/*********************************************************************************/
	/*
	 * Imprime la grille au format demandé en utlisant un StringBuilder.
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
	 * Effectue le placement demandé dans la grille après avoir effectué la
	 * validation
	 * 
	 * @param valeur - La valeur que l'on veut placer dans la grille.
	 * 
	 * @param ligne - La ligne sur laquelle on veut placer la valeur.
	 * 
	 * @param colonne - La colonne sur laquelle on veut placer la valeur.
	 */
	/*********************************************************************************/

	public void fairePlacement(int valeur, int ligne, int colonne) {
		// Décrémente les lignes et colonnes pour la validation.
		ligne -= 1;
		colonne -= 1;
		// Appelle validerPlacement pour valider le placement.
		if (validerPlacement(valeur, ligne, colonne)) {
			// Assgine la valeur à la case de la grille
			this.grid[ligne][colonne] = valeur;
			// Incrémente les lignes et colonnes pour l'affichage.
			ligne += 1;
			colonne += 1;
			System.out.println("Bravo! La valeur " + valeur
					+ " a été insérée dans la case (" + ligne + "," + colonne
					+ ").");
		}

	}

	/*********************************************************************************/
	/*
	 * Getter pour la grille de sudoku.
	 * 
	 * @return grid - La grille de sudoku.
	 */
	/*********************************************************************************/
	public int[][] getGrid() {
		return grid;
	}

	/*********************************************************************************/
	/*
	 * Setter pour la grille.
	 * 
	 * @param grille - La grille.
	 */
	/*********************************************************************************/
	public void setGrid(int[][] grid) {
		this.grid = grid;
	}
}