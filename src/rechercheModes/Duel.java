package rechercheModes;



public class Duel extends ModeParent{
	
	static boolean loop = true;
	static int currentTry = 1;
	static String intAdverse = "";	
	static String intPlayer = "";
	static String nbRandom = "";
	static String[][] numberMinMaxTab = new String[nbCase][3];
	
	
	public Duel() {
		
		whosTurn = "(PlayerTurn) ";
		
		
		System.out.println("Bienvenue dans le mode duel ! Nous allons jouer l'un contre l'autre a tour de rôle.");
		intAdverse = u.randomGenerator(nbCase);
		System.out.println("J'ai choisis un nombre secrêt que vous devez devinez avant que je devine le votre. ");
		
		System.out.println("Quel est votre nombre secret ?");
		intPlayer = u.listenPlayer("int_"+nbCase+"_stop");
		nbRandom = u.randomGenerator(nbCase);
		System.out.println("Je vous laisse commencé, que proposez vous ?"+'\n');

		numberMinMaxTab = initTabIntMinMax(nbRandom);
		loop = true;
		loopsDuelRecherche();
		
		
	}
	
	
	private static void loopsDuelRecherche() {
		while (loop) {
					
			String result = oneLoopDuel(intAdverse,intPlayer,nbRandom );
			
			if (result.equals("playerWin")) {
				System.out.println("Bravo vous avez trouvé ! La solution est bien : " +intAdverse);
				loop = false;
			}
			else if (result.equals("adversaireWin")) {
				System.out.println("J'ai trouvé ! La solution est bien : " +intPlayer);
				loop = false;
			}
			else if (currentTry < tryMax ){
				currentTry++;
				numberMinMaxTab = changeNbRandom(numberMinMaxTab, playerString);
				nbRandom = tabIntToInt(numberMinMaxTab);
			}
			else {
				loop = false;
				System.out.println("Nous avons perdu ... La solution était : "+intAdverse+" pour vous, et je devais trouver : "+intPlayer);
			}
		}
		
		
		
	}
}
