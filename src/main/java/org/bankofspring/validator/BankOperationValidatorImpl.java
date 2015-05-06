package org.bankofspring.validator;

import org.bankofspring.model.Account;
import org.bankofspring.model.BankOperationType;
import org.bankofspring.model.User;

public class BankOperationValidatorImpl implements BankOperationValidator {

	
	public boolean validateOperation(User user, Account fromAccount,
			Account toAccount, long amount, BankOperationType type) {
		
		return false;
	}
	protected void validateUser(User user) {
		if (user == null) {
			throw new RuntimeException("invalid User");
		}
	}
	protected void validateAmount(long amount) {
		if (amount < 0) {
			throw new RuntimeException("amount must be positive");
		}
	}
	protected boolean validateDebit(Account fromAccount, long amount) {
		if (fromAccount.getAccountBalance() - amount < 0 ) {
			return false;
		}
		return true;
	}
	protected boolean validateCredit(Account toAccount, long amount) {
		if (toAccount.getAccountBalance()+ amount > toAccount.getMaxBalanceAmount() ) {
			return false;
		}
		return true;
	}
}
