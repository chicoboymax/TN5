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
	 * @param al - Un ArrayList contenant les coordonnées et valeurs des cases.
	 */
	/*********************************************************************************/
	public Grille(ArrayList<Case> al) {
		this.grid = new int[TAILLE][TAILLE];
		for (Case i : al) {
			int ligne = i.getLigne();
			int colonne = i.getColonne();
			int valeur = i.getValeur();
			grid[ligne][colonne] = valeur;
		}
	}

	/*********************************************************************************/
	/* Permet de convertir une grille de Sudoku en ArrayList.
	 * 
	 * @param grille - Une grille de sudoku.
	 * @return al - Un ArrayList contenant les coordonnées et valeurs des cases.
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
	/*Vérifie si la valeur est déjà présente sur la ligne de la grille.
	 * @param valeur - La valeur que l'on veut vérifier.
	 * @param ligne - La ligne sur laquelle on veut effectuer la vérification.
	 * @return boolean - Return true si la valeur est sur la ligne.
	 */
	/*********************************************************************************/
	public boolean estSurLigne(int valeur, int ligne) {
		boolean surLigne = false;
		for (int i = 0; i < grid[ligne].length; i++) {
			if (grid[ligne][i] == valeur) {
				surLigne = true;
				ligne +=1;
				System.out.println("Erreur, la grille comporte déjà la valeur "
						+ valeur + " sur cette ligne.");
			}
		}
		return surLigne;
	}

	/*********************************************************************************/
	/*
	 * Vérifie si la valeur est déjà présente sur la colonne de la grille.
	 * @param valeur - La valeur que l'on veut vérifier.
	 * @param colonne - La colonne sur laquelle on veut effectuer la vérification.
	 * @return boolean - Return true si la valeur est sur la colonne.
	 * 
	 */
	/*********************************************************************************/
	public boolean estSurColonne(int valeur, int colonne) {
		boolean surColonne = false;
		for (int i = 0; i < grid[colonne].length; i++) {
			if (grid[i][colonne] == valeur) {
				surColonne = true;
				colonne+=1;
				System.out.println("Erreur, la grille comporte déjà la valeur "
						+ valeur + " sur cette colonne.");
			}
		}
		return surColonne;
	}

	/*********************************************************************************/
	/*
	 * Vérifie si la valeur est déjà présente dans le bloc de la grille.
	 * @param valeur - La valeur que l'on veut vérifier.
	 * @param ligne - La ligne sur laquelle est située la case.
	 * @param colonne - La colonne sur laquelle est située la case.
	 * @return boolean - Return true si la valeur est sur la ligne.
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
					ligne +=1;
					colonne+=1;
				System.out.println("Erreur, la grille comporte déjà la valeur "
						+ valeur + " dans le bloc représentant la case ("
						+ ligne + "," + colonne + ").");
				}
			}

		}
		return dansLeBloc;
	}

	/*********************************************************************************/
	/* Permet de valider le placement en appellant toutes les méthodes de vérification.
	 * @param valeur - La valeur à vérifier.
	 * @param ligne - La ligne sur laquelle est située la case.
	 * @param colonne - La colonne sur laquelle est située la case.
	 * @return boolean - Retourne false si le placement n'est pas valide.
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
	/* Imprime la grille au format demandé en utlisant un StringBuilder.
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
	/* Effectue le placement demandé dans la grille après avoir effectué la validation
	 * @param valeur - La valeur que l'on veut placer dans la grille.
	 * @param ligne - La ligne sur laquelle on veut placer la valeur.
	 * @param colonne - La colonne sur laquelle on veut placer la valeur. 
	 */
	/*********************************************************************************/

	public void fairePlacement(int valeur, int ligne, int colonne) {
		ligne -=1;
		colonne-=1;
		if (validerPlacement(valeur, ligne, colonne)) {
			this.grid[ligne][colonne] = valeur;
			ligne +=1;
			colonne+=1;
			System.out.println("Bravo! La valeur " + valeur + " a été insérée dans la case (" + ligne + "," + colonne + ").");
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