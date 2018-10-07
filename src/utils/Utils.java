package utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Scanner;

public class Utils {
	
	private static final Logger log4j = LogManager.getLogger(Utils.class.getName());
	
	public Utils() {
		
		
	}
	
	public int randomGenerator(int pNumberCase) {
		int nbRandom = (int)(Math.random()*Math.pow(10,pNumberCase));

		
		//Adds 0 to nbRandom if nbRandom synthax is incorrect 
		String str = Integer.toString(nbRandom);		
		if (pNumberCase > str.length()) {
			int i = pNumberCase - str.length();
			for ( int j = 0; j<i; j++) {
				str += "0";
				
			}
			nbRandom = Integer.parseInt(str);
		}
		
		return nbRandom;
	}
	
	public int[] tabIntMaker(int pIntToTransform, int pNumberCase) {
		
		int[] tabInt = new int[pNumberCase];
		int tmpInt = pIntToTransform;
		
		for ( int i = pNumberCase-1; i>=0; i--) {
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
	
	
	
}
