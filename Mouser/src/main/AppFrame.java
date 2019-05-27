package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AppFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = -8983175489282925162L;

	private static final Logger logger = LoggerFactory.getLogger(AppFrame.class);

	private JButton toggleBtn;
	private MouseMover mover;
	
	public AppFrame(MouseMover mover) {
		this.mover = mover;
		
		this.setTitle("Mouser dance party");
		this.setSize(280,100);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		toggleBtn = new JButton("let's dance");
		toggleBtn.addActionListener(this);
		this.add(toggleBtn);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logger.debug("toggle the dance");
		
		if(mover.isDance()) {
			toggleBtn.setText("let's dance again");
		}
		else {
			toggleBtn.setText("stop dancing");
		}
		
		mover.setDance(!mover.isDance());
	}
}
