package gui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

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
	private JSlider speedSlider;
	private JSpinner stepSizeSpinner;
	private JButton toggleBtn;
	private JComboBox<DancePattern> combo;

	private ActionListener toggleBtnAction;
	
	public AppFrame() throws AWTException {
		ShortcutManager.setup(this);
		
		this.setTitle("Mouser dance party");
		this.setSize(280, 120);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel(new BorderLayout());
		this.add(panel); 
		 
		initToggleBtn();
		panel.add(toggleBtn, BorderLayout.PAGE_START);
		
		speedSlider = new JSlider(JSlider.HORIZONTAL, 150, 150000, 1000);
		speedSlider.setMajorTickSpacing(30000);
		speedSlider.setMinorTickSpacing(6000);
		speedSlider.setPaintTicks(true);
		panel.add(speedSlider, BorderLayout.LINE_START);
		
		SpinnerNumberModel stepSizeSpinnerModel = new SpinnerNumberModel(10, 1, 100, 1);
		stepSizeSpinner = new JSpinner(stepSizeSpinnerModel);
		panel.add(stepSizeSpinner, BorderLayout.LINE_END);

		initComboBox();
		panel.add(combo, BorderLayout.PAGE_END);
		
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
