package com.bankofspring.model;

import java.util.Calendar;

/**
 * Class to represent an account transaction. To be eventually stored in the DB
 *
 */
public class AccountTransaction {
	
	

	private Account fromAccount;
	private Account toAccount;
	private Long amount;
	private Calendar transactionDate;
	
	public AccountTransaction(Account toAccount, Account fromAccount, Long amount){
		setToAccount(toAccount);
		setFromAccount(fromAccount);
		setTransactionAmount(amount);
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
		return amount;
	}

	public void setTransactionAmount(long amount) {
		this.amount = amount;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((fromAccount == null) ? 0 : fromAccount.hashCode());
		result = prime * result
				+ ((toAccount == null) ? 0 : toAccount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountTransaction other = (AccountTransaction) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (fromAccount == null) {
			if (other.fromAccount != null)
				return false;
		} else if (!fromAccount.equals(other.fromAccount))
			return false;
		if (toAccount == null) {
			if (other.toAccount != null)
				return false;
		} else if (!toAccount.equals(other.toAccount))
			return false;
		return true;
	}
	
	
	

}
