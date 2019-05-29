package gui;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dance.DanceFactory;
import dance.DancePattern;
import dance.MouseMover;


public class AppFrame extends JFrame {
	private static final long serialVersionUID = -8983175489282925162L;

	private static final Logger logger = LoggerFactory.getLogger(AppFrame.class);
	
	private MouseMover mover;
	
	private JPanel panel;
	private JButton toggleBtn;
	private JComboBox<DancePattern> combo;

	private ActionListener toggleBtnAction;
	
	public AppFrame() throws AWTException {
		ShortcutManager.setup(this);
		
		this.setTitle("Mouser dance party");
		this.setSize(280,70);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.panel = new JPanel();
		this.add(panel); 
		 
		initToggleBtn();
		this.panel.add(toggleBtn);

		initComboBox();
		this.panel.add(combo);
		
		mover = new MouseMover(10,  1000, getComboboxItem());
	}
	
	private DancePattern getComboboxItem() {
		return combo.getItemAt(combo.getSelectedIndex());
	}
	
	private void initToggleBtn() {
		toggleBtn = new JButton("let's dance");
		initToggleBtnAction();
		toggleBtn.addActionListener(toggleBtnAction);
	}

	private void initToggleBtnAction() {
		toggleBtnAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.debug("toggle the dance");
				
				if(mover.isDance()) {
					toggleBtn.setText("let's dance again");
				}
				else {
					mover.setDancePattern(getComboboxItem());
					toggleBtn.setText("stop dancing");
				}
				
				mover.setDance(!mover.isDance());				
			}
		};
	}
	
	public JButton getToggleBtn() {
		return this.toggleBtn;
	}
	
	private void initComboBox() throws AWTException {
		combo = new JComboBox<DancePattern>(new DancePatternComboboxModel());
		
		List<DancePattern> styleList = DanceFactory.getAvailableStyles();
		Collections.sort(styleList, (s1, s2) -> s1.toString().compareTo(s2.toString()));
		
		styleList.forEach(item -> combo.addItem(item));
		
		combo.setSelectedIndex(0);
	}
}
