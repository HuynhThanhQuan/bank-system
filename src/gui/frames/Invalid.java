package gui.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Invalid implements ActionListener {
	
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JButton continous = new JButton("Continous");
	private GridBagConstraints pattern = new GridBagConstraints();

	public Invalid(JFrame frame, JPanel panel) {
		setupGUI(frame, panel);
	}

	private void setupGUI(JFrame frame, JPanel panel) {
		this.frame = frame;
		this.panel = panel;

		frame.setTitle("Invalid");
		panel.setLayout(new GridBagLayout());
		pattern.fill = GridBagConstraints.BOTH;

		pattern.gridheight = 1;
		pattern.gridwidth = 1;
		pattern.gridx = 0;
		pattern.gridy = 2;
		panel.add(new JLabel("Invalid transaction"), pattern);

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
			new Transaction(frame, panel);
		}
	}
}
