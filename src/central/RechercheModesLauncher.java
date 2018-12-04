package central;

import central.GameManager;
import rechercheModes.Challenger;
import rechercheModes.Defenseur;
import rechercheModes.Duel;

public class RechercheModesLauncher extends GameManager{
	
	@Override
	public boolean lauchGame(String mode) {
		
		switch (mode) {
			case "Challenger" :
				Challenger obj = new Challenger();
				break;
			case "Defenseur" :
				Defenseur  obj2 = new Defenseur();
				break;
			case "Duel" :
				Duel  obj3 = new Duel();
				break;
		
		}
		return false;
		
	}
}
