package org.bankofspring.service;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.dao.AccountTransactionDAO;
import org.bankofspring.model.Account;
import org.bankofspring.model.AccountTransaction;
import org.bankofspring.model.BankOperationType;
import org.bankofspring.model.User;
import org.bankofspring.validator.BankOperationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service( "bankService" )
public class BankOfSpringServiceImpl implements BankOfSpringService {

	@Autowired
	private BankOperationValidator validator;

	@Autowired
	@Qualifier("jdbcAccountDao")
	private AccountDAO accountDAO;

	@Autowired
	private AccountTransactionDAO accountTransactionDAO;

	/**
	 * transfer will now rollback for either a TransferException or any other Exception
	 */
	@Transactional( rollbackFor=TransferException.class )
	public boolean transfer( User loggedInUser, Account fromAccount, Account toAccount, long amount ) {
		if ( !validator.validateOperation( loggedInUser, fromAccount, toAccount, amount, BankOperationType.TRANSFER ) ) {
			throw new TransferException("Validation failed");
		}
		
		if ( !accountDAO.updateAccountBalance( fromAccount, fromAccount.getAccountBalance() - amount ) ) {
			throw new TransferException("Failed to update from account");
		}
		
		if ( !accountDAO.updateAccountBalance( toAccount, toAccount.getAccountBalance() + amount ) ) {
			throw new TransferException("Failed to update to account");
		}
		
		if ( !accountTransactionDAO.create( new AccountTransaction( toAccount, fromAccount, amount ) ) ) {
			throw new TransferException("Failed to create account transaction");
		}
		
		return true;
	}

	/**
	 *
	 */
	public boolean withdraw( User loggedInUser, Account fromAccount, long amount ) {
		if ( !validator.validateOperation( loggedInUser, fromAccount, null, amount, BankOperationType.WITHDRAWAL ) ) {
			return false;
		}

		if ( !accountDAO.updateAccountBalance( fromAccount, fromAccount.getAccountBalance() - amount ) ) {
			return false;
		}
		
		return accountTransactionDAO.create( new AccountTransaction( null, fromAccount, amount ) );
	}

	/**
	 *
	 */
	public boolean deposit( User loggedInUser, Account toAccount, long amount ) {
		if ( !validator.validateOperation( loggedInUser, null, toAccount, amount, BankOperationType.DEPOSIT ) ) {
			return false;
		}

		if ( !accountDAO.updateAccountBalance( toAccount, toAccount.getAccountBalance() + amount ) ) {
			return false;
		}
		
		return accountTransactionDAO.create( new AccountTransaction( toAccount, null, amount ) );
	}

	public BankOperationValidator getValidator() {
		return validator;
	}

	public void setValidator( BankOperationValidator validator ) {
		this.validator = validator;
	}
}
