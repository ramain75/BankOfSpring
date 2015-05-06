package com.bankofspring.model;

import java.util.List;

/**
 * Class to represent a bank customer - extends user
 * 
 *
 */
public class Customer extends User {
	
	private String customerID;
	private String name;
	private List<Account> accounts;
	
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
}
