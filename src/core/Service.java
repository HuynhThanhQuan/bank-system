package core;

import gui.frames.Welcome;

import java.io.Serializable;
import core.Database;

public class Service implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static int user;
	private static double balance;
	private static double amount;

	/*
	 * Constructor
	 */
	public Service(int clientNumber) {
		new Welcome(clientNumber);
	}

	public static void setUser(int setUser) {
		user = setUser;
	}

	public static void setBalance(double setBalance) {
		balance = setBalance;
	}

	public static void setAmount(double setAmount) {
		amount = setAmount;
	}

	public static int getUser() {
		return user;
	}

	public static double getBalance() {
		return balance;
	}

	public static double getAmount() {
		return amount;
	}

	public static int showAccNumber() {
		return Database.getAccountNumber(user);
	}

	public static double showBalance() {
		return Database.getBalance(user);
	}

	public static void deposit() {
		double currentmoney = Database.getBalance(user);
		Database.setBalance(user, currentmoney + amount);
	}

	public static boolean withdraw() {
		boolean Confirmation = true;
		double currentmoney = Database.getBalance(user);
		if (Database.isValid(user, amount, "amount")) {
			Database.setBalance(user, currentmoney - amount);
		} else {
			Confirmation = false;
		}
		return Confirmation;
	}

	public static boolean transfer(int client) {
		boolean Confirmation = true;
		double currentmoneyuser1 = Database.getBalance(user);
		if (Database.isValid(user, amount, "amount")) {
			Database.setBalance(user, currentmoneyuser1 - amount);
			double currentmoneyuser2 = Database.getBalance(client);
			Database.setBalance(client, currentmoneyuser2 + amount);
		} else {
			Confirmation = false;
		}
		return Confirmation;
	}
}
