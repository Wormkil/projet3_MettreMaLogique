package mastermindModes;

import utils.AnswerDuelMastermind;

public class ModeParent extends central.MastermindModesLauncher {

    protected static AnswerDuelMastermind compareTabMastermind(String secret, String guess) {

        int nbFind = 0;
        int nbWellPlaced = 0;
        int cpt = 0;
        int cpt2 = 0;


        for (char secretCharacter : secret.toCharArray()) {
            if (guess.contains(""+secretCharacter)) {
                nbFind++;

                cpt2 = 0;
                for (char guessedCharacter : guess.toCharArray()) {
                    if(secretCharacter == guessedCharacter && cpt == cpt2) {
                        nbFind--;
                        nbWellPlaced++;
                    }

                    cpt2++;
                }
            }
            cpt++;

        }
        return new AnswerDuelMastermind(nbFind,nbWellPlaced);

    }



    protected static boolean outOneLoopChallengeur(String intRandom) {
        boolean win = false;

        System.out.print("Proposition : ");
        String playerString = u.listenIntPlayer(nbCase);

        int strLength = playerString.length();


        AnswerDuelMastermind answer = compareTabMastermind(intRandom,playerString);

        System.out.println("Proposition : "+playerString+" -> R�ponse : "+answer.nbFind+" pr�sent, "+answer.nbWellPlaced+" bien plac�");

        if (answer.nbWellPlaced == strLength ) {
            win  = true;
        }

        return win;
    }

}
