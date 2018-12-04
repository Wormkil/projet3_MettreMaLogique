package central;

import mastermindModes.Challenger;
import mastermindModes.Defenseur;
import mastermindModes.Duel;

public class MastermindModesLauncher extends GameManager {


    @Override
    public boolean lauchGame(String mode) {

        switch (mode) {
            case "Challenger" :
                Challenger obj = new Challenger();
                break;
            case "Defenseur" :
                Defenseur  obj2 = new Defenseur();
                obj2.initialisation();
                break;
            case "Duel" :
            	Duel  obj3 = new Duel();
                //obj3.initialisation();
                break;

        }
        return false;

    }

}
