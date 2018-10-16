package projet3_MettreMaLogique;

import org.apache.logging.log4j.Logger; 
import org.apache.logging.log4j.LogManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import projet3_MettreMaLogique.GameManagerParent;
import projet3_MettreMaLogique.Mastermind;
import utils.Utils;

public class Main {
	
	protected static final Logger log4j = LogManager.getLogger(Main.class.getName());
	protected static Utils u = new Utils();
	
	public static int prop_nbCase = 0;
	public static int prop_nbTry = 0;
	
	public static void main(String... args) {

		choiceMenu();
		
		
		
		boolean infiny = true;
		while (infiny) {
			
			
			
			System.out.println( "============================================================="+'\n'+'\n'+
					
								"Que voulez vous faire ?"+'\n'+
				
								"1° Rejouer"+'\n'+
								"2° Changer de jeux"+'\n'+
								"3° Quitter"+'\n'+'\n'+
								
								"=============================================================");
			
			
			
			
			
			
			
			switch (u.listenIntPlayer(1)) {
				case "1" :
					break;
				case "2" :
					System.out.println(" - Retour au menu");
					choiceMenu();
					break;
				case "3" :
					System.out.println(" - Exit");
					System.exit(0);
					break;
				default :
					log4j.debug("Erreur dans le menu du choix du jeux");
					break;
			}
		}	
	}

	private static void choiceMenu() {
		
		
		GameManagerParent gameChoice = new GameManagerParent();
		String modeChoice = "Challenger";
		
		
		System.out.println( "============================================================="+'\n'+'\n'+
				
				"Bienvenu dans cette compilation de jeux"+'\n'+
				"Vous pouvez jouer à deux jeux, choisissez en tapant"+'\n'+
				"1 ou 2, puis choississez le mode de la même manière :"+'\n'+'\n'+
				
				"1° Recherche + ou -"+'\n'+
				"2° Mastermind"+'\n'+'\n'+
				
				"============================================================="
		);
		
		switch (u.listenIntPlayer(1)) {
			case "1" :
				gameChoice = new Recherche();
				break;
			case "2" :
				gameChoice = new Mastermind();
				break;
			default :
				log4j.debug("Erreur dans le menu du choix du jeux");
				break;
		}
		
		System.out.println( "============================================================="+'\n'+'\n'+
			
						"Boujours, bienvenu dans cette compilation de jeux"+'\n'+
						"Vous pouvez jouer à deux jeux, choisissez en tapant"+'\n'+
						"1 ou 2, puis choississez le mode de la même manière :"+'\n'+'\n'+
						
						"1° Challenger"+'\n'+
						"2° Defenseur"+'\n'+
						"3° Duel"+'\n'+'\n'+
						
						"============================================================="
		);
		switch (u.listenIntPlayer(1)) {
			case "1" :
				modeChoice = "Challenger";
				break;
			case "2" :
				modeChoice = "Defenseur";
				break;
			case "3" :
				modeChoice = "Duel";
				break;
			default :
				log4j.debug("Erreur dans le menu du choix du jeux");
				break;
		}
		
		gameChoice.lauchGame(modeChoice);
				
	}

}			
		
	