package projet3_MettreMaLogique;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.Utils;

public class GameManager {
	
	private static final Logger log4j = LogManager.getLogger(Main.class.getName());
	public static Utils u = new Utils();
	
	Scanner keyboard = new Scanner(System.in);
	
	public static void gameManager() {
		
		
		
		//log4j.info("GameManager constructor launch"); 
		
		/*System.out.println( "============================================================="+'\n'+
							"Boujours, bienvenu dans cette compilation de jeux"+'\n'+
							"Vous pouvez jouer à deux jeux, choisissez à l'aide"+'\n'+
							"des touches fléchés et valider avec entré :"+'\n'+
							"> Recherche + ou - <"+'\n'+
							"Mastermind"+'\n'+
							"============================================================="
						);*/
		
		
		//JeuxRechercheManager obj_jeu = new JeuxRechercheManager(nbCase,nbTry, "defenseur");
		
		
	}
	
	//Une fonction qui gère le choix du jeux et du mode
	String choixPlayer() {
		final String gameToLaunch = "Recherche + ou -";
		System.out.println( "============================================================="+'\n'+'\n'+
				
							"Boujours, bienvenu dans cette compilation de jeux"+'\n'+
							"Vous pouvez jouer à deux jeux, choisissez à l'aide"+'\n'+
							"des touches fléchés et valider avec entré :"+'\n'+'\n'+
							
							"> Recherche + ou - <"+'\n'+
							"  Mastermind"+'\n'+'\n'+
							
							"============================================================="
						);
		
		
		return gameToLaunch;
	}
	
	
	
	public void gameRecherche(int nbCase, int nbTry) {
		

		log4j.info("Game recherche launch");
		
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
		
		log4j.info("Game recherche done");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
