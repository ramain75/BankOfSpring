package org.bankofspring.service;

import java.util.List;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service( "bankTransferService" )
public class BankOfSpringTransferServiceImpl implements BankOfSpringTransferService {
	@Autowired
	@Qualifier("jdbcAccountDao")
	private AccountDAO accountDAO;
	
	@Override
	public List<Account> getListOfAccountsForCustomer(int customerId) {
		List <Account> accounts = accountDAO.getAccountsForCustomer(customerId);
		System.out.println("listing accounts xxxxxxxxxxxxxx " + customerId + " " + accounts.size());
		return accounts;
	}

}
