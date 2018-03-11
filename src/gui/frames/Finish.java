package gui.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.Database;
import core.Service;

public class Finish implements ActionListener {

	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JButton exit = new JButton("Exit");
	private JButton continous = new JButton("Continous");
	private GridBagConstraints pattern = new GridBagConstraints();

	public Finish(JFrame frame, JPanel panel, String option) {
		setupGUI(frame, panel, option);
	}

	private void setupGUI(JFrame frame, JPanel panel, String quitOption) {
		this.frame = frame;
		this.panel = panel;
		frame.setTitle("Finish");
		panel.setLayout(new GridBagLayout());
		pattern.fill = GridBagConstraints.BOTH;

		pattern.gridheight = 1;
		pattern.gridwidth = 1;
		pattern.gridx = 0;
		pattern.gridy = 0;
		panel.add(new JLabel("Your account number : "), pattern);
		pattern.gridheight = 1;
		pattern.gridwidth = 1;
		pattern.gridx = 1;
		pattern.gridy = 0;
		panel.add(new JLabel(Integer.toString(Service.showAccNumber())), pattern);
		pattern.gridheight = 1;
		pattern.gridwidth = 1;
		pattern.gridx = 0;
		pattern.gridy = 1;
		panel.add(new JLabel("Your current balance : "), pattern);
		pattern.gridheight = 1;
		pattern.gridwidth = 1;
		pattern.gridx = 1;
		pattern.gridy = 1;
		panel.add(new JLabel(Double.toString(Service.showBalance())), pattern);

		if (!(quitOption.equals("quit"))) {
			pattern.gridheight = 1;
			pattern.gridwidth = 4;
			pattern.gridx = 0;
			pattern.gridy = 2;
			panel.add(continous, pattern);
			pattern.gridheight = 1;
			pattern.gridwidth = 4;
			pattern.gridx = 0;
			pattern.gridy = 3;
			panel.add(exit, pattern);

			frame.setContentPane(panel);
			continous.addActionListener(this);
			exit.addActionListener(this);
		} else {
			pattern.gridheight = 1;
			pattern.gridwidth = 4;
			pattern.gridx = 0;
			pattern.gridy = 3;
			panel.add(exit, pattern);
			frame.setContentPane(panel);
			exit.addActionListener(this);
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			frame.dispose();
			try {
				Database.UpdateDatabase();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == continous) {
			new Clear(frame, panel);
			new Transaction(frame, panel);
		}
	}
}
