package org.bankofspring.model;

/**
 * Class to represent an account transaction. To be eventually stored in the DB
 * @author malcolmmurray
 *
 */
public class AccountTransaction {
	
	private Account fromAccount;
	private Account toAccount;
	private Double transactionAmount;
	
	public AccountTransaction(Account toAccount, Account fromAccount, Double transactionAmount){
		setToAccount(toAccount);
		setFromAccount(fromAccount);
		setTransactionAmount(transactionAmount);
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

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	
	

}
