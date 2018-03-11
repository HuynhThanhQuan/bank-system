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

import core.Service;

public class Amount implements ActionListener {

	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JTextField amount = new JTextField(10);
	private JTextField client2 = new JTextField(10);
	private JButton next = new JButton("Continous");
	private String request;
	private GridBagConstraints pattern = new GridBagConstraints();

	public Amount(JFrame frame, JPanel panel, String request) {
		setupGUI(frame, panel, request);
	}

	private void setupGUI(JFrame frame, JPanel panel, String request) {

		this.frame = frame;
		this.panel = panel;
		this.request = request;
		if (request.equals("transfer") == false) {
			implement();
			frame.setContentPane(panel);
			next.addActionListener(this);
		} else {
			implement();
			pattern.gridheight = 1;
			pattern.gridwidth = 1;
			pattern.gridx = 0;
			pattern.gridy = 1;
			panel.add(new JLabel("to account "), pattern);
			pattern.gridheight = 1;
			pattern.gridwidth = 2;
			pattern.gridx = 1;
			pattern.gridy = 1;
			panel.add(client2, pattern);
			frame.setContentPane(panel);

			next.addActionListener(this);
		}
	}

	public void implement() {
		panel.removeAll();
		panel.setLayout(new GridBagLayout());
		pattern.fill = GridBagConstraints.BOTH;

		pattern.gridheight = 1;
		pattern.gridwidth = 1;
		pattern.gridx = 0;
		pattern.gridy = 0;
		panel.add(new JLabel("Amount: "), pattern);
		pattern.gridheight = 1;
		pattern.gridwidth = 2;
		pattern.gridx = 1;
		pattern.gridy = 0;
		panel.add(amount, pattern);

		pattern.gridheight = 1;
		pattern.gridwidth = 4;
		pattern.gridx = 0;
		pattern.gridy = 2;
		panel.add(next, pattern);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == next) {
			if (request.equals("deposit")) {
				Service.setAmount(Double.parseDouble(amount.getText()));
				Service.deposit();
				new Clear(frame, panel);
				new Finish(frame, panel, "deposit");
			}
			if (request.equals("withdraw")) {
				Service.setAmount(Double.parseDouble(amount.getText()));
				new Clear(frame, panel);
				if (Service.withdraw()) {
					new Finish(frame, panel, "withdraw");
				} else {
					new Invalid(frame, panel);
				}
			}
			if (request.equals("transfer")) {
				Service.setAmount(Double.parseDouble(amount.getText()));
				int user2 = Integer.parseInt(client2.getText());
				new Clear(frame, panel);
				if (Service.transfer(user2)) {
					new Finish(frame, panel, "transfer");
				} else {
					new Invalid(frame, panel);
				}
			}
		}
	}

}
