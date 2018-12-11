package rechercheModes;

import java.util.ArrayList;
import java.util.List;

public class Defenseur extends ModeParent{
	
	static boolean loop = true;
	static String nbRandom = "";
	static int currentTry = 1;
	static int playerInt = 0;
	static String[][] numberMinMaxTab;
	static List<String> sollutionList = new ArrayList<>();
	
	public Defenseur() {
		
		System.out.println("Veuillez me donnée un nombre, je vais essayer de le trouver !");
		System.out.println("Quand je vous donnerais un nombre, indiquez moi avec des signe +, -, ou = si je suis sur la bonne piste !");
		playerInt = Integer.parseInt(u.listenPlayer("int_"+nbCase+"_stop"));
		
		nbRandom = u.randomGenerator(nbCase);
		
		numberMinMaxTab = new String[nbCase][3];
		numberMinMaxTab = initTabIntMinMax(nbRandom);
		
		loopsDefenseurRecherche();
		
	}
	
	
	private static void loopsDefenseurRecherche() {
		
		while (loop) {
			
			if (oneLoopDefenseur(nbRandom,Integer.toString(playerInt))) {
				System.out.println("Oui, j'ai trouvé ! La solution était bien : " + nbRandom);
				loop = false;
			}
			else if (currentTry < tryMax ){
				numberMinMaxTab = changeNbRandom(numberMinMaxTab, playerString);
				nbRandom = tabIntToInt(numberMinMaxTab);
				currentTry++;
			}
			else {
				loop = false;
				System.out.println("Mince j'ai perdu ... La solution était : "+Integer.toString(playerInt)+'\n'+"Mais vous pouvez recommencer encore et encore !"+'\n'+'\n'+'\n');
			}
		}
		
	}
	
	public static String[][] getNumberMinMaxTab(){
		return numberMinMaxTab;
	}
	
	public static String getintPlayer(){
		return Integer.toString(playerInt);
	}
}
