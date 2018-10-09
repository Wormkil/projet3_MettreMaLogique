package utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Scanner;
import java.util.Arrays;

public class Utils {
	
	private static final Logger log4j = LogManager.getLogger(Utils.class.getName());
	Scanner keyboard = new Scanner(System.in);
	
	public Utils() {
		
		
	}
	
	public int randomGenerator(int NumberCase) {
		int nbRandom = (int)(Math.random()*Math.pow(10,NumberCase));

		
		//Adds 0 to nbRandom if nbRandom synthax is incorrect 
		String str = Integer.toString(nbRandom);		
		if (NumberCase > str.length()) {
			int i = NumberCase - str.length();
			for ( int j = 0; j<i; j++) {
				str += "0";
				
			}
			nbRandom = Integer.parseInt(str);
		}
		
		return nbRandom;
	}
	
	public int[] tabIntMaker(int IntToTransform, int NumberCase) {
		
		int[] tabInt = new int[NumberCase];
		int tmpInt = IntToTransform;
		
		for ( int i = NumberCase-1; i>=0; i--) {
			tabInt[i] =  tmpInt % 10;
			tmpInt /= 10;
		}
		
		return tabInt;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String[] tabStringMaker(String pStringToTransform, int pNumberCase) {
		
		String[] tabString = new String[pNumberCase];
		String tmpString = pStringToTransform;
		
		for ( int i = pNumberCase-1; i>=0; i--) {
			char tmpChar = tmpString.charAt(i);
			tabString[i] = Character.toString(tmpChar);
		}
		
		return tabString;
		
	}

	public String[] tabCompare(int[] ptab1, int[] ptab2) {
		
		int tabLength = ptab1.length;
		String[] str = new String[tabLength];
				
		for (int i = 0; i < tabLength; i++) {
			if (ptab1[i]>ptab2[i]) { str[i] = "+"; }
			else if (ptab1[i]<ptab2[i]) { str[i] = "-"; }
			else if (ptab1[i]==ptab2[i]) { str[i] = "="; }
			else {log4j.error("Error certainly fatal");}
		}
		
		
		return str;
	}

	public void consoleOutAnswer(int intProposition, String strAnswer) {
		System.out.println("Proposition : " + Integer.toString(intProposition) + " -> Réponse : "+strAnswer);
	}

	public boolean checkWinChallenge(String[] strTab) {
		
		boolean win = true;
		int strLength = strTab.length;
		
		for ( int i = 0; i<strLength; i++) {
			if (strTab[i].equals("+") || strTab[i].equals("-")) { win = false; }
		}
		
		return win;
	}

	public int tabIntToInt(int[][] tabIntMinMax) {
 
		String tmpString = "";
		
		for (int i = 0; i<tabIntMinMax.length; i++) {
			tmpString += Integer.toString(tabIntMinMax[i][0]);
		}
		int intToReturn = Integer.parseInt(tmpString);
		return intToReturn;
	}
	
	public int[][] initTabIntMinMax(int intToTab, int nbCase) {
		
		int[][] tabIntMinMax = new int[nbCase][3];
		int tmpInt = 0;
		String tmp_str = Integer.toString(intToTab);
		
		for (int i = 0;i<nbCase;i++) {
			tmpInt = Character.getNumericValue(tmp_str.charAt(i));
			tabIntMinMax[i][0] = tmpInt;
			tabIntMinMax[i][1] = 0;
			tabIntMinMax[i][2] = 9;
			log4j.trace("tmpInt = "+tmpInt);
			
		}
		
		return tabIntMinMax;
	}
	
	public int[][] changeNbRandom(int[][] tabIntMinMax, String result){
		
		String signe ="";
		int tailleTab = result.length();
		
		for (int i = 0;i<tailleTab;i++) {
			
			signe = Character.toString(result.charAt(i));
			
			switch (signe) {
				case "+":
					tabIntMinMax[i][1] = tabIntMinMax[i][0] + 1;
					tabIntMinMax[i][0] = tabIntMinMax[i][1] + (int)(Math.random() * ((tabIntMinMax[i][2] - tabIntMinMax[i][1]) + 1));
					break;
				case "-":
					tabIntMinMax[i][2] = tabIntMinMax[i][0] - 1;
					tabIntMinMax[i][0] = tabIntMinMax[i][1] + (int)(Math.random() * ((tabIntMinMax[i][2] - tabIntMinMax[i][1]) + 1));
					break;
				case "=":
					tabIntMinMax[i][1] = tabIntMinMax[i][0];
					tabIntMinMax[i][2] = tabIntMinMax[i][0];
					break;
					
			}
			log4j.trace("tabIntMinMax[i][0.1.2] = "+tabIntMinMax[i][0]+" - "+tabIntMinMax[i][1]+" - "+tabIntMinMax[i][2]+" - ");
		}
		
		
		
		
		return tabIntMinMax;
	}
	
	public String listenArrowKeyInput() {
		/*Scanner keyboard = new Scanner(System.in);
		playerInt = keyboard.nextInt();*/   
		/*if (event.getKeyCode() == KeyEvent.VK_UP) {
			return "up";
		}
		else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			return "down";
		}*/
		log4j.info("listen player input");
		Scanner keyboard = new Scanner(System.in);
		String playerInput = keyboard.nextLine();
		log4j.trace("player input = "+playerInput);
		return playerInput;
	}
	
	

	
	/*
	 * public int[] compareTabMastermind(int intSecret, int intToCompare) {
		log4j.info("compareTabMastermind launch");
		
		int nbFind = 0;
		int nbWellPlaced = 0;
		int nbCase = Integer.toString(intSecret).length();
		int[] tabIntSecret = tabIntMaker(intSecret,nbCase);
		int[] tabIntToCompare = tabIntMaker(intToCompare,nbCase);
		

		int[] tmptTab = new int[nbCase];
		
		int[] nbFindPlaceTab = new int[nbCase];
		int tmpInt = 0;
		
		
		for (int i=0; i<nbCase; i++) 
		{
			tmpInt = tabIntSecret[i];
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
		
		nbFindPlaceTab[0] = nbFind;
		nbFindPlaceTab[1] = nbWellPlaced;
		log4j.trace("nbFind = "+nbFind);
		log4j.trace("nbWellPlaced = "+nbWellPlaced);
		
		log4j.info("compareTabMastermind done");
		return nbFindPlaceTab;
		
	}

	public boolean outAnswerMastermind ( int[] resultatCompareTab, int proposition) {
		int nbCase = resultatCompareTab.length;
		System.out.println("Proposition : "+proposition+"  -> Réponse : "+resultatCompareTab[0]+" présent, "+resultatCompareTab[1]+" bien placé");
		if (resultatCompareTab[1] == nbCase) { return true; }
		else { return false; }
	}
	
	public boolean mastermindChallengerOneLoop(int nbRandom) {
		keyboard = new Scanner(System.in);
		int playerInt = keyboard.nextInt();
		
		if (outAnswerMastermind(compareTabMastermind(nbRandom, playerInt), playerInt)) { return true; }
		else { return false; }
	}

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
	 */
	
}
