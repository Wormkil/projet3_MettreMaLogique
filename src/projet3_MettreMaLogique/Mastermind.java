package projet3_MettreMaLogique;

import mastermindMode.ChallengerMasterMode;
import mastermindMode.DefenseurMasterMode;

public class Mastermind extends GameManagerParent {

	
	@Override
	public boolean lauchGame(String mode) {

		switch (mode) {
			case "Challenger" :
				ChallengerMasterMode obj = new ChallengerMasterMode();
				break;
			case "Defenseur" :
				DefenseurMasterMode  obj2 = new DefenseurMasterMode();
				break;
			case "Duel" :
				break;
		
		}
		return false;
		
	}
	
}
