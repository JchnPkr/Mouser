package gui;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	private JLabel sliderLabel;
	private JSpinner stepSizeSpinner;
	private JLabel spinnerLabel;
	private JButton toggleBtn;
	private JComboBox<DancePattern> combo;

	private ActionListener toggleBtnAction;
	private ChangeListener sliderChange;

	public AppFrame() throws AWTException {
		ShortcutManager.setup(this);
		
		this.setTitle("Mouser dance party");
		this.setSize(280, 130);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel(new GridBagLayout());
		panel.setBorder(new LineBorder(Color.blue));
		this.add(panel); 
		 
	    GridBagConstraints c = new GridBagConstraints();
		
		initToggleBtn();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.5;
	    c.gridwidth = 2;
	    c.gridx = 0;
	    c.gridy = 0;
		panel.add(toggleBtn, c);
		
		initSlider();
	    c.weightx = 0.5;
	    c.gridwidth = 1;
	    c.gridx = 0;
	    c.gridy = 1;
		panel.add(speedSlider, c);
		
		sliderLabel = new JLabel("speed", JLabel.CENTER);
	    c.gridx = 0;
	    c.gridy = 2;
		panel.add(sliderLabel, c);

		initSpinner();
		c.fill = GridBagConstraints.NONE;
	    c.gridx = 1;
	    c.gridy = 1;
		panel.add(stepSizeSpinner, c);
		
		spinnerLabel = new JLabel("range", JLabel.CENTER);
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 2;
		panel.add(spinnerLabel, c);

		initComboBox();
	    c.gridwidth = 2;
	    c.gridx = 0;
	    c.gridy = 3;
		panel.add(combo, c);
		
		mover = new MouseMover(10,  1000, getComboboxItem());
	}

	private void initSlider() {
		speedSlider = new JSlider(JSlider.HORIZONTAL, 150, 150000, 1000);
		speedSlider.setMajorTickSpacing(30000);
		speedSlider.setMinorTickSpacing(6000);
		speedSlider.setPaintTicks(true);
		initSliderListener();
		speedSlider.addChangeListener(sliderChange);
	}

	private void initSliderListener() {
		sliderChange = new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
		        if (!speedSlider.getValueIsAdjusting()) {
		            mover.setSpeed((int) speedSlider.getValue());
		        }
			}
		};
	}
		
	private void initSpinner() {
		SpinnerNumberModel stepSizeSpinnerModel = new SpinnerNumberModel(10, 1, 100, 1);
		stepSizeSpinner = new JSpinner(stepSizeSpinnerModel);
		stepSizeSpinner.setMaximumSize(new Dimension(20, stepSizeSpinner.getHeight()));
	}

	private void initToggleBtn() {
		toggleBtn = new JButton("let's dance");
		toggleBtn.setMinimumSize(new Dimension(toggleBtn.getWidth(), 40));
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
					mover.setStepSize((int) stepSizeSpinner.getValue());
					mover.setDancePattern(getComboboxItem());
					toggleBtn.setText("stop dancing");
				}
				
				mover.setDance(!mover.isDance());				
			}
		};
	}
	
	private DancePattern getComboboxItem() {
		return combo.getItemAt(combo.getSelectedIndex());
	}
	
	private void initComboBox() throws AWTException {
		combo = new JComboBox<DancePattern>(new DancePatternComboboxModel());
		
		List<DancePattern> styleList = DanceFactory.getAvailableStyles();
		Collections.sort(styleList, (s1, s2) -> s1.toString().compareTo(s2.toString()));
		
		styleList.forEach(item -> combo.addItem(item));
		
		combo.setSelectedIndex(0);
	}
	
	public JButton getToggleBtn() {
		return this.toggleBtn;
	}
}