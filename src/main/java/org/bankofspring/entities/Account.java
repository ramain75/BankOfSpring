/**
 * 
 */
package org.bankofspring.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sean
 *
 */
public class Account {

	private Customer customer;
	private long accountNumber;
	private String accountDescription;
	private long accountAmount;
	private List<AccountTransaction> transactions = new ArrayList<AccountTransaction>();

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the accountNumber
	 */
	public long getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber
	 *            the accountNumber to set
	 */
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the accountDescription
	 */
	public String getAccountDescription() {
		return accountDescription;
	}

	/**
	 * @param accountDescription
	 *            the accountDescription to set
	 */
	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}

	/**
	 * @return the accountAmount
	 */
	public long getAccountAmount() {
		return accountAmount;
	}

	/**
	 * @param accountAmount
	 *            the accountAmount to set
	 */
	public void setAccountAmount(long accountAmount) {
		this.accountAmount = accountAmount;
	}

	/**
	 * @return the transactions
	 */
	public List<AccountTransaction> getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions
	 *            the transactions to set
	 */
	public void setTransactions(List<AccountTransaction> transactions) {
		this.transactions = transactions;
	}

}
