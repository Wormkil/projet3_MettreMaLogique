package mastermindModes;

public class Duel extends Defenseur {
	
	
	boolean playerWin = false;
	boolean computerWin = false;
	Defenseur defenseurObject = new Defenseur();
	String nbRandom;
	
	public void initialisation(){
		
		defenseurObject.initialisation(false);
		
		nbRandom = "";
		for(int i = 0;i<nbCase;i++) {
			nbRandom += Math.round((Math.floor(Math.random() * (Integer.parseInt(intMax) - 0 +1)) + 0));
		}
		
		System.out.println("Bienvenue dans le mastermind mode Duel ! Nous allons jouer chacun notre tour ! Bonne chance");

		
		main();
	}
	
	protected void main(){
		
		int actualTry = 0;
		while(actualTry != tryMax && (!playerWin && !computerWin)) {
			
			u.countTurnDisplay(actualTry,tryMax);
			
			System.out.println("Je vous laisse tenter votre chance");
			playerWin = outOneLoopChallengeur(nbRandom);
			if (!playerWin) System.out.println("A mon tour");
			else System.out.println("C'est ma dernière chance");
			if (actualTry == 0) computerWin = outFirstLoopDefenseur(defenseurObject);
			else computerWin = outOneLoopDefenseur(defenseurObject);
			actualTry++;
		}
		
		if ( actualTry >= tryMax  )
		System.out.println("Nous n'avons plus d'essais disponible, nous avons tous les deux perdu ! Votre solution était "+nbRandom+'\n'+"Je devais trouver "+defenseurObject.secretCode);
		else if (computerWin == false && playerWin == true ) System.out.println("Vous avez gagné ! La solution était "+nbRandom);
		else if (computerWin == true && playerWin == false ) System.out.println("J'ai gagné ! La solution était "+defenseurObject.secretCode);
		else  System.out.println("Nous avons gagné en trouvant respectivement "+nbRandom+" et "+defenseurObject.secretCode+" !");
		
		
	}
	


}
