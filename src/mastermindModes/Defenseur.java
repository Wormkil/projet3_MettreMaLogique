package mastermindModes;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utils.AnswerDuelMastermind;

public class Defenseur extends ModeParent {

	protected int actualTry = 0;
	protected String firstGuess = "";
    protected boolean win = false;
    protected List<String> solutionToRemove  = new ArrayList<>();
    protected List<String> solutionList = new ArrayList<>();
    protected List<String> unuseds = new ArrayList<>();
    protected List<AnswerDuelMastermind> answersPossible = new ArrayList<>();
    protected String secretCode = "";

    protected String actualGuess;
	protected AnswerDuelMastermind currentAnswer;
    
    public void initialisation(boolean launchMain){

        String lastSolution = "";
        for (int i = 0; i<nbCase; i++) {
            lastSolution += intMax;
        }
        int nbSolution = Integer.parseInt(lastSolution);

        solutionList = IntStream.range(0,nbSolution)
                .mapToObj(nomVariable -> String.format("%0"+nbCase+"d",nomVariable))
                .collect(Collectors.toList());

        unuseds = new ArrayList<>(solutionList);
        answersPossible = new ArrayList<>();

        for (int i = 0; i<= nbCase; i++) {
            for (int j = 0;j <= nbCase-i;j++) {
                answersPossible.add(new AnswerDuelMastermind(i,j));
            }
        }
        
        //Step 2 - Set a precise first guess (1122) to improve code break.
    	setFirstGuess();
    	System.out.println("Bienvenue dans le mastermind !");
        System.out.println("Je vous laisse taper votre code secret, pour que vous puissiez le retrouver si vous l'oublié.");
        secretCode = u.listenPlayer("int_"+nbCase+"_stop");
        
        if (launchMain) main();
        
    }

    protected void main(){
    	
    	System.out.println("Le mode defenseur va maintenant commencer ! Je vais essayer de retrouver votre code secret.");
        System.out.println("Aidez moi en indiquant le nombre de chiffre trouvé et bien placé.");

        while (!win && actualTry < tryMax) {
        	
        	u.countTurnDisplay(actualTry,tryMax);
        	actualTry++;
        	
            guessAndCompileAnswers(actualGuess);

            win = checkWin(currentAnswer.nbWellPlaced, actualTry );

            solutionToRemove = getSolutionsToRemove(solutionList, actualGuess, currentAnswer);
            solutionList.removeAll(solutionToRemove);

            actualGuess = findNextGuess(unuseds, solutionList, answersPossible);
            
        }
        if (actualTry == tryMax) {
        	System.out.println("Je n'ai pas réussis à trouver la solution, je devais trouver : "+secretCode);
        }
        else {
        	System.out.println("J'ai gagné, la solution était bien : "+secretCode);
        }

    }

    protected void setFirstGuess() {
    	
        while (firstGuess.length() < nbCase/2) firstGuess += "1";
        while (firstGuess.length() < nbCase) firstGuess += "2";
        actualGuess = firstGuess;

    }
    
    protected void guessAndCompileAnswers(String guess) {
        System.out.println("Je pense que votre nombre est : "+guess);
        List<String> playerAnswerList = compilePlayerAnswer(guess);
        currentAnswer = new AnswerDuelMastermind(Integer.parseInt(playerAnswerList.get(1)), Integer.parseInt(playerAnswerList.get(2)));
    }
    
	protected String findNextGuess(List<String> unuseds, List<String> remainingSolutions, List<AnswerDuelMastermind> answersPossible) {

        Map<String, Integer> maxByUnused = new HashMap<>();
		for(String unused : unuseds) {
            Integer maxRemoved = answersPossible.stream()
                .map(answer -> getSolutionsToRemove(remainingSolutions, unused, answer).size())
                    .max(Comparator.comparing(Integer::valueOf))
                    .orElse(0);


            maxByUnused.put(unused, maxRemoved);
		}

        List<Map.Entry<String, Integer>> unusedInRemainingSolutions = maxByUnused.entrySet()
            .stream().filter(tuple -> remainingSolutions.contains(tuple.getKey()))
            .collect(Collectors.toList());

        Set<Map.Entry<String, Integer>> tmp = new HashSet<>(unusedInRemainingSolutions);

        final Optional<String> maybeNextGuess = getNextGuess(tmp);

        final Optional<String> ma2variable = getNextGuess(maxByUnused.entrySet());

        return maybeNextGuess.or( () -> ma2variable).orElseThrow();
	}

	protected Optional<String>  getNextGuess(Set<Map.Entry<String, Integer>> maxByUnused){
        int nextGuessMax = Integer.MAX_VALUE;
        Optional<String> nextGuess = Optional.empty();
        for (Map.Entry<String, Integer> mapEntry : maxByUnused) {
            if (mapEntry.getValue() < nextGuessMax) {
                nextGuessMax = mapEntry.getValue();
                nextGuess = Optional.of(mapEntry.getKey());
            }
        }
        return nextGuess;
    }

	protected List<String> compilePlayerAnswer(String proposition) {
		
        if (devMode.equals("1")) {
        	AnswerDuelMastermind tmpAnswer = compareTabMastermind(secretCode, proposition); 
        	System.out.println("(dev Mode) Votre code secret : "+secretCode
        			+" / Nombre de solutions restante : "+solutionList.size()+" "
        			+ "/ Réponse attendu : Nombre présent = "+tmpAnswer.nbFind+" Nombre bien placé =  "+tmpAnswer.nbWellPlaced);
        }
        
        System.out.println("Chiffre présent : ");
        String nbFind = u.listenPlayer("int_1_0_"+nbCase);
        System.out.println("Chiffre bien placé : ");
        String nbPlaced = u.listenPlayer("int_1_0_"+nbCase);
        System.out.println("");
        
        List<String> myList  = Arrays.asList(proposition,nbFind,nbPlaced);
        return myList;

    }

	protected List<String> getSolutionsToRemove(List<String> solutions, String currentGuess, AnswerDuelMastermind currentAnswer) {

        List<String> solutionToRemove  = new ArrayList<>();

        for(String solution : solutions) {
            AnswerDuelMastermind a = compareTabMastermind(solution, currentGuess);

            if (!(a.nbWellPlaced == currentAnswer.nbWellPlaced && a.nbFind == currentAnswer.nbFind )) {
                solutionToRemove.add(solution);
            }
        }

        return solutionToRemove;

    }


    protected List<String> fillNoIntList(List<String> noIntPresent, String intToFill) {
        for (int i=0; i<nbCase; i++) {
            String tmpStr = String.valueOf(intToFill.charAt(i));
            if (!noIntPresent.contains(tmpStr)) noIntPresent.add(String.valueOf(tmpStr));
        }
        return noIntPresent;
    }










}