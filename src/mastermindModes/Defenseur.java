package mastermindModes;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utils.AnswerDuelMastermind;

public class Defenseur extends ModeParent {


    private String firstGuess = "";
    private boolean win = false;
    private List<String> solutionToRemove  = new ArrayList<>();
    private List<String> solutionList = new ArrayList<>();
    private List<String> unuseds = new ArrayList<>();
    private List<AnswerDuelMastermind> answersPossible = new ArrayList<>();


    //Coté objet
	private String actualGuess;
	private AnswerDuelMastermind currentAnswer;
    
    public void initialisation(){

        String lastSolution = "";
        for (int i = 0; i<nbCase; i++) {
            lastSolution += "9";
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

        System.out.println("Bienvenue dans le mastermind mode defenseur ! Choississez un nombre et j'essayerai de le retrouver.");
        System.out.println("Je vous laisse taper un nombre, pour que vous puissiez le retrouver si vous l'oublié.");
        u.listenIntPlayer(nbCase);
        System.out.println("Aidez moi en indiquant le nombre de chiffre trouvé et bien placé.");

        main();
    }

    protected void main(){

        //Step 2 - Set a precise first guess (1122) to improve code break.
    	setFirstGuess();

        while ( !win) {
            nbTry++;

            //Step 3 - Wait response from player and compile it in Answer Object
            guessAndCompileAnswers(actualGuess);

            //Step 4 - Check if its a win guess
            win = checkWin(currentAnswer.nbWellPlaced, nbTry );

            //Step 5 - Remove from 'S' any code that would not give the same response if the current guess were the code.
            solutionToRemove = getSolutionsToRemove(solutionList, actualGuess, currentAnswer);
            solutionList.removeAll(solutionToRemove);

            //Step 6 - Find a next Guess with minMax technique
            //String nextGuess = unuseds.get(0); //--> Protection to be sur nextGuess is never null or something else
            //unuseds.remove(nextGuess);
            actualGuess = findNextGuess(unuseds, solutionList, answersPossible);
            
            
        }

        System.out.println("You have win !");

    }

    private void setFirstGuess() {
    	
        while (firstGuess.length() < nbCase/2) firstGuess += "1";
        while (firstGuess.length() < nbCase) firstGuess += "2";
        actualGuess = firstGuess;

    }
    
    private void guessAndCompileAnswers(String guess) {
        System.out.println("--> Je pense que votre nombre est : "+guess);
        List<String> playerAnswerList = compilePlayerAnswer(guess);
        currentAnswer = new AnswerDuelMastermind(Integer.parseInt(playerAnswerList.get(1)), Integer.parseInt(playerAnswerList.get(2)));
    }
    
	private String findNextGuess(List<String> unuseds, List<String> remainingSolutions, List<AnswerDuelMastermind> answersPossible) {

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

	private Optional<String>  getNextGuess(Set<Map.Entry<String, Integer>> maxByUnused){
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

        System.out.println("Chiffre présent : ");
        String nbFind = u.listenIntPlayer(1);
        System.out.println("Chiffre bien placé : ");
        String nbPlaced = u.listenIntPlayer(1);

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
            /*if (!a.equals(currentAnswer)) {
                solutionToRemove.add(solution);
            }*/
        }

        return solutionToRemove;

    }


































    protected List<String> fillNoIntList(List<String> noIntPresent, String intToFill) {
        for (int i=0; i<nbCase; i++) {
            String tmpStr = String.valueOf(intToFill.charAt(i));
            if (!noIntPresent.contains(tmpStr)) noIntPresent.add(String.valueOf(tmpStr));
        }
        //log4j.debug(" new List  = "+noIntPresent);
        return noIntPresent;
    }










}