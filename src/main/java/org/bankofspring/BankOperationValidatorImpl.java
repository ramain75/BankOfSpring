package org.bankofspring;

import org.bankofspring.model.Account;
import org.bankofspring.model.BankOperationType;
import org.bankofspring.model.User;

public class BankOperationValidatorImpl implements BankOperationValidator {
	public boolean validateOperation(User user, Account fromAccount, Account toAccount, long amount, BankOperationType type) {
		 validateUser(user);
		 validateAmount(amount);
		 if (type == BankOperationType.DEBIT) {
			 return validateDebit(fromAccount, amount);
		 }
		 if (type == BankOperationType.CREDIT) {
			 return validateCredit(toAccount, amount);
		 }
		 return false;
		 
	}
	public void validateUser(User user) {
		if (user == null) {
			throw new RuntimeException("invalid user");
		}
	}
	
	public void validateAmount(long amount) {
		if (amount < 0) {
			throw new RuntimeException("amount should be positive");
		}
	}
	public boolean validateDebit(Account fromAccount, long amount) {
		if (fromAccount.getAccountAmount() - amount < 0 ) {
			return false;
		}
		return true;
	}
	public boolean validateCredit(Account toAccount, long amount) {
		if (toAccount.getAccountAmount() + amount > Account.MAX_BALANCE ) {
			return false;
		}
		return true;
	}
}
