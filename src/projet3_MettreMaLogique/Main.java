package projet3_MettreMaLogique;

import org.apache.logging.log4j.Logger; 
import org.apache.logging.log4j.LogManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
	
	private static final Logger log4j = LogManager.getLogger(Main.class.getName());
	
	public static void main(String... args) {
		
		log4j.trace("This is a trace message."); 
		log4j.debug("This is a debug message."); 
		log4j.info("This is an info message."); 
		log4j.error("This is an error message");
		
		
		final Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("src/config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("nbCase"));
			System.out.println(prop.getProperty("nbTry"));
			

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
		
	}
	
	
}
