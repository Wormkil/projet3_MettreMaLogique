package projet3_MettreMaLogique;

import rechercheMode.ChallengerRechercheMode;
import rechercheMode.DefenseurRechercheMode;
import rechercheMode.DuelRechercheMode;
import projet3_MettreMaLogique.GameManagerParent;

public class Recherche extends GameManagerParent{
	@Override
	public boolean lauchGame(String mode) {
		
		switch (mode) {
			case "Challenger" :
				ChallengerRechercheMode obj = new ChallengerRechercheMode();
				break;
			case "Defenseur" :
				DefenseurRechercheMode  obj2 = new DefenseurRechercheMode();
				break;
			case "Duel" :
				DuelRechercheMode  obj3 = new DuelRechercheMode();
				break;
		
		}
		return false;
		
	}
}
