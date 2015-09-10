package org.bankofspring.web;

public class AccountForm {
	private String number;
	private String description;
	private long balance;
	private long maxbalance;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public long getMaxbalance() {
		return maxbalance;
	}
	public void setMaxbalance(long maxbalance) {
		this.maxbalance = maxbalance;
	}
}
