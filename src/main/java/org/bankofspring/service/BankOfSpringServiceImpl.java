
package org.bankofspring.service;

import org.bankofspring.dao.BankOfSpringDAO;
import org.bankofspring.model.Account;
import org.bankofspring.model.AccountTransaction;
import org.bankofspring.model.BankOperationType;
import org.bankofspring.model.User;
import org.bankofspring.validator.BankOperationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service( "bankService" )
public class BankOfSpringServiceImpl implements BankOfSpringService {

	@Autowired
	private BankOperationValidator validator;
	@Autowired
	private BankOfSpringDAO dao;
	
	public BankOperationValidator getValidator() {
		return validator;
	}

	public void setValidator( BankOperationValidator validator ) {
		this.validator = validator;
	}

	public boolean debit( User loggedInUser, Account fromAccount,
	    Account toAccount, long amount ) {
		if ( validator.validateOperation( loggedInUser, fromAccount, toAccount, amount, BankOperationType.DEBIT ) ) {
			AccountTransaction txn = new AccountTransaction( 0, fromAccount, toAccount, -amount );
			// at a later stage, will ensure we credit the account receiving the money but not at this stage
			if ( fromAccount.applyTransaction( txn )) {
				String toAccountNumber = toAccount == null ? null : toAccount.getAccountNumber();
				txn = dao.createAccountTransaction(fromAccount.getAccountNumber(), toAccountNumber, amount);
				dao.updateAccountBalance(fromAccount.getAccountNumber(), fromAccount.getAccountBalance());
				return true;
			}
		}
		return false;
	}

	public boolean debit( User loggedInUser, Account fromAccount, long amount ) {
		return debit( loggedInUser, fromAccount, null, amount );

	}

	public boolean credit( User loggedInUser, Account toAccount,
	    Account fromAccount, long amount ) {

		if ( validator.validateOperation( loggedInUser, fromAccount, toAccount, amount, BankOperationType.CREDIT ) ) {
			AccountTransaction txn = new AccountTransaction( 0, fromAccount, toAccount, amount );
			// at a later stage, will ensure we debit the account releasing the money but not at this stage

			if (toAccount.applyTransaction( txn ) ) {
				String fromAccountNumber = fromAccount == null ? null : fromAccount.getAccountNumber();
				txn = dao.createAccountTransaction(fromAccountNumber, toAccount.getAccountNumber(), amount);
				dao.updateAccountBalance(toAccount.getAccountNumber(), toAccount.getAccountBalance());
				return true;
			}
		}
		return false;
	}

	public boolean credit( User loggedInUser, Account toAccount, long amount ) {
		// TODO Auto-generated method stub
		return credit( loggedInUser, toAccount, null, amount );
	}

}
