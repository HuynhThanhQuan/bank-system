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

public class Register implements ActionListener {

	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel userlabel = new JLabel("Your account number: ");
	private JLabel balancelabel = new JLabel("Your initial balance: ");
	private JTextField usertext = new JTextField(10);
	private JTextField balancetext = new JTextField(8);
	private JButton createbutton = new JButton("CREATE AN ACCOUNT");
	private GridBagConstraints pattern = new GridBagConstraints();
	private JLabel dialog = new JLabel("Your account has been created");
	private JButton myaccount = new JButton("My account");
	private JButton otheraccount = new JButton("Other account");

	public Register(JFrame frame, JPanel panel) {
		setupGUI(frame, panel);
	}

	private void setupGUI(JFrame frame, JPanel panel) {
		this.frame = frame;
		this.panel = panel;
		frame.setTitle("Register");
		panel.setLayout(new GridBagLayout());
		pattern.fill = GridBagConstraints.BOTH;

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
		pattern.gridwidth = 1;
		pattern.gridx = 0;
		pattern.gridy = 1;
		panel.add(balancelabel, pattern);
		pattern.gridheight = 1;
		pattern.gridwidth = 3;
		pattern.gridx = 1;
		pattern.gridy = 1;
		panel.add(balancetext, pattern);

		pattern.gridheight = 1;
		pattern.gridwidth = 4;
		pattern.gridx = 0;
		pattern.gridy = 2;
		panel.add(createbutton, pattern);
		frame.setContentPane(panel);

		createbutton.addActionListener(this);
	}

	public void finishRegister() {
		new Database(Service.getUser(), Service.getBalance());
		panel.removeAll();
		panel.setLayout(new GridBagLayout());
		pattern.fill = GridBagConstraints.BOTH;

		pattern.gridheight = 1;
		pattern.gridwidth = 1;
		pattern.gridx = 0;
		pattern.gridy = 0;
		panel.add(dialog, pattern);

		pattern.gridheight = 1;
		pattern.gridwidth = 1;
		pattern.gridx = 0;
		pattern.gridy = 1;
		panel.add(myaccount, pattern);

		pattern.gridheight = 1;
		pattern.gridwidth = 1;
		pattern.gridx = 0;
		pattern.gridy = 2;
		panel.add(otheraccount, pattern);
		frame.setContentPane(panel);

		otheraccount.addActionListener(this);
		myaccount.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == createbutton) {
			Service.setUser(Integer.parseInt(usertext.getText()));
			Service.setBalance(Double.parseDouble(balancetext.getText()));

			if (Database.isValid(Service.getUser(), Service.getBalance(), "accountnumber")) {
				finishRegister();
			} else {
				new Clear(frame, panel);
				new InvalidAccount(frame, panel, "Cannot create this account");
			}
		}
		if (e.getSource() == otheraccount) {
			new Clear(frame, panel);
			new Login(frame, panel);
		}
		if (e.getSource() == myaccount) {
			new Clear(frame, panel);
			new Transaction(frame, panel);
		}
	}
}
