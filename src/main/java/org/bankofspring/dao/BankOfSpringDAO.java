package org.bankofspring.dao;

import java.util.List;

import org.bankofspring.model.*;

public interface BankOfSpringDAO {
	public Customer getCustomer(String customerId);
	public List <Customer> getCustomers();
	public Account getAccount(String accountNumber);
	public List<Customer> getCustomersForAccount(String accountNumber);
	
}
