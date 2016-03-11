import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Nom du programme : TN5 Fichier : Test.java
 * 
 * @author Maxime Drouin
 */

public class Test {
	// Scanner utilisé pour la saisie des données au clavier
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// Appel de la méthode contenant le menu principal
		menuPrincipal(1, 2, 3);
	}

	/*********************************************************************************/
	/*
	 * Affiche le menu principal ainsi que ces choix.
	 * 
	 * @param inf - Le chiffre du choix le plus bas
	 * 
	 * @param mid - Le chiffre du choix du milieu
	 * 
	 * @param sup - Le chiffre du choix le plus haut
	 */
	/*********************************************************************************/
	public static void menuPrincipal(int inf, int mid, int sup) {
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
		// Utilise la méthode ObtenirUnNombre pour obtenir et valider la saisie
		// des nombres
		nombre = ObtenirUnNombre(inf, sup);

		// Si choix 1 appelle la méthode menuSecondaire et lui passe en
		// paramètre la méthode chargerJeu
		if (nombre == inf) {
			System.out.println(VOTRE_CHOIX + inf);
			menuSecondaire(inf, mid, sup, chargerJeu());
			// Si choix 2 appelle la méthode menuSecondaire et créer une
			// nouvelle instance de Grille à partie de la méthode
			// initialiserGrille.
		} else if (nombre == mid) {
			System.out.println(VOTRE_CHOIX + mid);
			Grille sudoku = new Grille(
					Grille.grilleToArrayList(initialiserGrille()));
			menuSecondaire(inf, mid, sup, sudoku);
			// Si choix 3 remercie le joueur d'avoir joué et sort du programme
		} else {
			System.out.println(VOTRE_CHOIX + sup);
			System.out.println("Merci d’avoir joué au jeu sudoku.");
			System.exit(0);
		}
	}

	/*********************************************************************************/
	/*
	 * Affiche le menu principal ainsi que ces choix.
	 * 
	 * @param inf - Le chiffre du choix le plus bas
	 * 
	 * @param mid - Le chiffre du choix du milieu
	 * 
	 * @param sup - Le chiffre du choix le plus haut
	 * 
	 * @param sudoku - La grille crée par le menu principal
	 * 
	 * @return boolean - Affiche le menu après chaque itération.
	 */
	/*********************************************************************************/
	public static boolean menuSecondaire(int inf, int mid, int sup,
			Grille sudoku) {
		// Affiche le menu après chaque itération.
		while (true) {
			System.out
					.println("\n-------------------------------------------------------------------------------");
			// Imprime la grille
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
				// Appelle la méthode sauvegarderJeu et lui passe en paramètre
				// la grille du jeu
				sauvegarderJeu(sudoku.getGrid());
			} else if (nombre == mid) {
				System.out.println(VOTRE_CHOIX + mid);
				// Appelle la méthode jouerSudoku et lui passe le sudoku en
				// paramètre
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
	 * Permet de charger une grille sauvegardée dans un fichier texte.
	 * 
	 * @return - Grille de sudoku générè à partie d'un fichier sauvegardé
	 */
	/*********************************************************************************/
	public static Grille chargerJeu() {
		// Utilise la méthode fairelalecture pour obtenir un BufferedReader
		BufferedReader br = fairelalecture();
		// Créer un ArrayList de cases à partir du BufferedReader en utilisant
		// la méthode lireFichier
		ArrayList<Case> al = lireFichier(br);
		// Créer une nouvelle grille en utilisant l'ArrayList de cases.
		Grille sudoku = new Grille(al);
		// Indique au joueur que la partie a été chargée
		System.out.println("Fichier chargé! \n");
		return sudoku;
	}

	/*********************************************************************************/
	/*
	 * Initialise une grille de sudoku dans le cas où le joueur choisi l'option
	 * jouer sudoku avant d'avoir chargé une grille.
	 */
	/*********************************************************************************/
	public static int[][] initialiserGrille() {
		int[][] grille = { { 0, 6, 0, 1, 0, 4, 0, 5, 0 },
				{ 0, 0, 8, 3, 0, 5, 6, 0, 0 }, { 2, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 8, 0, 0, 4, 0, 7, 0, 0, 6 }, { 0, 0, 6, 0, 0, 0, 3, 0, 0 },
				{ 7, 0, 0, 9, 0, 1, 0, 0, 4 }, { 5, 0, 0, 0, 0, 0, 0, 0, 2 },
				{ 0, 0, 7, 2, 0, 6, 9, 0, 0 }, { 0, 4, 0, 5, 0, 8, 0, 7, 0 } };
		return grille;
	}

	/*********************************************************************************/
	/*
	 * Créer un BufferedReader à partir du nom d'un fichier demandé à
	 * l'utilisateur
	 * 
	 * @return entree - le bufferedReader
	 */
	/*********************************************************************************/
	private static BufferedReader fairelalecture() {
		BufferedReader entree = null;
		boolean flag = true;
		// Exécute la boucle tant que le flag est vrai
		while (flag) {
			try {
				System.out.println("Veuillez fournir le nom du fichier:");
				String nomFichier = sc.next();
				// Créer un fichier
				File unfichier = new File(nomFichier);
				// Créer un bufferedReader à partir d'un fileReader utilisant le
				// fichier
				entree = new BufferedReader(new FileReader(unfichier));
				// Si la lecture est réussie met le flag à false et sort de la
				// boucle.
				flag = false;
			} catch (FileNotFoundException e) {
				// Si ne trouve pas le fichier affiche un message l'indiquant
				System.out.println("Erreur, fichier inexistant! ");
			}

		}
		return entree;
	}

	/*********************************************************************************/
	/*
	 * Créer un ArrayList de cases à Partir du BufferedReader
	 * 
	 * @param fluxEntree - Le BufferedReader
	 * 
	 * @return alCase - Un ArrayList de cases
	 */
	/*********************************************************************************/
	private static ArrayList<Case> lireFichier(BufferedReader fluxEntree) {
		int ligne, colonne, valeur;
		ArrayList<Case> alCase = new ArrayList<>();
		String line = " ";
		String[] donnee;
		try {
			// Li la ligne du fichier
			line = fluxEntree.readLine();
		} catch (IOException e) {
			System.out.println("Erreur de lecture");
			System.exit(0);
		}
		// Renvoi null si la ligne est vide
		if (line == null) {
			return null;
		} else {
			// Split le contenu de la ligne à chaque "Espace" et met le résultat
			// dans l'Array donnee
			donnee = line.split(" ");

			// Pour chaque entrée de "donnee"
			for (String placement : donnee) {
				// Assigne le premier chiffre de la donnee à la variable ligne
				ligne = placement.charAt(0) - '0';
				// Assigne le deuxième chiffre de la donnee à la variable
				// colonne
				colonne = placement.charAt(1) - '0';
				// Assigne le troisième chiffre de la donnee à la variable
				// valeur
				valeur = placement.charAt(2) - '0';
				// Créer une case à partir des 3 données
				Case caseActuelle = new Case(ligne, colonne, valeur);
				// Ajoute cette case à l'ArrayList
				alCase.add(caseActuelle);
			}
		}

		return alCase;
	}

	/*********************************************************************************/
	/* Sauvegarde la partie courante au format texte
	 * 
	 * @param grid - Une grille de sudoku
	 */
	/*********************************************************************************/
	public static void sauvegarderJeu(int[][] grid) {
		System.out.println("Veuillez fournir le nom du fichier:");
		String cheminDuFichier = sc.next();
		// Transforme la grille en ArrayList de Cases
		ArrayList<Case> al = Grille.grilleToArrayList(grid);
		// Créer le fichier
		File fichier = new File(cheminDuFichier);
		PrintWriter sortie = null;

		try {
			// Créer un PrintWriter à partir d'un BufferedWriter
			sortie = new PrintWriter(
					new BufferedWriter(new FileWriter(fichier)));
			// Pour chaque entrée de l'ArrayList
			for (Case placement : al) {
				// Si la valeur du placement est différente de 0
				if (placement.getValeur() != 0) {
					// Imprime la ligne, la colonne et la valeur suivi d'un espace.
					sortie.print(placement.getLigne());
					sortie.print(placement.getColonne());
					sortie.print(placement.getValeur());
					sortie.print(" ");
				}
			}
			// Affiche le message de confirmation du fichier sauvegardé
			System.out.println("Fichier sauvegardé!");
		} catch (IOException e) {
			// Si erreur de sauvegarde affiche ce message.
			System.out.println("Erreur de sauvegarde");
			e.printStackTrace();
		} finally {
			// Finalement ferme "sortie" si pas nul
			if (sortie != null) {
				sortie.close();
			}
		}

	}

	/*********************************************************************************/
	/* Demande au joueur 1 ligne, 1 colonne et 1 valeur et ensuite appelle la
	 * méthode fairePlacement pour placer ces valeurs dans la grille.
	 * 
	 * @param sudoku - Une instance de jeu
	 */
	/*********************************************************************************/
	public static void jouerSudoku(Grille sudoku) {
		int ligne = obtenirNombrePlusPetitOuEgalA(9, "ligne");
		int colonne = obtenirNombrePlusPetitOuEgalA(9, "colonne");
		int valeur = obtenirNombrePlusPetitOuEgalA(9, "valeur");
		sudoku.fairePlacement(valeur, ligne, colonne);
	}

	/*********************************************************************************/
	/* Demande au joueur d'entrer une valeur et valide que celle=ci est contenu 
	 * dans l'interval voulu. Lance une exception InputMismatchException si la 
	 * valeur n'est pas un integer.
	 * 
	 * @param sup - La valeur maximale du chiffre.
	 * @param rowColVal - Le type de valeur demandé
	 */
	/*********************************************************************************/
	public static int obtenirNombrePlusPetitOuEgalA(int sup, String rowColVal) {
		final String message = "Entrez un chiffre pour la " + rowColVal
				+ ", il doit être compris entre 1 et " + sup + ".";
		final String message1 = "Veuillez choisir une " + rowColVal + ":";
		int nombre = 0;
		do {
			System.out.println(message1);
			try {
				nombre = sc.nextInt();
				if (nombre < 1 || nombre > sup) {
					System.out.println(message);
				}

			} catch (InputMismatchException e) {
				System.out.println(message);
				sc.next();
				obtenirNombrePlusPetitOuEgalA(sup, rowColVal);
			}
		} while (nombre < 1 || nombre > sup);
		return nombre;
	}

	/*********************************************************************************/
	/* Demande une valeur qui doit être comprise dans l'interval donné. Lance une exception 
	 * InputMismatchException si la valeur n'est pas un integer.
	 * 
	 * @param inf - Valeur minimal du chiffre
	 * @param sup - Valeur maximal du chiffre
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
