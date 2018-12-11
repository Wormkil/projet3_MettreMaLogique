package central;

import java.util.ArrayList;
import java.util.List;

import utils.Utils;

public class MainMenu {
	//Fonction qui dysplay le menu
	
	private List<String> games = new ArrayList<>();
	private List<String> modes = new ArrayList<>();
	
	public static Utils u = new Utils();
	public static String devMode = u.getConfig()[2];
	
	private String gameChoice = "";
	private String modeChoice = "";
	
	protected void setSettings(List<String> games, List<String> modes) {
		this.games = games;
		this.modes = modes;
		
	}
	
	
	private void displayChoiceGame() {
		System.out.println(
				"============================================================="+'\n'+'\n'+
				
				"Bienvenu dans cette compilation de jeux"+'\n'+
				"Vous pouvez jouer à "+games.size()+" jeux, choisissez en tapant"+'\n'+
				"le chiffre correspondant, puis choississez le mode de la même manière :"+'\n');		
			
		int cpt = 1;
		for (String game : games) {
			System.out.println(cpt+"° "+game);
			cpt++;
		}
		
		if (devMode.equals("1")) {
			System.out.println(
					'\n'+
					"   Dev Mode : ON");
		}
		
	}
	
	private void displayChoiceModes(int gameNumber) {
		System.out.println(
				"============================================================="+'\n'+'\n'+
				
				"Vous pouvez jouer à "+modes.size()+" modes, choisissez en tapant"+'\n'+
				"le chiffre correspondant :"+'\n');		
			
		int cpt = 1;
		System.out.println("-> "+games.get(cpt-1)+" :");
		for (String mode : modes) {
			System.out.println(""+'\t'+cpt+"° "+mode);
			cpt++;
		}

	}
	
	private void displayEndGame() {
		System.out.println(
				"============================================================="+'\n'+'\n'+
				
				"Partie terminé, que voulez vous faire ?"+'\n'+'\n'+
				
				"1° : Rejouer"+'\n'+
				"2° : Changer de jeu"+'\n'+
				"3° : Quitter le programme"+'\n'+'\n'+
				"=============================================================");		
	}
	
	
	private String choiceGame() {
		return gameChoice = games.get(Integer.parseInt(u.listenPlayer("int_1_1_"+games.size()))-1); 
	}
	
	private String choiceModes() {
		return modeChoice = modes.get(Integer.parseInt(u.listenPlayer("int_1_1_"+modes.size()))-1);
	}
	
	protected String setEndGame() {
		displayEndGame();
		
		String gameAndMode = "";
		switch (u.listenPlayer("int_1_1_3")) {
			case "1":
				gameAndMode = gameChoice+"~"+modeChoice;
				break;
			case"2":
				gameAndMode = setGameAndMode();
				break;
			case"3":
				System.exit(0);
				break;
		}
		return gameAndMode;
	}
	
	
	protected String setGameAndMode() {
		String gameAndMode = "";
		
		while(gameAndMode.equals("")) {
			displayChoiceGame();
			gameAndMode += choiceGame();
			System.out.println('\n'+"=============================================================");
			displayChoiceModes(games.indexOf(gameAndMode)+1);
			gameAndMode += "~";	
			gameAndMode += choiceModes();
			System.out.println('\n'+"=============================================================");
		}
		System.out.println("Vous venez de choisir : "+gameAndMode);
		int cpt = 0;
		while(cpt<10) {
			System.out.println("");
			cpt++;
		}
		
		return gameAndMode;
		
	}
	
}
