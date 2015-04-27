package org.bankofspring.service;

import org.bankofspring.entity.Account;
import org.bankofspring.entity.AccountTransaction;
import org.bankofspring.entity.User;
import org.bankofspring.exception.OperationDisallowedException;

/**
 * The BankService is called to make bank transfers etc.
 * 
 * @author axp
 */
public class BankService {
	public AccountTransaction transfer( User user, Account fromAccount, Account toAccount, int amount )
			throws OperationDisallowedException {
		
		// Neither account may be null
		if ( fromAccount == null ) {
			throw new OperationDisallowedException( "Account to debit is not valid" );
		}
		if ( toAccount == null ) {
			throw new OperationDisallowedException( "Account to credit is not valid" );
		}
		
		// Users may only transfer from their own accounts
		if ( ( user == null ) || !user.equals( fromAccount.getCustomer() ) ) {
			throw new OperationDisallowedException( "User does not have permissions on this account" );
		}
		
		// Accounts may not (for now) have negative balance
		if ( amount > fromAccount.getBalance() ) {
			throw new OperationDisallowedException( "Not enough money in account" );
		}
		
		// Nor may transfers be negative
		if ( amount < 0 ) {
			throw new OperationDisallowedException( "Not a positive amount" );
		}
		
		fromAccount.debit( amount );
		toAccount.credit( amount );
		
		return new AccountTransaction( fromAccount, toAccount, amount );
	}
}
