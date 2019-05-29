package gui;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShortcutManager {
	private static final Logger logger = LoggerFactory.getLogger(ShortcutManager.class);
	
	private static HashMap<KeyStroke, Action> actionMap = new HashMap<KeyStroke, Action>();

	public static void setup(AppFrame parent) {
		KeyStroke key1 = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
		actionMap.put(key1, new AbstractAction("stop") {
			private static final long serialVersionUID = -4909314606208482875L;

			@Override
			public void actionPerformed(ActionEvent e) {
				logger.debug("Ctrl-S pressed: " + e);
				
				parent.getToggleBtn().doClick();
			}
		});

		KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		kfm.addKeyEventDispatcher(new KeyEventDispatcher() {

			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				KeyStroke keyStroke = KeyStroke.getKeyStrokeForEvent(e);

				if (actionMap.containsKey(keyStroke)) {
					final Action a = actionMap.get(keyStroke);
					final ActionEvent ae = new ActionEvent(e.getSource(), e.getID(), null);

					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							a.actionPerformed(ae);
						}
					});
					
					return true;
				}
				
				return false;
			}
		});
	}
}
