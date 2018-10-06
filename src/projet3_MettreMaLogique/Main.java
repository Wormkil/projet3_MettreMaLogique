package projet3_MettreMaLogique;

import org.apache.logging.log4j.Logger; 
import org.apache.logging.log4j.LogManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import projet3_MettreMaLogique.GameManager;

public class Main {
	
	private static final Logger log4j = LogManager.getLogger(Main.class.getName());
	public static int prop_nbCase = 0;
	public static int prop_nbTry = 0;
	
	public static void main(String... args) {
		
		/*log4j.trace("This is a trace message."); 
		log4j.debug("This is a debug message."); 
		log4j.info("This is an info message."); 
		log4j.error("This is an error message");
		*/
		log4j.info("Program has been launch"); 
		log4j.info("Recup properties from config.properties"); 
		final Properties confProperties = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("src/config.properties");

			// load a properties file
			confProperties.load(input);


			
			prop_nbCase = Integer.parseInt(confProperties.getProperty("nbCase"));
			prop_nbTry = Integer.parseInt(confProperties.getProperty("nbTry"));
			
			//Small security against users
			if (prop_nbCase == 0) { prop_nbCase = 1; }
			if (prop_nbTry == 0) { prop_nbTry = 1; }
			

		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		log4j.info("Recup properties sucess");
		log4j.info("Call gameManager()");
		
		GameManager obj_gameManager = new GameManager(); 
		
		//boucle pour l'instant sur lui meme
		while (1==1) {
			// Choix du jeux et du mode
			log4j.info("Game choice --> "+obj_gameManager.choixPlayer());
			
			//Lancement du jeux et du mode

			obj_gameManager.gameRecherche(prop_nbCase,prop_nbTry);
			
		}
		
		
		
	}
	
	
}
