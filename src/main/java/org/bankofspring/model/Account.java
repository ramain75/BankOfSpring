package org.bankofspring.model;

import java.util.List;

/**
 * Class to represent a bank account
 * @author malcolmmurray
 *
 */
public class Account {
	
	private String accountNumber;
	private String accountDescription;
	private Double accountAmount; //This is a Double object for now as this will be needed later for Hibernate
	private Double maxBalanceAmount;
	
	//List of owning customers, for e.g., joint accounts
	private List<Customer> owningCustomers;
	
	public Account(String accountNumber, String accountDescription, List<Customer> owningCustomers){
		setAccountNumber(accountNumber);
		setAccountDescription(accountDescription);
		setOwningCustomers(owningCustomers);
		setAccountAmount(0.00);
		setMaxBalanceAmount(Double.POSITIVE_INFINITY); //Make very high unless specified 
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

	public Double getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(Double accountAmount) {
		this.accountAmount = accountAmount;
	}

	public List<Customer> getOwningCustomers() {
		return owningCustomers;
	}

	public void setOwningCustomers(List<Customer> owningCustomers) {
		this.owningCustomers = owningCustomers;
	}
	
	public Double getMaxBalanceAmount() {
		return maxBalanceAmount;
	}

	public void setMaxBalanceAmount(Double maxBalanceAmount) {
		this.maxBalanceAmount = maxBalanceAmount;
	}

	/**
	 * Method to debit an amount from this account object
	 * @param amount
	 * @return true if the debit was successful, false if not
	 */
	public boolean debitAccount(Double amount){
		
		if(getAccountAmount() >= amount){
			setAccountAmount(getAccountAmount() - amount);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean creditAccount(Double amount){
		
		if((getAccountAmount() + amount) <= getMaxBalanceAmount()){
			setAccountAmount(getAccountAmount()+amount);
			return true;
		}else{
			return false;
		}
	}
	
	

}
