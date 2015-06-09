package org.bankofspring.dao;

import java.util.List;

import org.bankofspring.model.*;

public interface BankOfSpringDAO {
	public Customer getCustomer(String customerId);
	public List <Customer> getCustomers();
	public Account getAccount(String accountNumber);
	public List<Customer> getCustomersForAccount(String accountNumber);
	public Account updateAccountBalance(String accountNumber, long balance);
	public AccountTransaction getAccountTransaction(int id);
	public AccountTransaction createAccountTransaction(String from_account_Number, String to_accountNumer, long amount );
	
}
