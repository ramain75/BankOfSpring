package org.bankofspring.service;

import java.util.List;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.dao.CustomerDAO;
import org.bankofspring.model.Account;
import org.bankofspring.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service( "bankTransferService" )
public class BankOfSpringTransferServiceImpl implements BankOfSpringTransferService {
	@Autowired
	@Qualifier("jdbcAccountDao")
	private AccountDAO accountDAO;
	
	@Autowired
	@Qualifier("jdbcCustomerDao")
	private CustomerDAO customerDAO;

	@Override
	public List<Account> getListOfAccountsForCustomer(int customerId) {
		List <Account> accounts = accountDAO.getAccountsForCustomer(customerId);
		return accounts;
	}

	@Override
	public List<Customer> getListOfCustomerExcludingOne(int customerId) {
		List <Customer> customers = customerDAO.getCustomers();
		customers.remove(customerDAO.getCustomerById(customerId));
		return customers;
	}

}
