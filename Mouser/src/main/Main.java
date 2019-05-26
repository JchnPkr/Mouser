package main;

import java.awt.AWTException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		logger.debug("Start main");
		
		try {
			MouseMover mover = new MouseMover(10);
			
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
				    AppFrame app = new AppFrame(mover);
				}
			});
		} 
		catch (AWTException e ) {
			logger.error(e.getMessage());
		} 
	}
}
