package central;

import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import utils.Utils;

public class GameManager {
	
	public static final Logger log4j = LogManager.getLogger(GameManager.class.getName());
	public static Utils u = new Utils();
	public Scanner keyboard = new Scanner(System.in);
	
	protected static String gameChoice;
	protected static String modeChoice;
	
	public static String[] configTab = u.getConfig();
	public static int nbCase = Integer.parseInt(configTab[0]);
	public static int nbTry = Integer.parseInt(configTab[1]);
	public static String mode = configTab[2];
	public static String devMode;

	public boolean lauchGame(String mode) {
		log4j.debug("je suis pas un jeux");
		return false;
		
	}
	
    protected boolean checkWin(int tmpI, int currentNbTry){
        boolean win = true;
        if (tmpI != nbCase) win = false;
        if (currentNbTry > nbTry) win = false;
        return win;
    }
	
}

	
