package gui.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InvalidAccount implements ActionListener {
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JButton continous = new JButton("Continous");
	private GridBagConstraints pattern = new GridBagConstraints();

	public InvalidAccount(JFrame frame, JPanel panel, String text) {
		setupGUI(frame, panel, text);
	}

	private void setupGUI(JFrame frame, JPanel panel, String text) {
		this.frame = frame;
		this.panel = panel;

		frame.setTitle("Invalid Account");
		panel.setLayout(new GridBagLayout());
		pattern.fill = GridBagConstraints.BOTH;

		pattern.gridheight = 1;
		pattern.gridwidth = 1;
		pattern.gridx = 0;
		pattern.gridy = 2;
		panel.add(new JLabel(text), pattern);

		pattern.gridheight = 1;
		pattern.gridwidth = 4;
		pattern.gridx = 0;
		pattern.gridy = 3;
		panel.add(continous, pattern);

		frame.setContentPane(panel);
		continous.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == continous) {
			new Clear(frame, panel);
			frame.dispose();
			new Welcome(0);
		}
	}
}
