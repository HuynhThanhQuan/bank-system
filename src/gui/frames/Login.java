package gui.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Database;
import core.Service;

public class Login implements ActionListener {

	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JButton loginbutton = new JButton("LOGIN");
	private JLabel userlabel = new JLabel("Your account number: ");
	private JTextField usertext = new JTextField(10);
	private GridBagConstraints pattern = new GridBagConstraints();

	public Login(JFrame frame, JPanel panel) {
		setupGUI(frame, panel);
	}

	private void setupGUI(JFrame frame, JPanel panel) {
		this.frame = frame;
		this.panel = panel;
		frame.setTitle("Login");
		panel.setLayout(new GridBagLayout());

		pattern.fill = GridBagConstraints.HORIZONTAL;
		pattern.gridheight = 1;
		pattern.gridwidth = 1;
		pattern.gridx = 0;
		pattern.gridy = 0;
		panel.add(userlabel, pattern);

		pattern.gridheight = 1;
		pattern.gridwidth = 3;
		pattern.gridx = 1;
		pattern.gridy = 0;
		panel.add(usertext, pattern);

		pattern.gridheight = 1;
		pattern.gridwidth = 4;
		pattern.gridx = 0;
		pattern.gridy = 1;
		panel.add(loginbutton, pattern);
		frame.setContentPane(panel);
		loginbutton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == loginbutton) {
			Service.setUser(Integer.parseInt(usertext.getText()));

			if (!(Database.isValid(Service.getUser(), 0, "accountnumber"))) {
				new Clear(frame, panel);
				new Transaction(frame, panel);
			} else {
				new Clear(frame, panel);
				new InvalidAccount(frame, panel, "This account does not exist");
			}
		}
	}
}
