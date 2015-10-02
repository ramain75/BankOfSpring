package org.bankofspring.service;

import java.util.List;

import org.bankofspring.model.Account;
import org.bankofspring.model.Customer;

public interface BankOfSpringTransferService {

	public List <Account> getListOfAccountsForCustomer(int customerId); 
	public List<Customer> getListOfCustomerExcludingOne(int customerId);

}
