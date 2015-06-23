package org.bankofspring.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Class to represent an account transaction. To be eventually stored in the DB
 *
 */
@Entity
@Table(name = "account_transaction")
public class AccountTransaction {
	@Id 
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="from_account_number")
	private Account fromAccount;
	
	@OneToOne
	@JoinColumn(name="to_account_number")
	private Account toAccount;
	
	@Column(name = "amount")
	private long transactionAmount;
	
	//small addition to specs, just to use spEL but it makes sense to store the date associated with an txn
	@Column(name = "time")
	private Date transactionDate;
	
	public AccountTransaction(Account toAccount, Account fromAccount, long transactionAmount){
		setToAccount(toAccount);
		setFromAccount(fromAccount);
		setTransactionAmount(transactionAmount);
		setTransactionDate(new Date());
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
