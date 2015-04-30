package com.bankofspring.service;

import com.bankofspring.model.Account;
import com.bankofspring.model.AccountTransaction;
import com.bankofspring.model.AccountTransactions;
import com.bankofspring.model.User;

public class BankOfSpringServiceImpl implements BankOfSpringService {
	
	private AccountTransactions transactionLog;
	
	public AccountTransactions getTransactionLog() {
		return transactionLog;
	}

	public void setTransactionLog(AccountTransactions transactionLog) {
		this.transactionLog = transactionLog;
	}

	@Override
	public boolean debit(User loggedInUser, Account fromAccount,
			Account toAccount, Double amount) {
		
		boolean success = true;
		
		//If the logged in user is valid and from account is owned by the user
		if(isUserValid(loggedInUser) && 
				accountOwnedByUser(fromAccount, loggedInUser) && 
				isAmountValid(amount) ){
			
			//Debit account
			success = fromAccount.debitAccount(amount);
			
			if(success && toAccount != null){
				success = toAccount.creditAccount(amount);
			}
		}else{
			throw new RuntimeException("User or amount not valid");
		}
		
		//Record transaction
		this.transactionLog.addTransactionRecord(new AccountTransaction(toAccount, fromAccount, -amount));
		
		return success;
	}

	@Override
	public boolean debit(User loggedInUser, Account fromAccount, Double amount) {
		
		return debit(loggedInUser,fromAccount, null, amount);
	}

	@Override
	public boolean credit(User loggedInUser, Account toAccount,
			Account fromAccount, Double amount) {
		
		boolean success = true;
		
		//If the logged in user is valid and from account is owned by the user
		if(isUserValid(loggedInUser) && 
				accountOwnedByUser(toAccount, loggedInUser) && 
				isAmountValid(amount) ){
			
			//Debit account
			success = toAccount.creditAccount(amount);
			
			if(success && fromAccount != null){
				success = fromAccount.debitAccount(amount);
			}
		}else{
			throw new RuntimeException("User or amount not valid");
		}
		
		//Record transaction
		this.transactionLog.addTransactionRecord(new AccountTransaction(toAccount, fromAccount, amount));
		
		return success;
	}

	@Override
	public boolean credit(User loggedInUser, Account toAccount, Double amount) {
		return credit(loggedInUser, toAccount, null, amount);
	}
	
	/**
	 * Method to check if a user is valid, just null check for now
	 * @param user
	 * @return
	 */
	private boolean isUserValid(User user){
		return user != null;
	}
	
	/**
	 * Check if an account is owned by a specified user
	 * @param account
	 * @param user
	 * @return
	 */
	private boolean accountOwnedByUser(Account account, User user){
		return account.getOwningCustomers().contains(user);
	}
	
	private boolean isAmountValid(Double amount){
		return amount >= 0;
	}
	
	
	
	
}
