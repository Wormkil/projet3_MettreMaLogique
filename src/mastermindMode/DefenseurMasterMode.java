package mastermindMode;

public class DefenseurMasterMode extends MastermindModeParent {
	
	
	public DefenseurMasterMode() {
		
		System.out.println("Bienvenue dans le mastermind mode defenseur ! Choississez un nombre et j'essayerai de le retrouver.");
		System.out.println("Aidez moi en indiquant le nombre de chiffre trouvé et bien placé ");
		
		int nbRandom = Integer.parseInt(u.listenIntPlayer(nbCase));
		System.out.println("(dev mode) nbRandom = "+nbRandom);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}