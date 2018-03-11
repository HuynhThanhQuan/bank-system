package gui.frames;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.Client;

public class Welcome implements ActionListener {

	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JLabel labelregister = new JLabel();
	private JLabel labellogin = new JLabel();
	private JLabel labelstop = new JLabel();
	private JButton register = new JButton("Register");
	private JButton login = new JButton("Login");
	private JButton stop = new JButton("STOP");

	public Welcome(int clientNumber) {
		setupGUI(clientNumber);
	}

	private void setupGUI(int clientNumber) {
		frame.setTitle("Welcome client " + clientNumber);
		panel.setLayout(new GridLayout(2, 2));
		panel.add(register);
		panel.add(login);
		panel.add(stop);
		panel.add(labelregister);
		panel.add(labellogin);
		panel.add(labelstop);
		frame.setContentPane(panel);
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		register.addActionListener(this);
		login.addActionListener(this);
		stop.addActionListener(this);
	}

	//@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == register) {
			new Clear(frame, panel);
			new Register(frame, panel);
		}
		if (e.getSource() == login) {
			new Clear(frame, panel);
			new Login(frame, panel);
		}
		if (e.getSource() == stop) {
			// @FIXME
			FailedConnection.disconnect();
			try {
				new FailedConnection();
				FailedConnection.showWarning();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new Client().stopServer();
			frame.dispose();
		}
	}
}
