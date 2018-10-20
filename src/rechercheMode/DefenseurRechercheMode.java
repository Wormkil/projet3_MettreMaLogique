package rechercheMode;



public class DefenseurRechercheMode extends RechercheModeParent{
	
	static boolean loop = true;
	static String nbRandom = "";
	static int currentTry = 1;
	static int playerInt = 0;
	static String[][] numberMinMaxTab;
	
	public DefenseurRechercheMode() {

		
		
		
		System.out.println("Veuillez me donnée un nombre, je vais essayer de le trouver !");
		System.out.println("Quand je vous donnerais un nombre, indiquez moi avec des signe +, -, ou = si je suis sur la bonne piste !");
		playerInt = Integer.parseInt(u.listenIntPlayer(nbCase));
		
		System.out.println("playerInt = "+playerInt);
		
		nbRandom = u.randomGenerator(nbCase);

		System.out.println("nbRandom = "+nbRandom);
		
		numberMinMaxTab = new String[nbCase][3];
		numberMinMaxTab = initTabIntMinMax(nbRandom);
		
		
		
		
		loopsDefenseurRecherche();
		
		
	}
	
	private static void loopsDefenseurRecherche() {
		
		while (loop) {
			
			if (oneLoopDefenseur(nbRandom)) {
				System.out.println("Oui, j'ai trouvé ! La solution était bien : " + nbRandom);
				loop = false;
			}
			else if (currentTry < nbTry ){
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
	
}
