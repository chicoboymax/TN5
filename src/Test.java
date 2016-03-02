import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Nom du programme : TN5 Fichier : Test.java
 * 
 * @author Maxime Drouin
 */

public class Test {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		validerChoix(1, 2, 3);

	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public void chargerJeu(String cheminDuFicher) throws IOException {

		int[][] tab1 = new int[9][9];

		try (Scanner scanner = new Scanner(new File(cheminDuFicher))) {
			while (scanner.hasNextInt()) {
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				int z = scanner.nextInt();
				tab1[x][y] = z;
			}
			return;
		}
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public static void sauvegarderJeu(int[][] tab1, String cheminDuFichier)
			throws IOException {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(
				new File(cheminDuFichier)))) {
			for (int i = 0; i < tab1.length; i++) {
				for (int j = 0; j < tab1[i].length; j++) {
					if (tab1[i][j] != 0) {
						writer.write(String.valueOf(i));
						writer.write(" ");
						writer.write(String.valueOf(j));
						writer.write(" ");
						writer.write(String.valueOf(tab1[i][j]));
						writer.write(" ");
					}
				}

			}
		}

	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public static void jouerSudoku() {

	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public static int ObtenirUnNombre(int inf, int sup) {
		while (true) {
			int nombre = 0;
			System.out
					.println("*************sudoku***************************......");
			System.out
					.println("Bienvenue dans le programme de Sudoku, ce programme ...");
			System.out.println(" ");
			System.out.println("Veuillez choisir un des choix suivants:");
			System.out.println("1. Charger jeu.");
			System.out.println("2. Jouer Sudoku.");
			System.out.println("3. Quitter.");
			do {
				if (!sc.hasNextInt()) {
					// Si le nombre n'est pas un entier affiche un message
					System.out.print("Vous devez entrer un nombre entier. ");
					sc.next();
				} else {
					nombre = sc.nextInt();
					// si le nombre n'est pas dans l'interval affiche un message
					if (nombre < inf || nombre > sup) {
						System.out.println("Vous devez entrer 1,2 ou 3. ");
					} else
						// Retourne le nombre
						return nombre;
				}
				// Répète la boucle tant que le nombre n'est pas dans l'interval
			} while (nombre < inf || nombre > sup);
		}
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public static void validerChoix(int inf, int mid, int sup) {
		final String VOTRE_CHOIX = "Votre choix: ";
		int nombre;

		nombre = ObtenirUnNombre(inf, sup);

		if (nombre == inf) {
			System.out.println(VOTRE_CHOIX + inf);
			System.out.println("Veuillez fournir le nom du fichier:");
		} else if (nombre == mid) {
			System.out.println(VOTRE_CHOIX + mid);
			jouerSudoku();
		} else {
			System.out.println(VOTRE_CHOIX + sup);
			System.out.println("Merci d’avoir joué au jeu sudoku.");
			System.exit(0);
		}
	}
}
