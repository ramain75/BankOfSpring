package org.bankofspring.validator;

import org.bankofspring.model.Account;
import org.bankofspring.model.BankOperationType;
import org.bankofspring.model.User;
import org.springframework.stereotype.Component;
/*
 * validates a bank operation 
 * checks if user is not null (throws RuntimeException if null)
 * checks if amount is positive (throws RuntimeException if null)
 * for debit, check that originating amount has at least the debit amount
 * for credit, checks that not going over maximum amount
 */
@Component
public class BankOperationValidatorImpl implements BankOperationValidator {

	
	public boolean validateOperation(User user, Account fromAccount,
			Account toAccount, long amount, BankOperationType type) {
		validateOperation(type);
		validateUser(user);
		validateAmount(amount);
		if (type == BankOperationType.CREDIT) {
			return validateCredit(toAccount, amount);
		}
		if (type == BankOperationType.DEBIT) {
			return validateDebit(fromAccount, amount);
		}
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
	
	protected void validateOperation (BankOperationType type) {
		if (type == null) {
			throw new RuntimeException("operation is unknown");
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
