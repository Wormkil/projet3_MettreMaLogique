package mastermindModes;

import utils.AnswerDuelMastermind;
import mastermindModes.Defenseur;

public class ModeParent extends central.MastermindModesLauncher {

    protected String intMax = u.getConfig()[3];

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

        
        if (devMode.equals("1")) System.out.println("(devMode) Nombre à deviner : "+intRandom);
        System.out.print("Proposition : ");
        String playerString = u.listenPlayer("int_"+nbCase+"_stop");

        int strLength = playerString.length();


        AnswerDuelMastermind answer = compareTabMastermind(intRandom,playerString);

        System.out.println("Proposition : "+playerString+" -> Réponse : "+answer.nbFind+" présent, "+answer.nbWellPlaced+" bien placé");
        System.out.println("");
        
        if (answer.nbWellPlaced == strLength ) {
            win  = true;
        }

        return win;
    }
    
    protected static boolean outOneLoopDefenseur(Defenseur defenseurObject) {
    	
        
    	String actualGuess = defenseurObject.findNextGuess(defenseurObject.unuseds, defenseurObject.solutionList, defenseurObject.answersPossible);
    	
    	defenseurObject.guessAndCompileAnswers(actualGuess);
    	
    	if( !defenseurObject.checkWin(defenseurObject.currentAnswer.nbWellPlaced, tryMax )) {
    		defenseurObject.solutionToRemove = defenseurObject.getSolutionsToRemove(defenseurObject.solutionList, actualGuess, defenseurObject.currentAnswer);
    		defenseurObject.solutionList.removeAll(defenseurObject.solutionToRemove);
    		defenseurObject.actualGuess = defenseurObject.findNextGuess(defenseurObject.unuseds, defenseurObject.solutionList, defenseurObject.answersPossible);
    		
    		return false;
    	}
    	return true;
    }
    
    protected static boolean outFirstLoopDefenseur(Defenseur defenseurObject) {
    	
    	String actualGuess = defenseurObject.firstGuess;
    	defenseurObject.guessAndCompileAnswers(actualGuess);
    	
    	if( !defenseurObject.checkWin(defenseurObject.currentAnswer.nbWellPlaced, tryMax )) {
    		defenseurObject.solutionToRemove = defenseurObject.getSolutionsToRemove(defenseurObject.solutionList, actualGuess, defenseurObject.currentAnswer);
    		defenseurObject.solutionList.removeAll(defenseurObject.solutionToRemove);
    		defenseurObject.actualGuess = defenseurObject.findNextGuess(defenseurObject.unuseds, defenseurObject.solutionList, defenseurObject.answersPossible);
    		
    		return false;
    	}
    	return true;
    	
    }

}
