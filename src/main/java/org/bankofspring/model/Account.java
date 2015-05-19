package org.bankofspring.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a bank account
 * we have added a maxBalanceAmount to maintain the maximum allowed for an account
 * note that an Account has a bidirectional relationship with customer and that
 * more than one customer can be linked to an account (joined account)
 * we have added facilities to add customer against the account but no facilities as yet to 
 * remove an customer from account
 */
public class Account {
	
	private String accountNumber;
	private String accountDescription;
	private long accountBalance; 
	private long maxBalanceAmount;
	
	//List of owning customers, for e.g., joint accounts
	private List<Customer> owningCustomers = new ArrayList<Customer>();
	private List<AccountTransaction> transactions = new ArrayList<AccountTransaction>();
	
	public Account(String accountNumber, String accountDescription, List<Customer> owningCustomers){
		setAccountNumber(accountNumber);
		setAccountDescription(accountDescription);
		setOwningCustomers(owningCustomers);
		setAccountBalance(0L);
		setMaxBalanceAmount(Long.MAX_VALUE); //Make very high unless specified 
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountDescription() {
		return accountDescription;
	}

	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}

	public long getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}

	public List<Customer> getOwningCustomers() {
		return owningCustomers;
	}

	public void setOwningCustomers(List<Customer> owningCustomers) {
		if (owningCustomers != null) {
			this.owningCustomers = owningCustomers;
		}
	}
	
	public void addCustomer(Customer customer) {
		if (customer == null) {
			return;
		}
		if ( !owningCustomers.contains(customer)) {
			owningCustomers.add(customer);
		}
	}
	public long getMaxBalanceAmount() {
		return maxBalanceAmount;
	}

	public void setMaxBalanceAmount(long maxBalanceAmount) {
		this.maxBalanceAmount = maxBalanceAmount;
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
		accountBalance += transaction.getTransactionAmount();
		transactions.add(transaction);
		return true;
	}

	public List<AccountTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<AccountTransaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountNumber == null) ? 0 : accountNumber.hashCode());
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
		Account other = (Account) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		return true;
	}

	

}
