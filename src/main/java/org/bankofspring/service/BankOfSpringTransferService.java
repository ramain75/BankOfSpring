package org.bankofspring.service;

import java.util.List;

import org.bankofspring.model.Account;

public interface BankOfSpringTransferService {

	public List <Account> getListOfAccountsForCustomer(int customerId); 

}
