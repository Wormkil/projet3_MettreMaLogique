package rechercheMode;

import projet3_MettreMaLogique.Recherche;

public class RechercheModeParent extends Recherche {
	

	static String playerString = "";
	
	// Mode 1,2
	protected static String tabCompare(String[] ptab1, String[] ptab2) {
		
		int tabLength = ptab1.length;
		String str = "";
				
		for (int i = 0; i < tabLength; i++) {
			if (Integer.parseInt(ptab1[i])>Integer.parseInt(ptab2[i])) { str += "+"; }
			else if (Integer.parseInt(ptab1[i])<Integer.parseInt(ptab2[i])) { str += "-"; }
			else if (Integer.parseInt(ptab1[i])==Integer.parseInt(ptab2[i])) { str += "="; }
		}
		
		
		return str;
	}
	
	// Mode 1
	protected static void printProposition(String proposition, String result) { 
		
		System.out.println("Proposition : " + proposition + " -> Réponse : "+result);
		
	}
	
	// Mode 1,2	
	protected static boolean checkWinChallenge(String strTab) {
		
		boolean win = true;
		int strLength = strTab.length();
		
		for ( int i = 0; i<strLength; i++) {
			if (strTab.charAt(i) == '+' || strTab.charAt(i) == '-') { win = false; }
		}
		
		return win;
	}
	
	// Mode 1	
	protected static boolean oneLoopChallenger(String nbRandom) {
		System.out.print("Proposition : ");
		String playerInt = u.listenIntPlayer(nbCase);
		
		
		
		String[] tabPlayerInt = playerInt.split("");
		String[] tabPlayerNbRandom = nbRandom.split("");
		
		String resultCompare = tabCompare(tabPlayerNbRandom, tabPlayerInt);
		

		printProposition(playerInt,resultCompare);
		
		return checkWinChallenge(resultCompare);
	}
	
	// Mode 2
	protected static boolean oneLoopDefenseur(String nbRandom) {
		System.out.println("Je pense que votre nombre est : "+nbRandom);
		playerString = u.listenStringPlayer(nbCase, true);
		
		printProposition(nbRandom,playerString);
		
		return checkWinChallenge(playerString);
		
	}
	
	// Mode 2
	protected static String tabIntToInt(String[][] tabIntMinMax) {
		 
		String tmpString = "";
		
		for (int i = 0; i<tabIntMinMax.length; i++) {
			tmpString += tabIntMinMax[i][0];
		}
		
		return tmpString;
	}
	
	// Mode 2
	protected static String[][] changeNbRandom(String[][] tabIntMinMax, String result){
		
		String signe ="";
		int tailleTab = result.length();
		
		for (int i = 0;i<tailleTab;i++) {
			
			signe = Character.toString(result.charAt(i));
			
			switch (signe) {
				case "+":
					tabIntMinMax[i][1] = Integer.toString(Integer.parseInt(tabIntMinMax[i][0]) + 1);
					tabIntMinMax[i][0] = Integer.toString(Integer.parseInt(tabIntMinMax[i][1]) + (int)(Math.random() * ((Integer.parseInt(tabIntMinMax[i][2]) - Integer.parseInt(tabIntMinMax[i][1])) + 1)));
					break;
				case "-":
					tabIntMinMax[i][2] = Integer.toString(Integer.parseInt(tabIntMinMax[i][0]) - 1);
					tabIntMinMax[i][0] = Integer.toString(Integer.parseInt(tabIntMinMax[i][1]) + (int)(Math.random() * ((Integer.parseInt(tabIntMinMax[i][2]) - Integer.parseInt(tabIntMinMax[i][1])) + 1)));
					break;
				case "=":
					tabIntMinMax[i][1] = tabIntMinMax[i][0];
					tabIntMinMax[i][2] = tabIntMinMax[i][0];
					break;
					
			}
			//log4j.trace("tabIntMinMax[i][0.1.2] = "+tabIntMinMax[i][0]+" - "+tabIntMinMax[i][1]+" - "+tabIntMinMax[i][2]+" - ");
		}
		
		return tabIntMinMax;
	}
	
	//Mode 2
	protected static String[][] initTabIntMinMax(String intToTab) {
		
		String[][] tabIntMinMax = new String[intToTab.length()][3];
		String tmp_str = intToTab;
		
		for (int i = 0;i<nbCase;i++) {
			tabIntMinMax[i][0] = Character.toString(tmp_str.charAt(i));
			tabIntMinMax[i][1] = "0";
			tabIntMinMax[i][2] = "9";
			
		}
		
		return tabIntMinMax;
	}
	
}
