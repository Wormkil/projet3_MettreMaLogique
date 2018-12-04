package mastermindModes;

public class Duel extends Defenseur {
	
	public Duel() {
	
	System.out.println("Bienvenue dans le mastermind mode Duel ! Nous allons jouer chacun notre tour ! Bonne chance");
	System.out.println("Je vous aiderais en vous indiquant le nombre de chiffre trouv� et bien plac� ");
	
	String nbRandom = u.randomGenerator(nbCase);
	System.out.println("(dev mode) nbRandom = "+nbRandom);
	
	loopAndCheckWinMastermind(nbRandom,nbTry);
}
	

private static void loopAndCheckWinMastermind(String nbRandom, int nbTry) {
	int cpt = 0;
	boolean haveWin = false;
	
	while ( cpt < nbTry && haveWin == false) {
		if (outOneLoopChallengeur(nbRandom)){ haveWin = true; }
		else { haveWin = false; }
		cpt++;
	}
	
	if (haveWin) {
		System.out.println("Bravo vous avez trouv� ! La solution �tait bien "+nbRandom);
	}
	else {
		System.out.println("Vous avez perdu ... La solution �tait "+nbRandom);
	}
}

}
