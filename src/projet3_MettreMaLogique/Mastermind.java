package projet3_MettreMaLogique;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.Scanner;
import utils.Utils;



// Regroupe les méthodes utilisées pour jouer au mastermind
public class Mastermind {
	
	//Initialisation de quelque classe utilisé tout au long des méthodes
	private static final Logger log4j = LogManager.getLogger(Mastermind.class.getName());
	private static Scanner keyboard = new Scanner(System.in);
	private static final Utils u = new Utils();
	
	public Mastermind(int nbCase, int nbTry, String mode) {
		int nbRandom = 0;

		switch (mode) {
		case "defenseur" :
			System.out.println("Bienvenue dans le mastermind ! Je vais choisir un nombre et vous essayerez de le deviner");
			System.out.println("Je vous aiderais en vous indiquant le nombre de chiffre trouvé et bien placé ");
			
			nbRandom = u.randomGenerator(nbCase);
			System.out.println("(dev mode) nbRandom = "+nbRandom);
			
			loopAndCheckWinMastermind(nbRandom,nbTry);
			break;
		}
		
	}
	
	
	
	//Méthode qui prend deux suite de nombre en paramètre, les parcours puis compte le nombre de bien placé et de présent
	public int[] compareTabMastermind(int intSecret, int intToCompare) {
		
		log4j.info("compareTabMastermind launch");
		
		int nbFind = 0;
		int nbWellPlaced = 0;
		int nbCase = Integer.toString(intSecret).length();
		
		// On transforme les suite de chiffre en tableaux
		int[] tabIntSecret = u.tabIntMaker(intSecret,nbCase);
		int[] tabIntToCompare = u.tabIntMaker(intToCompare,nbCase);
		int[] nbFindPlaceTab = new int[nbCase];
		
		//Double for pour parcourir les deux tableaux d'int, pour compter le nombre de chiffre présent et
		//bien placé du premier tableaux en fonction du deuxième
		for (int i=0; i<nbCase; i++) 
		{
			for (int j=0; j<nbCase; j++) 
			{
				if ( tabIntSecret[i] == tabIntToCompare[i]) {
					nbWellPlaced += 1;
					j = nbCase;
				}
				
				else if (tabIntSecret[i] == tabIntToCompare[j]) { 
					nbFind += 1;
					j = nbCase;
				}
			}
			
			
		}
		
		//On centralise les résultat dans un tableaux à 2 case qui est retourné
		nbFindPlaceTab[0] = nbFind;
		nbFindPlaceTab[1] = nbWellPlaced;
		log4j.info("nbFind = "+nbFind);
		log4j.info("nbWellPlaced = "+nbWellPlaced);
		
		log4j.info("compareTabMastermind done");
		return nbFindPlaceTab;
		
	}

	// Méthode qui affiche sur la console une réponse, gràce a ses paramètres, de type :
	//" Proposition : 4278 -> Réponse : 1 présent, 1 bien placé "
	public boolean outAnswerMastermind ( int[] resultatCompareTab, int proposition) {
		int nbCase = resultatCompareTab.length;
		System.out.println("Proposition : "+proposition+"  -> Réponse : "+resultatCompareTab[0]+" présent, "+resultatCompareTab[1]+" bien placé");
		if (resultatCompareTab[1] == nbCase) { return true; }
		else { return false; }
	}
	
	//Correspond à une boucle de jeux mastermind, prend un nombre secret en paramètre puis le compare à un chiffre donné par l'utilsateur
	public boolean mastermindChallengerOneLoop(int intSecret) {
		keyboard = new Scanner(System.in);
		int playerInt = keyboard.nextInt();
		
		if (outAnswerMastermind(compareTabMastermind(intSecret, playerInt), playerInt)) { return true; }
		else { return false; }
	}

	//invoque nbTry fois la methode mastermindChallengerOneLoop, et affiche une réponse en fonction du résultat	
	public void loopAndCheckWinMastermind(int nbRandom, int nbTry) {
		int cpt = 0;
		boolean haveWin = false;
		
		while ( cpt < nbTry && haveWin == false) {
			if (mastermindChallengerOneLoop(nbRandom)){ haveWin = true; }
			else { haveWin = false; }
			cpt++;
		}
		
		if (haveWin) {
			System.out.println("Bravo vous avez trouvé ! La solution était bien "+nbRandom);
		}
		else {
			System.out.println("Vous avez perdu ... La solution étaitdown "+nbRandom);
		}
	}
}
