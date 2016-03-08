import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Nom du programme : TN5 Fichier : Test.java
 * 
 * @author Maxime Drouin
 */

public class Test {
	static Scanner sc = new Scanner(System.in);

	// Grille utilisée si le joueur choisi l'option jouerSudoku avant d'avoir
	// chargé le jeu
	static int[][] grille = { { 0, 4, 3, 7, 0, 0, 9, 0, 8 },
			{ 0, 0, 5, 0, 3, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 3, 0, 0 },
			{ 6, 0, 0, 0, 2, 7, 0, 0, 0 }, { 4, 0, 7, 0, 0, 0, 1, 0, 3 },
			{ 0, 0, 0, 5, 4, 0, 0, 0, 9 }, { 0, 0, 2, 0, 0, 0, 0, 3, 0 },
			{ 0, 0, 0, 0, 5, 0, 4, 0, 0 }, { 5, 0, 4, 0, 0, 1, 2, 6, 0 } };

	public static void main(String[] args) {

		final String VOTRE_CHOIX = "Votre choix: ";
		int nombre;

		System.out
				.println("*************sudoku***************************......");
		System.out
				.println("Bienvenue dans le programme de Sudoku, ce programme ...");
		System.out.println(" ");
		System.out.println("Veuillez choisir un des choix suivants:");
		System.out.println("1. Charger jeu.");
		System.out.println("2. Jouer Sudoku.");
		System.out.println("3. Quitter.");

		nombre = ObtenirUnNombre(1, 3);

		if (nombre == 1) {
			System.out.println(VOTRE_CHOIX + 1);

			menuSecondaire(1, 2, 3, chargerJeu());

		} else if (nombre == 2) {
			System.out.println(VOTRE_CHOIX + 2);
			Grille sudoku = new Grille(Grille.grilleToArrayList(grille));

			jouerSudoku(sudoku);
		} else {
			System.out.println(VOTRE_CHOIX + 3);
			System.out.println("Merci d’avoir joué au jeu sudoku.");
			System.exit(0);
		}
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public static Grille chargerJeu() {
		BufferedReader br = fairelalecture();
		ArrayList<Case> al = lireFichier(br);
		Grille sudoku = new Grille(al);
		System.out.println("Fichier chargé! \n");
		return sudoku;
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	private static BufferedReader fairelalecture() {
		BufferedReader entree = null;
		boolean flag = true;
		while (flag) {
			try {
				System.out.println("Veuillez fournir le nom du fichier:");
				String nomFichier = sc.next();
				File unfichier = new File(nomFichier);
				entree = new BufferedReader(new FileReader(unfichier));
				flag = false;
			} catch (FileNotFoundException e) {
				System.out.println("Erreur, fichier inexistant! ");
			}

		}
		return entree;
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	private static ArrayList<Case> lireFichier(BufferedReader fluxEntree) {
		int ligne, colonne, valeur;
		ArrayList<Case> alCase = new ArrayList<>();
		String line = " ";
		String[] donnee;
		try {
			line = fluxEntree.readLine();
		} catch (IOException e) {
			System.out.println("erreur de I/O");
			System.exit(0);
		}
		if (line == null) {
			return null;
		} else {
			donnee = line.split(" ");

			for (String placement : donnee) {
				ligne = placement.charAt(0) - '0';
				colonne = placement.charAt(1) - '0';
				valeur = placement.charAt(2) - '0';
				Case caseActuelle = new Case(ligne, colonne, valeur);
				alCase.add(caseActuelle);
			}
		}

		return alCase;
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public static void sauvegarderJeu(String cheminDuFichier) {
		ArrayList<Case> al = Grille.grilleToArrayList(grille);
		File fichier = new File(cheminDuFichier);
		PrintWriter sortie = null;

		try {
			sortie = new PrintWriter(
					new BufferedWriter(new FileWriter(fichier)));
			for (Case placement : al) {
				if (placement.getValeur() != 0) {
					sortie.print(placement.getLigne());
					sortie.print(placement.getColonne());
					sortie.print(placement.getValeur());
					sortie.print("\t");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sortie != null) {
				sortie.close();
			}
		}

	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public static void jouerSudoku(Grille sudoku) {
		int ligne = obtenirNombrePlusPetitQue(8, "ligne");
		int colonne = obtenirNombrePlusPetitQue(8, "colonne");
		int valeur = obtenirNombrePlusPetitQue(9, "valeur");
		sudoku.fairePlacement(valeur, ligne, colonne);
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public static boolean menuSecondaire(int inf, int mid, int sup,
			Grille sudoku) {
		
		while (true) {
			System.out
			.println("\n-------------------------------------------------------------------------------");
			sudoku.imprimerGrille();
			final String VOTRE_CHOIX = "Votre choix: ";
			int nombre;
			System.out.println("Veuillez choisir un des choix suivants:");
			System.out.println("1. Sauvegarder jeu.");
			System.out.println("2. Jouer Sudoku.");
			System.out.println("3. Quitter.");

			nombre = ObtenirUnNombre(inf, sup);

			if (nombre == inf) {
				System.out.println(VOTRE_CHOIX + inf);
				System.out.println("Veuillez fournir le nom du fichier:");
				String nomFichier = sc.next();
				sauvegarderJeu(nomFichier);
			} else if (nombre == mid) {
				System.out.println(VOTRE_CHOIX + mid);
				jouerSudoku(sudoku);
			} else {
				System.out.println(VOTRE_CHOIX + sup);
				System.out.println("Merci d’avoir joué au jeu sudoku.");
				System.exit(0);
			}

		}
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public static int obtenirNombrePlusPetitQue(int sup, String rowColVal) {
		final String message = "Entrez un chiffre pour la " + rowColVal
				+ ", il doit être compris entre 0 et " + sup + ".";
		final String message1 = "Veuillez choisir une " + rowColVal + ":";
		int nombre = 0;
		do {
			System.out.println(message1);
			try {
				nombre = sc.nextInt();
				if (nombre < 0 || nombre > sup) {
					System.out.println(message);
				}

			} catch (InputMismatchException e) {
				System.out.println(message);
				sc.next();
				obtenirNombrePlusPetitQue(sup, rowColVal);
			}
		} while (nombre < 0 || nombre > sup);
		return nombre;
	}

	/*********************************************************************************/
	/*
	 * 
	 * 
	 */
	/*********************************************************************************/
	public static int ObtenirUnNombre(int inf, int sup) {
		final String message = "Vous devez entrer un nombre compris entre "
				+ inf + " et " + sup + ".";
		int nombre = 0;
		do {
			try {
				nombre = sc.nextInt();
				if (nombre < inf || nombre > sup) {
					System.out.println(message);
				}

			} catch (InputMismatchException e) {
				System.out.println(message);
				sc.next();
			}
		} while (nombre < inf || nombre > sup);
		return nombre;
	}

}
