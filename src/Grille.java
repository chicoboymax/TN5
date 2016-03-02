/**
 * Nom du programme : TN5
 * Fichier : Grille.java
 * 
 * @author Maxime Drouin
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Grille {

	public static void main(String[] args){
		int[][] grille = { { 0, 6, 0, 1, 0, 4, 0, 5, 0 },
				{ 0, 0, 8, 3, 0, 5, 6, 0, 0 }, { 2, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 8, 0, 0, 4, 0, 7, 0, 0, 6 }, { 0, 0, 6, 0, 0, 0, 3, 0, 0 },
				{ 7, 0, 0, 9, 0, 1, 0, 0, 4 }, { 5, 0, 0, 0, 0, 0, 0, 0, 2 },
				{ 0, 0, 7, 2, 0, 6, 9, 0, 0 }, { 0, 4, 0, 5, 0, 8, 0, 7, 0 } };
		
		ArrayList<Integer> grid = grilleToArrayList(grille);
		
		Grille sudoku = new Grille(grid);
		sudoku.imprimerGrille();
	}
	private int[][] grid;
	private int taille;

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public Grille(ArrayList<Integer> al) {
		this.setTaille(al.size());
		this.grid = new int[taille][taille];
		int row = 0;
		int col = 0;
		for (int i : al) {
			grid[row][col] = i;
			col++;
			if (col >= taille) {
				col = 0;
				row++;
			}
		}
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public static ArrayList<Integer> grilleToArrayList(int[][] grille) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int row = 0; row < grille.length; row++) {
			for (int col = 0; col < grille[row].length; col++) {
				al.add(grille[row][col]);
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
	public void imprimerGrille() {
		final String LINE = "------------------------\n";
		StringBuilder sb = new StringBuilder();

		sb.append(LINE);
		for (int i = 0; i < grid.length; i++) {
			if (i == 3 || i == 6) {
				sb.append(LINE);
			}
			sb.append("|");
			for (int j = 0; j < grid[i].length; j += 3) {
				for (int kj = j; kj < j + 3; kj += 1) {
					if (grid[i][kj] != 0)
						sb.append(grid[i][kj]);
					else
						sb.append(" ");
					sb.append(" ");
				}
				sb.append("| ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		sb.append(LINE);
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}
}