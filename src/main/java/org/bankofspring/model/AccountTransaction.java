package org.bankofspring.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Class to model an account transaction. If it is a debit, the fromAccount will
 * be populated and the amount will be negative. If it is a credit the toAccount
 * will be populated and the amount will be positive. The other account can be
 * populated if the transaction represents a payment or transfer.
 * 
 * @author ram
 *
 */
public class AccountTransaction implements Serializable {

	private static final long serialVersionUID = 9011178094204166131L;

	private Calendar txnDate;

	private Account fromAccount;

	private Account toAccount;

	private Long amount;

	/**
	 * Constructor.
	 * 
	 * If this transaction represents a debit, the fromAccount will be populated
	 * and the amount will be negative. If it is a credit the toAccount will be
	 * populated and the amount will be positive. The other account can be
	 * populated if the transaction represents a payment or transfer.
	 * 
	 * @param fromAccount
	 * @param toAccount
	 * @param amount
	 */
	public AccountTransaction(Account fromAccount, Account toAccount,
			Long amount) {

		// Works out the date and time in GMT
		this.txnDate = GregorianCalendar.getInstance(TimeZone
				.getTimeZone("GMT"));

		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}

	/**
	 * @return the txnDate
	 */
	public Calendar getTxnDate() {
		return txnDate;
	}

	/**
	 * @return the fromAccount
	 */
	public Account getFromAccount() {
		return fromAccount;
	}

	/**
	 * @return the toAccount
	 */
	public Account getToAccount() {
		return toAccount;
	}

	/**
	 * @return the amount
	 */
	public Long getAmount() {
		return amount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((fromAccount == null) ? 0 : fromAccount.hashCode());
		result = prime * result
				+ ((toAccount == null) ? 0 : toAccount.hashCode());
		result = prime * result + ((txnDate == null) ? 0 : txnDate.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		if (txnDate == null) {
			if (other.txnDate != null)
				return false;
		} else if (!txnDate.equals(other.txnDate))
			return false;
		return true;
	}

}
