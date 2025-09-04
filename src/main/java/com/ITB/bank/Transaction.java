package com.ITB.bank;

public class Transaction {
	String Transaction_Message;
	String Transaction_Date_Time;
	double Amount;
	double Old_Balance;
	double Current_Balance;
	public Transaction(String transaction_Message, String transaction_Date_Time, double amount, double old_Balance,
			double current_Balance) {
		super();
		Transaction_Message = transaction_Message;
		Transaction_Date_Time = transaction_Date_Time;
		Amount = amount;
		Old_Balance = old_Balance;
		Current_Balance = current_Balance;
	}
	public String getTransaction_Message() {
		return Transaction_Message;
	}
	public void setTransaction_Message(String transaction_Message) {
		Transaction_Message = transaction_Message;
	}
	public String getTransaction_Date_Time() {
		return Transaction_Date_Time;
	}
	public void setTransaction_Date_Time(String transaction_Date_Time) {
		Transaction_Date_Time = transaction_Date_Time;
	}
	public double getAmount() {
		return Amount;
	}
	public void setAmount(double amount) {
		Amount = amount;
	}
	public double getOld_Balance() {
		return Old_Balance;
	}
	public void setOld_Balance(double old_Balance) {
		Old_Balance = old_Balance;
	}
	public double getCurrent_Balance() {
		return Current_Balance;
	}
	public void setCurrent_Balance(double current_Balance) {
		Current_Balance = current_Balance;
	}
	
	
	
}
