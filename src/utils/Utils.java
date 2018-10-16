package utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
	
	private static final Logger log4j = LogManager.getLogger(Utils.class.getName());
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
	
	public String listenIntPlayer(int nbCase) {
		keyboard = new Scanner(System.in);
		String playerOut = keyboard.nextLine();
		
		while (playerOut.length() != nbCase || !stringIsInt(playerOut) ) { 
			System.out.println("Saisie Int incorrecte, veuillez recommencer !");
			playerOut = keyboard.nextLine();
		}
		return playerOut;
	}
	
	public boolean stringIsInt(String string) {
		try {
			Integer.parseInt(string);
			return true;
			
		} catch (NumberFormatException ex) {
			return false;
		} 
	}
	
	public String listenStringPlayer(int nbCase, boolean bool) {
		keyboard = new Scanner(System.in);
		String playerOut = keyboard.nextLine();
		
		while (playerOut.length() != nbCase || !correctFormat(playerOut) ) { 
			
			
			System.out.print("Saisie String incorrecte, veuillez recommencer ! ");
			
			if (playerOut.length() < nbCase) {
				System.out.print("Pas assez de charactère. ");
			}
			else if (playerOut.length() > nbCase) {
				System.out.print("Trop de charactère. ");
			} 
			
			if (!correctFormat(playerOut)) {
				System.out.print("Charactère interdit. ");
			}
			System.out.print('\n');
			playerOut = keyboard.nextLine();
		}
		return playerOut;
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
		String[] configTab = new String[3];
		
		
		log4j.info("Recup properties from config.properties"); 
		final Properties confProperties = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("src/config.properties");

			confProperties.load(input);
			
			configTab[0] = confProperties.getProperty("nbCase");
			configTab[1] = confProperties.getProperty("nbTry");
			configTab[2] = confProperties.getProperty("devMode");
			

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
		
		log4j.info("Recup properties sucess");
		return configTab;
		
	}
	

	
	
	
	
	
	

	
	
	
	
	
	
	
}
