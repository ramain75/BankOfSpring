package org.bankofspring.entity;

public class Account {
	private String accountNumber;
	private String description;
	private int balance;
	private Customer customer;
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	public void setAccountNumber( String accountNumber ) {
		this.accountNumber = accountNumber;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription( String description ) {
		this.description = description;
	}
	
	/**
	 * @return balance in pence
	 */
	public int getBalance() {
		return this.balance;
	}
	
	/**
	 * @param balance new balance in pence
	 */
	public void setBalance( int balance ) {
		this.balance = balance;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public void setCustomer( Customer customer ) {
		this.customer = customer;
	}
	
	/**
	 * Debit the account
	 * @param amount amount to debit in pence
	 */
	public void debit( int amount ) {
		this.balance -= amount;
	}
	
	/**
	 * Credit the account
	 * @param amount amount to credit in pence
	 */
	public void credit( int amount ) {
		this.balance += amount;
	}
}
