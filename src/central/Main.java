package central;

import org.apache.logging.log4j.Logger;
import central.GameManager;

import central.MastermindModesLauncher;
import central.MainMenu;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import utils.Utils;

public class Main {
	
	protected static final Logger log4j = LogManager.getLogger(Main.class.getName());
	protected static Utils u = new Utils();
	
	public static int prop_nbCase = 0;
	public static int prop_nbTry = 0;
	
	private static List<String> games = Arrays.asList("Recherche + ou -","Mastermind");
	private static List<String> modes = Arrays.asList("Challenger","Defenseur","Duel");
	
	public static void main(String... args) {
		
		MainMenu menu = new MainMenu();
		menu.setSettings(games, modes);
		
		String gameAndMode = menu.setGameAndMode();
		
		while(!gameAndMode.equals("")) {
			GameManager gameChoice = new GameManager();
			
			String game = gameAndMode.split("~")[0];
			String mode = gameAndMode.split("~")[1];
			
			switch (game) {
				case "Recherche + ou -" :
					gameChoice = new RechercheModesLauncher();
					break;
				case "Mastermind" :
					gameChoice = new MastermindModesLauncher();
					break;
				default :
					log4j.debug("Erreur dans le menu du choix du jeux");
					break;
			}
			
			gameChoice.lauchGame(mode);
			
			gameAndMode = menu.setEndGame();
		}
		
	}

}			
		
	