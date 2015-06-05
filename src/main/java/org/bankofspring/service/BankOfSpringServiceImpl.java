
package org.bankofspring.service;

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

	public BankOperationValidator getValidator() {
		return validator;
	}

	public void setValidator( BankOperationValidator validator ) {
		this.validator = validator;
	}

	/**
   *
   */
  public boolean transfer( User loggedInUser, Account fromAccount, Account toAccount, long amount ) {
		if ( validator.validateOperation( loggedInUser, fromAccount, toAccount, amount, BankOperationType.TRANSFER ) ) {
			AccountTransaction txn = new AccountTransaction( toAccount, fromAccount, amount );
			// at a later stage, will ensure we debit the account releasing the money but not at this stage

			return fromAccount.applyTransaction( txn ) && toAccount.applyTransaction( txn );
		}
		return false;
  }

	/**
   *
   */
  public boolean withdraw( User loggedInUser, Account fromAccount, long amount ) {
  	if ( validator.validateOperation( loggedInUser, fromAccount, null, amount, BankOperationType.WITHDRAWAL ) ) {
			AccountTransaction txn = new AccountTransaction(null , fromAccount, amount );
			// at a later stage, will ensure we debit the account releasing the money but not at this stage

			return fromAccount.applyTransaction( txn );
		}
		return false;
  }

	/**
   *
   */
  public boolean deposit( User loggedInUser, Account toAccount, long amount ) {
  	if ( validator.validateOperation( loggedInUser, null, toAccount, amount, BankOperationType.DEPOSIT ) ) {
			AccountTransaction txn = new AccountTransaction( toAccount, null, amount );
			return toAccount.applyTransaction( txn );
		}
		return false;
  }

}
