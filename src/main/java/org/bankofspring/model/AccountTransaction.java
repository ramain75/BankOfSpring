package org.bankofspring.model;

import java.util.Date;

/**
 * Class to represent an account transaction. To be eventually stored in the DB
 *
 */
public class AccountTransaction {
	
	private Long id;
	private Account fromAccount;
	private Account toAccount;
	private long transactionAmount;
	//small addition to specs, just to use spEL but it makes sense to store the date associated with an txn
	private Date transactionDate;
	
	public AccountTransaction(Account toAccount, Account fromAccount, long transactionAmount){
		setToAccount(toAccount);
		setFromAccount(fromAccount);
		setTransactionAmount(transactionAmount);
		setTransactionDate(new Date());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

}
