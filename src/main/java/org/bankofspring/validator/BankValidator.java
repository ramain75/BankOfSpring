package org.bankofspring.validator;

import org.bankofspring.entities.Account;
import org.springframework.stereotype.Component;

@Component
public class BankValidator {

	/**
	 * This should probably return the errors at some point.
	 * @param fromAccount
	 * @param toAccount
	 * @param amount
	 * @return
	 */
	public boolean validateTransfer(Account fromAccount, Account toAccount, long amount) {
		// ensure the from account has money
		if (fromAccount.getAccountAmount() < amount) {
			return false;
		}
		
		// check you're not moving money to the same account
		if (fromAccount.getAccountNumber() == toAccount.getAccountNumber()) {
			return false;
		}
		
		// check that the account the money is being sent from belongs to the logged in user
		// We'll do that when we have a bit of security
		
		return true;
	}
}
