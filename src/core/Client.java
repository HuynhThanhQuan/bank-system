package core;

import java.io.BufferedWriter;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.*;

public class Client {
	private static Service objectFromServer;
	final static int SERVER_PORT = 8888;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		connectToServer();
	}

	public static void connectToServer() {
		try {
			Socket socketOfClient = new Socket("localhost", SERVER_PORT);

			BufferedWriter action = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
			action.write("");
			action.newLine();
			action.flush();
			ObjectInputStream inputObjectClient = new ObjectInputStream(socketOfClient.getInputStream());
			objectFromServer = (Service) inputObjectClient.readObject();

			socketOfClient.close();
		} catch (UnknownHostException e) {
			System.err.println("Dont' know about host");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Server is stopped");
			return;
		}
	}

	public void stopServer() {
		try {
			Socket socketOfClosing = new Socket("localhost", SERVER_PORT);
			BufferedWriter action = new BufferedWriter(new OutputStreamWriter(socketOfClosing.getOutputStream()));
			action.write("stop");
			action.newLine();
			action.flush();

			socketOfClosing.close();
		} catch (UnknownHostException e) {
			System.err.println("Dont know about host");
		} catch (Exception e) {
			System.out.println("Cannot get IO for the connection - Server is already stopped");
		} finally {
		}
	}

}