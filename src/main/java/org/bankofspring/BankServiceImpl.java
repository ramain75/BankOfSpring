package org.bankofspring;

import org.bankofspring.model.Account;
import org.bankofspring.model.AccountTransaction;
import org.bankofspring.model.BankOperationType;
import org.bankofspring.model.User;

public class BankServiceImpl implements BankService {
	protected BankOperationValidator validator;
	public BankOperationValidator getValidator() {
		return validator;
	}

	public void setValidator(BankOperationValidator validator) {
		this.validator = validator;
	}

	@Override
	public boolean debit(User loggedInUser, Account fromAccount,
			Account toAccount, long amount) {
		
		if (validator.validateOperation(loggedInUser, fromAccount, toAccount, amount, BankOperationType.DEBIT)) {
			AccountTransaction accountTxn = new AccountTransaction(fromAccount,toAccount,amount);
			return fromAccount.addAccountTransaction(accountTxn,BankOperationType.DEBIT);
		}
		return false;
		
	}

	@Override
	public boolean debit(User loggedInUser, Account fromAccount,
			long amount) {
		
		return debit(loggedInUser, fromAccount, null, amount);
	}

	@Override
	public boolean credit(User loggedInUser, Account fromAccount,
			Account toAccount, long amount) {
		if (validator.validateOperation(loggedInUser, fromAccount, toAccount, amount, BankOperationType.CREDIT)) {
			AccountTransaction accountTxn = new AccountTransaction(fromAccount,toAccount,amount);
			return toAccount.addAccountTransaction(accountTxn,BankOperationType.CREDIT);
		}
		return false;
	}

	@Override
	public boolean credit(User loggedInUser, Account toAccount,
			long amount) {
		return credit(loggedInUser, null,toAccount, amount);
	}
	
	

}
