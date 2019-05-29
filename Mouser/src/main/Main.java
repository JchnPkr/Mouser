package main;

import java.awt.AWTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gui.AppFrame;


public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		logger.debug("Start main");
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AppFrame app;
				try {
					app = new AppFrame();
					app.setVisible(true);
				} 
				catch (AWTException e) {
					logger.error(e.getMessage());
				}
			}
		}); 
	}
}
