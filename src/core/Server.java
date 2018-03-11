package core;

import java.io.*;
import java.net.*;
import javax.swing.JFileChooser;

public class Server {

	final static int SERVER_PORT = 8888;

	private static ServerSocket server;
	static FileReader database;

	public static void main(String[] args) throws IOException, FileNotFoundException, InterruptedException {
		initializeDatabase();
		startServer();
	}

	private static void initializeDatabase() throws FileNotFoundException {
		File selectedFile = new File("/home/invincible/workspace/BankProject/Database/database.txt");
		JFileChooser chosenFile = new JFileChooser();

		while (selectedFile.canRead() == false) {
			System.out.println("Input the file database");
			if (chosenFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				selectedFile = chosenFile.getSelectedFile();
			}
		}
		database = new FileReader(selectedFile);
		new Database(database, selectedFile.getAbsolutePath());
	}

	private static void startServer() throws IOException {
		try {
			server = new ServerSocket(SERVER_PORT);
			String serverName = server.getInetAddress().getHostAddress();
			System.out.println("IP Address Server: " + serverName);
			System.out.println("Waiting for clients to connect...");

			int clientNumber = 1;
			while (true) {
				Socket socketOfServer = server.accept();
				BufferedReader action = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
				String requestAction = action.readLine();
				if (!(requestAction.equals("stop"))) {
					new ServerThread(socketOfServer, clientNumber++).start();
				} else {
					break;
				}
			}
		} catch (IOException e) {
			System.out.println("Cannot connect to client");
		} finally {
			System.out.println("SERVER STOPS");
			server.close();
		}
	}

	private static class ServerThread extends Thread {
		private Socket socketOfServer;
		private int clientNumber;

		public ServerThread(Socket socketOfServer, int clientNumber) {
			this.clientNumber = clientNumber;
			this.socketOfServer = socketOfServer;
			System.out.println("New connection with client " + this.clientNumber + " at " + this.socketOfServer);
		}

		// @Override
		public void run() {
			try {
				ObjectOutputStream outputObjectServer = new ObjectOutputStream(socketOfServer.getOutputStream());
				outputObjectServer.writeObject(new Service(this.clientNumber));
				outputObjectServer.flush();
			} catch (IOException e) {
				System.err.println("IO Server connection error");
				e.printStackTrace();
			}
		}
	}
}