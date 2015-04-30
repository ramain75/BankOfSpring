package com.bankofspring.service;

import com.bankofspring.model.Account;
import com.bankofspring.model.AccountTransactions;
import com.bankofspring.model.User;

public interface BankOfSpringService {
	
	public AccountTransactions getTransactionLog();

	public void setTransactionLog(AccountTransactions transactionLog);
	
	boolean debit(User loggedInUser, Account fromAccount, Account toAccount, Double amount);
	
	public boolean debit(User loggedInUser, Account fromAccount, Double amount);
	
	boolean credit(User loggedInUser, Account toAccount, Account fromAccount, Double amount);
	
	boolean credit(User loggedInUser, Account toAccount, Double amount);
	
	
	

}
