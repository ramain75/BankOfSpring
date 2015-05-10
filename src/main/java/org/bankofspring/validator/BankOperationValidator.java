package org.bankofspring.validator;

import org.bankofspring.model.Account;
import org.bankofspring.model.BankOperationType;
import org.bankofspring.model.User;
import org.springframework.stereotype.Component;
/*
 * Validates any Bank Operation
 */
public interface BankOperationValidator {
	public boolean validateOperation(User user, Account fromAccount, Account toAccount, long amount, BankOperationType type);
	
	
}
