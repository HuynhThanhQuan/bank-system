package gui.frames;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Transaction implements ActionListener {

	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JButton showBalance = new JButton("Show balance");
	private JButton deposit = new JButton("Deposit");
	private JButton withdraw = new JButton("Withdraw");
	private JButton transfer = new JButton("Transfer");
	private JButton quit = new JButton("Quit");

	public Transaction(JFrame frame, JPanel panel) {
		setupGUI(frame, panel);
	}

	private void setupGUI(JFrame frame, JPanel panel) {
		this.frame = frame;
		this.panel = panel;
		panel.removeAll();
		frame.setTitle("Transaction");
		panel.setLayout(new GridLayout(5, 1));
		panel.add(showBalance);
		panel.add(deposit);
		panel.add(withdraw);
		panel.add(transfer);
		panel.add(quit);
		frame.setContentPane(panel);

		showBalance.addActionListener(this);
		deposit.addActionListener(this);
		withdraw.addActionListener(this);
		transfer.addActionListener(this);
		quit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == showBalance) {
			new Clear(frame, panel);
			new Finish(frame, panel, "showBalance");
		}
		if (e.getSource() == deposit) {
			new Clear(frame, panel);
			new Amount(frame, panel, "deposit");
		}
		if (e.getSource() == withdraw) {
			new Clear(frame, panel);
			new Amount(frame, panel, "withdraw");
		}
		if (e.getSource() == transfer) {
			new Clear(frame, panel);
			new Amount(frame, panel, "transfer");
		}
		if (e.getSource() == quit) {
			new Clear(frame, panel);
			new Finish(frame, panel, "quit");
		}
	}
}
