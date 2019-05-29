package dance;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Iterables;


public class MouseMover implements ActionListener {
	private static final Logger logger = LoggerFactory.getLogger(MouseMover.class);
	
	private Point pointer;
	private Timer timer;
	private Robot robot;
	
	private int stepsize;
	private Iterator<Step> stepIterator;
	private boolean dance;

	public MouseMover(int stepsize, int speedInMs, DancePattern pattern) throws AWTException {	
		timer = new Timer(speedInMs, this);
		robot = new Robot();
		this.stepsize = stepsize;
		this.stepIterator = Iterables.cycle(pattern.getDancePattern()).iterator();
		this.dance = false;
	}

	private void move() throws InterruptedException {
		logger.debug("robot dance...");
		
		Step step = new Step();
		
		if(stepIterator.hasNext()) {
			step = stepIterator.next();
		}
		
		pointer = MouseInfo.getPointerInfo().getLocation();			
		robot.mouseMove(getX(step), getY(step));
	}

	private int getX(Step step) {
		return (int) pointer.getX() + step.getX() * stepsize;
	}
	
	private int getY(Step step) {
		return (int) pointer.getY() + step.getY() * stepsize;
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
	
	public void setDancePattern(DancePattern pattern) {
		this.stepIterator = Iterables.cycle(pattern.getDancePattern()).iterator();
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
