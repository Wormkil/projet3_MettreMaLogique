package rechercheMode;



public class DuelRechercheMode extends RechercheModeParent{
	
	static boolean loop = true;
	static int currentTry = 1;
	static String intAdverse = "";	
	static String intPlayer = "";
	static String nbRandom = "";
	static String[][] numberMinMaxTab = new String[nbCase][3];
	
	
	public DuelRechercheMode() {
		
		whosTurn = "(PlayerTurn) ";
		
		
		System.out.println("Bienvenue dans le mode duel ! Nous allons jouer l'un contre l'autre a tour de r�le.");
		intAdverse = u.randomGenerator(nbCase);
		System.out.println("J'ai choisis un nombre secr�t que vous devez devinez avant que je devine le votre. ");
		
		System.out.println("Quel est votre nombre secret ?");
		intPlayer = u.listenIntPlayer(nbCase);
		nbRandom = u.randomGenerator(nbCase);
		System.out.println("Je vous laisse commenc�, que proposez vous ?"+'\n');

		numberMinMaxTab = initTabIntMinMax(nbRandom);
		
		loopsDuelRecherche();
		
		
	}
	
	
	private static void loopsDuelRecherche() {
		log4j.debug("avant la boucle");
		while (loop) {
			log4j.debug("avant le if");
			
			String result = oneLoopDuel(intAdverse,intPlayer,nbRandom );
			
			if (result.equals("playerWin")) {
				System.out.println("Bravo vous avez trouv� ! La solution est bien : " +intAdverse);
				loop = false;
			}
			else if (result.equals("adversaireWin")) {
				System.out.println("J'ai trouv� ! La solution est bien : " +intPlayer);
				loop = false;
			}
			else if (currentTry < nbTry ){
				currentTry++;
				log4j.debug("playerString : "+playerString);
				numberMinMaxTab = changeNbRandom(numberMinMaxTab, playerString);
				nbRandom = tabIntToInt(numberMinMaxTab);
			}
			else {
				loop = false;
				System.out.println("Nous avons perdu ... La solution �tait : "+intAdverse+" pour vous, et je devais trouver : "+intPlayer);
			}
		}
		
		
		
	}
}
