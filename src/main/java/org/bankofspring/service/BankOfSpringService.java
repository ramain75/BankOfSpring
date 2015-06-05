package org.bankofspring.service;
/*
 * the service interface for the bank, at this stage we support credit and debit
 * also we have an BankOperationValidator
 */
import org.bankofspring.model.Account;
import org.bankofspring.model.User;

public interface BankOfSpringService {
	
	public boolean transfer(User loggedInUser, Account fromAccount, Account toAccount, long amount);
	
	public boolean withdraw(User loggedInUser, Account fromAccount, long amount);
	
	public boolean deposit(User loggedInUser, Account toAccount, long amount);

}
