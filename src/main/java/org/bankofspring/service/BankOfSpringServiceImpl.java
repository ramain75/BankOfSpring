
package org.bankofspring.service;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.dao.AccountTransactionDAO;
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
	private AccountDAO accountDAO;

	@Autowired
	private AccountTransactionDAO accountTransactionDAO;

	/**
   *
   */
	public boolean transfer( User loggedInUser, Account fromAccount, Account toAccount, long amount ) {
		if ( !validator.validateOperation( loggedInUser, fromAccount, toAccount, amount, BankOperationType.TRANSFER ) ) {
			return false;
		}

		AccountTransaction txn = new AccountTransaction( toAccount, fromAccount, amount );
		// at a later stage, we'll ensure all these things happen
		return accountDAO.debitAccount( fromAccount, amount ) && accountDAO.creditAccount( toAccount, amount ) && accountTransactionDAO.create( txn );
	}

	/**
   *
   */
	public boolean withdraw( User loggedInUser, Account fromAccount, long amount ) {
		if ( !validator.validateOperation( loggedInUser, fromAccount, null, amount, BankOperationType.WITHDRAWAL ) ) {
			return false;
		}
		AccountTransaction txn = new AccountTransaction( null, fromAccount, amount );

		// at a later stage, we'll ensure all these things happen
		return accountDAO.debitAccount( fromAccount, amount ) && accountTransactionDAO.create( txn );
	}

	/**
   *
   */
	public boolean deposit( User loggedInUser, Account toAccount, long amount ) {
		if ( !validator.validateOperation( loggedInUser, null, toAccount, amount, BankOperationType.DEPOSIT ) ) {
			return false;
		}

		AccountTransaction txn = new AccountTransaction( toAccount, null, amount );
		// at a later stage, we'll ensure all these things happen
		return accountDAO.creditAccount( toAccount, amount ) && accountTransactionDAO.create( txn );
	}

	public BankOperationValidator getValidator() {
		return validator;
	}

	public void setValidator( BankOperationValidator validator ) {
		this.validator = validator;
	}
}
