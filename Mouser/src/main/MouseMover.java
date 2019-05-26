package main;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MouseMover implements ActionListener {
	private static final Logger logger = LoggerFactory.getLogger(MouseMover.class);
	
	private Robot robot;
	private Point pointer;
	private int stepsize;
	private boolean dance;
	Timer timer = new Timer(1000, this);

	public MouseMover(int stepsize) throws AWTException {		
		robot = new Robot();
		this.stepsize = stepsize;
		this.dance = false;
	}

	private void move() throws InterruptedException {
		logger.debug("robot dance...");
		
		pointer = MouseInfo.getPointerInfo().getLocation();			
		robot.mouseMove((int) pointer.getX() + stepsize, (int) pointer.getY());
		stepsize = -stepsize;
	}
	
	public boolean isDance() {
		return dance;
	}

	public void setDance(boolean dance) {
		this.dance = dance;
		
		if(dance) {
			timer.start();
		}
		else {
			timer.stop();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			move();
		} catch (InterruptedException e1) {
			logger.error(e1.getMessage());
		}
	}
}
