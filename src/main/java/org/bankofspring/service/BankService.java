package org.bankofspring.service;

import org.bankofspring.model.Account;
import org.bankofspring.model.User;

/**
 * Interface to represent the account transactions that can be carried out by a user.
 *  
 * @author ram
 *
 */
public interface BankService {

	/**
	 * The maximum balance that we will allow in an account. Currently 1 million
	 * Pounds.
	 */
	public static final long MAX_BALANCE = 100000000L;

	/**
	 * Perform a debit transaction (withdrawl or transfer). 
	 * @param loggedInUser performing the transaction on behalf of the account owner
	 * @param fromAccount the Account from which to withdraw funds
	 * @param toAccount for auditing only, the Account to which the funds are being transferred, or null if this is a withdrawl 
	 * @param amount of the withdrawl. Must be at least 1p
	 * @return true if successful, false if not
	 */
    boolean debit(User loggedInUser, Account fromAccount, Account toAccount, long amount);
    
	/**
	 * Perform a debit transaction (withdrawl). 
	 * @param loggedInUser performing the transaction on behalf of the account owner
	 * @param fromAccount the Account from which to withdraw funds
	 * @param amount of the withdrawl. Must be at least 1p
	 * @return true if successful, false if not
	 */
    boolean debit(User loggedInUser, Account fromAccount, long amount);
    
	/**
	 * Perform a credit transaction (counter credit or transfer). 
	 * @param loggedInUser performing the transaction on behalf of the account owner
	 * @param toAccount the Account to credit 
	 * @param fromAccount for auditing only, the Account from which the funds originated
	 * @param amount of the credit. Must be at least 1p
	 * @return true if successful, false if not
	 */
    boolean credit(User loggedInUser, Account toAccount, Account fromAccount, long amount);
    
	/**
	 * Perform a credit transaction (counter credit). 
	 * @param loggedInUser performing the transaction on behalf of the account owner
	 * @param toAccount the Account to credit 
	 * @param amount of the credit. Must be at least 1p
	 * @return true if successful, false if not
	 */
    boolean credit(User loggedInUser, Account toAccount, long amount);

}
