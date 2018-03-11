package core;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

	private static FileReader fileForDatabase;
	private static String pathData;
	public static ArrayList<Account> data = new ArrayList<Account>();

	public Database(int accountnumber, double balance) {
		data.add(new Account(accountnumber, balance));
	}
	
	public Database(FileReader reader, String path) {
		pathData = path;
		fileForDatabase = reader;
		Scanner input = new Scanner(reader);
		while (input.hasNext()) {
			int accountnumber = Integer.parseInt(input.next());
			double balance = Double.parseDouble(input.next());
			data.add(new Account(accountnumber, balance));
		}
		input.close();
	}

	public static int getAccountNumber(int accountnumber) {
		int a = 0;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).CheckAccount(accountnumber)) {
				a = data.get(i).getAccountNumber();
				break;
			}
		}
		return a;
	}

	public static double getBalance(int accountnumber) {
		double b = 0;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).CheckAccount(accountnumber)) {
				b = data.get(i).getBalance();
			}
		}
		return b;
	}

	public static void setBalance(int accountnumber, double newbalance) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).CheckAccount(accountnumber)) {
				data.get(i).setBalance(newbalance);
			}
		}
	}

	public static boolean isValid(int accountnumber, double amount, String mode) {
		boolean Confirmation = true;

		if (mode.equals("amount")) {
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i).CheckAccount(accountnumber)) {
					if (data.get(i).CheckAmount(amount)) {
						Confirmation = true;
					} else {
						Confirmation = false;
						break;
					}
				}
			}
		} else if (mode.equals("accountnumber")) {
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i).CheckAccount(accountnumber)) {
					Confirmation = false;
					break;
				}
			}
		}
		return Confirmation;
	}

	public static void UpdateDatabase() throws IOException {
		FileWriter output = new FileWriter(pathData);
		for (int i = 0; i < data.size(); i++) {
			output.write(Integer.toString(data.get(i).getAccountNumber()) + " "
					+ Double.toString(data.get(i).getBalance()) + "\n");
			output.flush();
		}
		output.close();
	}
	
	public void showDatabase() {
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i).getAccountNumber() + " " + data.get(i).getBalance());
		}
	}
}
