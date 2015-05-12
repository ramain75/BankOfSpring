package org.bankofspring.entity;

public class AccountTransaction {
	private Account fromAccount;
	private Account toAccount;
	private int amount;
	
	/**
	 * Create a transaction
	 * 
	 * @param fromAccount account to debit
	 * @param toAccount account to credit
	 * @param amount positive amount in pence to transfer
	 */
	public AccountTransaction( Account fromAccount, Account toAccount, int amount ) {
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}

	public Account getFromAccount() {
		return this.fromAccount;
	}
	
	public void setFromAccount( Account fromAccount ) {
		this.fromAccount = fromAccount;
	}
	
	public Account getToAccount() {
		return this.toAccount;
	}
	
	public void setToAccount( Account toAccount ) {
		this.toAccount = toAccount;
	}
	
	/**
	 * @return transaction amount in pence
	 */
	public int getAmount() {
		return this.amount;
	}
	
	/**
	 * @param amount transaction amount in pence
	 */
	public void setAmount( int amount ) {
		this.amount = amount;
	}
}
