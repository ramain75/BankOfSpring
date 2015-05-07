package org.bankofspring.service;

import org.bankofspring.model.Account;
import org.bankofspring.model.AccountTransaction;
import org.bankofspring.model.BankOperationType;
import org.bankofspring.model.User;
import org.bankofspring.validator.BankOperationValidator;

public class BankOfSpringServiceImpl implements BankOfSpringService {
	protected BankOperationValidator validator;

	public BankOperationValidator getValidator() {
		return validator;
	}

	public void setValidator(BankOperationValidator validator) {
		this.validator = validator;
	}

	public boolean debit(User loggedInUser, Account fromAccount,
			Account toAccount, long amount) {
		if ( validator.validateOperation(loggedInUser, fromAccount, toAccount, amount, BankOperationType.DEBIT)) {
			AccountTransaction txn = new AccountTransaction(fromAccount,toAccount,-amount);
			return fromAccount.applyTransaction(txn);
		}
		return false;
	}

	
	public boolean debit(User loggedInUser, Account fromAccount, long amount) {
		return debit(loggedInUser,fromAccount,null,amount);

	}

	
	public boolean credit(User loggedInUser, Account toAccount,
			Account fromAccount, long amount) {
		
		if ( validator.validateOperation(loggedInUser, fromAccount, toAccount, amount, BankOperationType.CREDIT)) {
			AccountTransaction txn = new AccountTransaction(fromAccount,toAccount,amount);
			return toAccount.applyTransaction(txn);
		}
		return false;
	}

	
	public boolean credit(User loggedInUser, Account toAccount, long amount) {
		// TODO Auto-generated method stub
		return debit(loggedInUser,toAccount,null,amount);
	}

	
	
	
	
	
}
