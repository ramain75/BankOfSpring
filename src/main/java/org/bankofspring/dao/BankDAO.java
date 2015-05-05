package org.bankofspring.dao;

import org.bankofspring.entities.Account;
import org.bankofspring.entities.AccountTransaction;

public interface BankDAO {

	/**
	 * Debit the account by the specified amount.
	 * @param account the account
	 * @param amount the amount
	 */
	void debitAccount(Account account, long amount);
	
	/**
	 * Credit the account by the specified amount
	 * @param account the account
	 * @param amount the amount
	 */
	void creditAccount(Account account, long amount);
	
	/**
	 * Logs an {@link AccountTransaction}
	 * @param fromAccount the from account
	 * @param toAccount the to account
	 * @param amount the amount
	 */
	void logTransaction(Account fromAccount, Account toAccount, long amount);
	
	/**
	 * Retrieve an account from the id
	 * @param id the ID
	 * @return the account matching
	 */
	Account getAccount(long id);
}
