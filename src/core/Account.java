package core;

public class Account {

	private int accountNumber;
	private double balance;

	/*
	 * Constructor
	 */
	public Account(int anAccountNumber, double initialBalance) {
		accountNumber = anAccountNumber;
		balance = initialBalance;
	}

	public boolean CheckAccount(int anAccountNumber) {
		if (anAccountNumber != accountNumber) {
			return false;
		} else
			return true;
	}

	public boolean CheckAmount(double amount) {
		if (amount > balance) {
			return false;
		} else
			return true;
	}

	public void setBalance(double newbalance) {
		balance = newbalance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void showData() {
		System.out.println(accountNumber);
		System.out.println(balance);
	}
}