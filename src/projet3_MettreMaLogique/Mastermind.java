package projet3_MettreMaLogique;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.Scanner;
import utils.Utils;



// Regroupe les m�thodes utilis�es pour jouer au mastermind
public class Mastermind {
	
	//Initialisation de quelque classe utilis� tout au long des m�thodes
	private static final Logger log4j = LogManager.getLogger(Mastermind.class.getName());
	private static Scanner keyboard = new Scanner(System.in);
	private static final Utils u = new Utils();
	
	public Mastermind(int nbCase, int nbTry, String mode) {
		int nbRandom = 0;

		switch (mode) {
		case "defenseur" :
			System.out.println("Bienvenue dans le mastermind ! Je vais choisir un nombre et vous essayerez de le deviner");
			System.out.println("Je vous aiderais en vous indiquant le nombre de chiffre trouv� et bien plac� ");
			
			nbRandom = u.randomGenerator(nbCase);
			System.out.println("(dev mode) nbRandom = "+nbRandom);
			
			loopAndCheckWinMastermind(nbRandom,nbTry);
			break;
		}
		
	}
	
	
	
	//M�thode qui prend deux suite de nombre en param�tre, les parcours puis compte le nombre de bien plac� et de pr�sent
	public int[] compareTabMastermind(int intSecret, int intToCompare) {
		
		log4j.info("compareTabMastermind launch");
		
		int nbFind = 0;
		int nbWellPlaced = 0;
		int nbCase = Integer.toString(intSecret).length();
		
		// On transforme les suite de chiffre en tableaux
		int[] tabIntSecret = u.tabIntMaker(intSecret,nbCase);
		int[] tabIntToCompare = u.tabIntMaker(intToCompare,nbCase);
		int[] nbFindPlaceTab = new int[nbCase];
		
		//Double for pour parcourir les deux tableaux d'int, pour compter le nombre de chiffre pr�sent et
		//bien plac� du premier tableaux en fonction du deuxi�me
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
		
		//On centralise les r�sultat dans un tableaux � 2 case qui est retourn�
		nbFindPlaceTab[0] = nbFind;
		nbFindPlaceTab[1] = nbWellPlaced;
		log4j.info("nbFind = "+nbFind);
		log4j.info("nbWellPlaced = "+nbWellPlaced);
		
		log4j.info("compareTabMastermind done");
		return nbFindPlaceTab;
		
	}

	// M�thode qui affiche sur la console une r�ponse, gr�ce a ses param�tres, de type :
	//" Proposition : 4278 -> R�ponse : 1 pr�sent, 1 bien plac� "
	public boolean outAnswerMastermind ( int[] resultatCompareTab, int proposition) {
		int nbCase = resultatCompareTab.length;
		System.out.println("Proposition : "+proposition+"  -> R�ponse : "+resultatCompareTab[0]+" pr�sent, "+resultatCompareTab[1]+" bien plac�");
		if (resultatCompareTab[1] == nbCase) { return true; }
		else { return false; }
	}
	
	//Correspond � une boucle de jeux mastermind, prend un nombre secret en param�tre puis le compare � un chiffre donn� par l'utilsateur
	public boolean mastermindChallengerOneLoop(int intSecret) {
		keyboard = new Scanner(System.in);
		int playerInt = keyboard.nextInt();
		
		if (outAnswerMastermind(compareTabMastermind(intSecret, playerInt), playerInt)) { return true; }
		else { return false; }
	}

	//invoque nbTry fois la methode mastermindChallengerOneLoop, et affiche une r�ponse en fonction du r�sultat	
	public void loopAndCheckWinMastermind(int nbRandom, int nbTry) {
		int cpt = 0;
		boolean haveWin = false;
		
		while ( cpt < nbTry && haveWin == false) {
			if (mastermindChallengerOneLoop(nbRandom)){ haveWin = true; }
			else { haveWin = false; }
			cpt++;
		}
		
		if (haveWin) {
			System.out.println("Bravo vous avez trouv� ! La solution �tait bien "+nbRandom);
		}
		else {
			System.out.println("Vous avez perdu ... La solution �taitdown "+nbRandom);
		}
	}
}
