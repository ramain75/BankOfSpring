package org.bankofspring.validator;

import org.bankofspring.model.Account;
import org.bankofspring.model.BankOperationType;
import org.bankofspring.model.User;

public interface BankOperationValidator {
	public boolean validateOperation(User user, Account fromAccount, Account toAccount, long amount, BankOperationType type);
	
	
}
