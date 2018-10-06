package utils;

import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;

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
			if (strTab[i] == "+" || strTab[i] == "-") { win = false; }
		}
		
		return win;
	}
}
