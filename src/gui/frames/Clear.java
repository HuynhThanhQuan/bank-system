package gui.frames;

import javax.swing.*;

public class Clear {
	public Clear(final JFrame frame, final JPanel panel) {
		panel.removeAll();
		frame.setContentPane(panel);
	}
}
