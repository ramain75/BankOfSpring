package org.bankofspring.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class to represent a bank customer - extends user
 * 
 *
 */
public class Customer extends User {
	
	private String customerID;
	private String name;
	private Map<String, Account> accounts;
	
	public Customer(String username, String password, String name, String customerID){
		super(username, password);
		
		setName(name);
		setCustomerID(customerID);
	}
	
	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String,Account> getAccounts() {
		return accounts;
	}
	
	public List<Account> getAccountsList() {
		return new ArrayList<Account>(accounts.values());
	}
	
	public Account getCustomerAccount(String accountNumber) {
		return accounts.get(accountNumber);
	}
	
	public void deleteAccount(String accountNumber) {
		if (accounts.containsKey((accountNumber))) {
			accounts.remove(accountNumber);
		};
	}
	public void addeAccount(Account account) {
		if (!accounts.containsKey(account.getAccountNumber())) {
			accounts.remove(account);
		};
	}

	public void setAccounts(Map<String,Account> accounts) {
		// just to ensure account and customer are consistent
		for (Account account: accounts.values()) {
			List<Customer> customers = account.getOwningCustomers();
			if (customers == null || !customers.contains(this)) {
				ArrayList<Customer> newCustomers = new ArrayList<Customer>(customers);
				newCustomers.add(this);
				account.setOwningCustomers(newCustomers);
			}
		}
		this.accounts = accounts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((customerID == null) ? 0 : customerID.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customerID == null) {
			if (other.customerID != null)
				return false;
		} else if (!customerID.equals(other.customerID))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
}
