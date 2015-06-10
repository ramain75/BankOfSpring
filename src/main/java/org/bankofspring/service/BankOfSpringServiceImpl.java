
package org.bankofspring.service;

import org.bankofspring.dao.AccountDao;
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
	private AccountDao accountDao;

	public BankOperationValidator getValidator() {
		return validator;
	}

	public void setValidator( BankOperationValidator validator ) {
		this.validator = validator;
	}
	
	/** DAO setter - useful for unit tests */
	public void setAccountDao( AccountDao dao ) {
		this.accountDao = dao;
	}

	public boolean debit( User loggedInUser, Account fromAccount,
	    Account toAccount, long amount ) {
		if ( validator.validateOperation( loggedInUser, fromAccount, toAccount, amount, BankOperationType.DEBIT ) ) {
			AccountTransaction txn = new AccountTransaction( fromAccount, toAccount, -amount );
			
			// at a later stage, will ensure we credit the account receiving the money but not at this stage
			if ( fromAccount.applyTransaction( txn ) ) {
				return accountDao.debitAccount( fromAccount, amount );
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
			AccountTransaction txn = new AccountTransaction( fromAccount, toAccount, amount );
			// at a later stage, will ensure we debit the account releasing the money but not at this stage

			if ( toAccount.applyTransaction( txn ) ) {
				return accountDao.creditAccount( toAccount, amount );
			}
		}
		return false;
	}

	public boolean credit( User loggedInUser, Account toAccount, long amount ) {
		// TODO Auto-generated method stub
		return credit( loggedInUser, toAccount, null, amount );
	}

}
