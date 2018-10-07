package projet3_MettreMaLogique;

import java.util.Objects;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.Utils;

public class GameManager {
	
	private static final Logger log4j = LogManager.getLogger(Main.class.getName());
	public static Utils u = new Utils();
	
	Scanner keyboard = new Scanner(System.in);
	
	public static void gameManager() {
		
	}	

	//Une fonction qui gère le choix du jeux et du mode
	String choixPlayer() {
		String gameToLaunch = "Recherche + ou -";
		boolean entre = false;
		String gameString = gameToLaunch;
		
		while (!entre) {
			for (int i = 0; i<20; i++) {System.out.println(".");}
			
			if (Objects.equals(gameString,"Recherche + ou -")) {
				System.out.println( "============================================================="+'\n'+'\n'+
					
								"Boujours, bienvenu dans cette compilation de jeux"+'\n'+
								"Vous pouvez jouer à deux jeux, choisissez à l'aide"+'\n'+
								"de commande up ou down et valider avec entré :"+'\n'+'\n'+
								
								"> Recherche + ou - <"+'\n'+
								"  Mastermind"+'\n'+'\n'+
								
								"============================================================="
							);
			}
			else if (Objects.equals(gameString,"Mastermind")) {
				System.out.println( "============================================================="+'\n'+'\n'+
						
						"Boujours, bienvenu dans cette compilation de jeux"+'\n'+
						"Vous pouvez jouer à deux jeux, choisissez à l'aide"+'\n'+
						"de commande up ou down et valider avec entré :"+'\n'+'\n'+
						
						"  Recherche + ou - "+'\n'+
						"> Mastermind <" +'\n'+'\n'+
						
						"============================================================="
					);
			}
			String inputPlayer = u.listenArrowKeyInput();
			switch (inputPlayer) {
				case "up":
					if (Objects.equals(gameString,"Recherche + ou -")) { gameString = "Mastermind"; }
					else if (Objects.equals(gameString,"Mastermind")) { gameString = "Recherche + ou -"; }
					break;
				case "down":
					if (Objects.equals(gameString,"Recherche + ou -")) { gameString = "Mastermind"; }
					else if (Objects.equals(gameString,"Mastermind")) { gameString = "Recherche + ou -"; }			
					break;
				case "entre" :
					gameToLaunch = gameString;
					entre = true;
					break;
				
				
			
			}
		}
		return gameToLaunch;
	}
	
	
	
	public void gameRechercheChallenger(int nbCase, int nbTry) {
		

		log4j.info("Game recherche Challenger launch");
		
		boolean loop = true;
		int currentTry = 1;
		
		
		System.out.println("Je vais maintenant tirer un nombre aléatoire, à vous de me faire une proposition et je vous dirais si "+'\n'+"chaque chiffre est plus petit, égal ou plus grand ! ");
		int nbRandom = u.randomGenerator(nbCase);
		log4j.trace("nbRandom = "+nbRandom);
		
		while (loop) {
			keyboard = new Scanner(System.in);
			int playerInt = keyboard.nextInt();
			log4j.trace("nbPlayer = "+playerInt);
			
			int[] tabPlayerInt = u.tabIntMaker(playerInt,nbCase);
			int[] tabPlayerNbRandom = u.tabIntMaker(nbRandom,nbCase);
			
			String[] tabResultCompare = u.tabCompare(tabPlayerNbRandom, tabPlayerInt);
			
			
			String result = "";
			for (int i = 0; i<nbCase; i++) {
				
				result += tabResultCompare[i];
			}
			
			u.consoleOutAnswer(playerInt, result);
			
			if (u.checkWinChallenge(tabResultCompare)) {
				System.out.println("Bravo vous avez trouvé ! La solution était bien : " + Integer.toString(nbRandom)  /*+ ". Vous avez trouvé en " + Integer.toString(tmp_nbTry) + " tentative !"+'\n'+"Félicitation, le jeux va maintenant ce relancer..."+'\n'+'\n'*/);
				loop = false;
			}
			else if (currentTry < nbTry ){
				currentTry++;
			}
			else {
				loop = false;
				System.out.println("Vous avez perdu, désolé ... La solution était : "+Integer.toString(nbRandom)+'\n'+"Mais vous pouvez recommencer encore et encore ! Le jeux va maintenant ce relancer "+'\n'+'\n'+'\n');
			}
		}
		
		log4j.info("Game recherche Challenger done");
		
	}
	
	// Attention, leger problème d'affichage quand un chiffre ne fait pas la taille de nbCase
	public void gameRechercheDefenseur(int nbCase, int nbTry) {
		
		log4j.info("Game recherche Defenseur launch");
		
		String playerString = "";
		String[] tabString = new String[nbCase];
		boolean loop = true;
		int currentTry = 1;
		
		
		System.out.println("Veuillez me donnée un nombre, je vais essayer de le trouver !");
		System.out.println("Quand je vous donnerais un nombre, indiquez moi avec des signe +, -, ou = si je suis sur la bonne piste !");
		
		
		keyboard = new Scanner(System.in);
		int playerInt = keyboard.nextInt();
		log4j.trace("playerInt = "+Integer.toString(playerInt));
		
		
		int nbRandom = u.randomGenerator(nbCase);
		int[][] numberMinMaxTab = new int[nbCase][3];
		numberMinMaxTab = u.initTabIntMinMax(nbRandom, nbCase);
		
		while (loop) {
			
			System.out.println("Je pense que votre nombre est : "+nbRandom);
			
			keyboard = new Scanner(System.in);
			playerString = keyboard.nextLine();
			
			u.consoleOutAnswer(nbRandom, playerString);
			
			tabString = u.tabStringMaker(playerString, nbCase);

			if (u.checkWinChallenge(tabString)) {
				System.out.println("Oui, j'ai trouvé ! La solution était bien : " + Integer.toString(nbRandom)  /*+ ". Vous avez trouvé en " + Integer.toString(tmp_nbTry) + " tentative !"+'\n'+"Félicitation, le jeux va maintenant ce relancer..."+'\n'+'\n'*/);
				loop = false;
			}
			else if (currentTry < nbTry ){
				numberMinMaxTab = u.changeNbRandom(numberMinMaxTab, playerString);
				nbRandom = u.tabIntToInt(numberMinMaxTab);
				log4j.trace("new nb random draw = "+nbRandom);
				currentTry++;
			}
			else {
				loop = false;
				System.out.println("Mince j'ai perdu ... La solution était : "+Integer.toString(playerInt)+'\n'+"Mais vous pouvez recommencer encore et encore ! Le jeux va maintenant ce relancer "+'\n'+'\n'+'\n');
			}
			
		}
		
		
		
		
		
		log4j.info("Game recherche Defenseur done");
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
