package gui.frames;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FailedConnection {
	private static Lock lock = new ReentrantLock();
	private static Condition disconnection = lock.newCondition();
	private static boolean isConnected = true;

	public FailedConnection() {
	}

	public static void showWarning() throws InterruptedException {
		lock.lock();
		try {
			while (isConnected == true) {
				disconnection.await();
			}
			disconnection.signalAll();
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Cannot connect to Server \nYour update will be saved", "Client ",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			lock.unlock();
		}
	}

	public static void disconnect() {
		isConnected = false;
	}
}
