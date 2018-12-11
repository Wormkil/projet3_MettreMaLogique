package utils;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
	
	//private static final Logger log4j = LogManager.getLogger(Utils.class.getName());
	Scanner keyboard = new Scanner(System.in);
	
	public String randomGenerator(int NumberCase) {
		int nbRandom = (int)(Math.random()*Math.pow(10,NumberCase));

		
		String str = Integer.toString(nbRandom);		
		if (NumberCase > str.length()) {
			int i = NumberCase - str.length();
			for ( int j = 0; j<i; j++) {
				str = "0"+str;
			}
		}
		
		return str;
	}
	
	public String listenPlayer(String contraintes) {
		Contraintes contrainte = new Contraintes(contraintes);
		keyboard = new Scanner(System.in);
		String playerIn;
		
		do{
			playerIn = keyboard.nextLine();
			
		} while (!contrainte.testPlayerIn(playerIn));
		
		return playerIn;
	}
	
	public boolean stringIsInt(String string) {
		try {
			Integer.parseInt(string);
			return true;
			
		} catch (NumberFormatException ex) {
			return false;
		} 
	}
	
	
	public boolean correctFormat(String string) {
		
		for (int j = 0; j<string.length(); j++) {
			if (string.charAt(j) != '+' && string.charAt(j) != '-' && string.charAt(j) != '=' ) {
				System.out.println("return false + "+string);
				return false;
			}
		}
		return true;
		
	}
	
	public String[] getConfig() {
		String[] configTab = new String[4];
		
		final Properties confProperties = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("src/config.properties");

			confProperties.load(input);
			
			configTab[0] = confProperties.getProperty("nbCase");
			configTab[1] = confProperties.getProperty("nbTry");
			configTab[2] = confProperties.getProperty("devMode");
			configTab[3] = confProperties.getProperty("nbColors");
			

		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		return configTab;
		
	}
	
	public void countTurnDisplay(int actualTry, int tryMax) {
		if (actualTry+1 < tryMax) {
			System.out.println('\n'+"<- Nouveau tour -> ");
			System.out.println("- "+(actualTry+1)+'/'+tryMax+" - "+'\n');
		}
		else {
			System.out.println('\n'+"- Dernier tour - ");
			System.out.println("- "+(actualTry+1)+'/'+tryMax+" - "+'\n');
			
		}
	}
	
	
	
	
	

	
	
	
	
	
	
	
}
