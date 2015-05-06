package org.bankofspring.service;

import org.bankofspring.model.Account;
import org.bankofspring.model.User;
import org.bankofspring.validator.BankOperationValidator;

public interface BankOfSpringService {
	
	public boolean debit(User loggedInUser, Account fromAccount, Account toAccount, long amount);
	
	public boolean debit(User loggedInUser, Account fromAccount, long amount);
	
	public boolean credit(User loggedInUser, Account toAccount, Account fromAccount, long amount);
	
	public boolean credit(User loggedInUser, Account toAccount, long amount);
	
	public BankOperationValidator getValidator();
	

}
