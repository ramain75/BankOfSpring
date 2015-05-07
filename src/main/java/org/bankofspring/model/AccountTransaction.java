package org.bankofspring.model;

import java.util.Calendar;

/**
 * Class to represent an account transaction. To be eventually stored in the DB
 *
 */
public class AccountTransaction {
	private Account fromAccount;
	private Account toAccount;
	private long transactionAmount;
	private Calendar transactionDate;
	
	public AccountTransaction(Account toAccount, Account fromAccount, long transactionAmount){
		setToAccount(toAccount);
		setFromAccount(fromAccount);
		setTransactionAmount(transactionAmount);
		setTransactionDate(Calendar.getInstance());
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}

	public long getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(long amount) {
		this.transactionAmount = amount;
	}
	
	
	public Calendar getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Calendar transactionDate) {
		this.transactionDate = transactionDate;
	}

}
