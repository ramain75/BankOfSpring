package org.bankofspring.model;


public class Account {
	public static long MAX_BALANCE = 99999999999L;
	private String accountNumber;
	private String accountDescription;
	private long accountAmount;
	private Customer customer;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountDescription() {
		return accountDescription;
	}
	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}
	public long getAccountAmount() {
		return accountAmount;
	}
	public void setAccountAmount(long accountAmount) {
		this.accountAmount = accountAmount;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public boolean addAccountTransaction(AccountTransaction txn, BankOperationType type) {
		if (type == BankOperationType.DEBIT) {
			accountAmount -= txn.getAmount();
			return true;
		}
		if (type == BankOperationType.CREDIT) {
			accountAmount += txn.getAmount();
			return true;
		}
		return false;
	}
	
	
}
