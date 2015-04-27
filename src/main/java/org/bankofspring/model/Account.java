/**
 * 
 */
package org.bankofspring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ram
 *
 */
public class Account implements Serializable {

	private static final long serialVersionUID = -2377049632935889067L;

	/**
	 * The account number.
	 */
	private long accountNumber;

	/**
	 * The type of account.
	 */
	private AccountType accountType;

	/**
	 * The balance of the account in the smallest unit of currency, i.e. pence.
	 * We could calculate this on the fly but it is probably cheaper to keep a
	 * running total, at least until we start persisting the data.
	 */
	private long accountBalance;

	/**
	 * The owner of the account.
	 */
	private Customer customer;

	/**
	 * A list of transactions that have been performed on the account over time.
	 */
	private List<AccountTransaction> transactions;

	/**
	 * Constructor.
	 * 
	 * @param accountNumber
	 * @param accountType
	 * @param accountBalance
	 * @param customer
	 */
	public Account(Long accountNumber, AccountType accountType,
			Customer customer) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.accountBalance = 0L;
		this.customer = customer;
		transactions = new ArrayList<AccountTransaction>();
	}

	/**
	 * @return the accountNumber
	 */
	public long getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @return the accountType
	 */
	public AccountType getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType
	 *            the accountType to set
	 */
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @return the accountBalance
	 */
	public long getAccountBalance() {
		return accountBalance;
	}

	/**
	 * Applies a transaction to the account. Ignores the transaction if it is
	 * null.
	 * 
	 * @param transaction
	 *            an AccountTransaction
	 * @return false if AccountTransaction could not be applied, true otherwise
	 */
	public boolean applyTransaction(AccountTransaction transaction) {
		if (null == transaction) {
			return false;
		}

		accountBalance += transaction.getAmount();
		transactions.add(transaction);
		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Uses only the accountNumber to determine hashCode as no Account should
	 * share an accountNumber with another Account.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		// Note that we can cast here as we don't care if it changes its value
		result = prime * result + (int) accountNumber;
		return result;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Uses only the accountNumber to determine equality as no Account should
	 * share an accountNumber with another Account.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		return true;
	}

}
