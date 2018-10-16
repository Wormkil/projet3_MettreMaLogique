package mastermindMode;

import projet3_MettreMaLogique.Mastermind;

public class MastermindModeParent extends Mastermind {
	
	
	protected static int[] compareTabMastermind(int intSecret, int intToCompare) {
				
		int nbFind = 0;
		int nbWellPlaced = 0;
		String stringSecret = Integer.toString(intSecret);
		String stringToCompare = Integer.toString(intToCompare);
		int nbCase = stringSecret.length();
		
		
		String[] tabIntSecret = stringSecret.split("");
		String[] tabIntToCompare = stringToCompare.split("");
		int[] nbFindPlaceTab = new int[nbCase];
		
		for (int i=0; i<nbCase; i++) 
		{
			for (int j=0; j<nbCase; j++) 
			{
				if ( tabIntSecret[i].equals(tabIntToCompare[i]) ) {
					nbWellPlaced += 1;
					j = nbCase;
				}
				
				else if (tabIntSecret[i].equals(tabIntToCompare[j])) { 
					nbFind += 1;
					j = nbCase;
				}
			}
		}
		
		nbFindPlaceTab[0] = nbFind;
		nbFindPlaceTab[1] = nbWellPlaced;

		return nbFindPlaceTab;
		
	}
	
	protected static boolean outOneLoopChallengeur(String intRandom) {
		boolean win = false;
		
		System.out.print("Proposition : ");
		String playerString = u.listenIntPlayer(nbCase);
		
		int strLength = playerString.length();
		int[] tabSolution = new int[strLength];
	
		tabSolution = compareTabMastermind(Integer.parseInt(intRandom),Integer.parseInt(playerString));
		
		System.out.println("Proposition : "+playerString+" -> Réponse : "+tabSolution[0]+" présent, "+tabSolution[1]+" bien placé");
		
		if (tabSolution[1] == strLength ) {
			win  = true;
		}
		
		return win;
	}
	
}
