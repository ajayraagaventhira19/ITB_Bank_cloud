package com.ITB.bank;

public class Account {
	String name;
	String dob;
	String gender;
	String martial;
	String account_type;
	String state;
	String city;
	int pincode;
	double amount;
	String username;
	String password;
	public Account(String name, String dob, String gender, String martial, String account_type, String state,
			String city, int pincode, double amount, String username, String password) {
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.martial = martial;
		this.account_type = account_type;
		this.state = state;
		this.city = city;
		this.pincode = pincode;
		this.amount = amount;
		this.username = username;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMartial() {
		return martial;
	}
	public void setMartial(String martial) {
		this.martial = martial;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
