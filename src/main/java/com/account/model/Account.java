package com.account.model;

public class Account {
	private int id;
	private String firstName;
	private String secondName;
	private String accountNumber;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", accountNumber="
				+ accountNumber + "]";
	}
	
}
