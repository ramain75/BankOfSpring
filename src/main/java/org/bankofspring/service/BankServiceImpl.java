/**
 * 
 */
package org.bankofspring.service;

import org.bankofspring.dao.BankDAO;
import org.bankofspring.entities.Account;
import org.bankofspring.entities.User;
import org.bankofspring.validator.BankValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sean
 *
 */
@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDAO bankDAO;
	
	@Autowired
	private BankValidator bankValidator;
	
	@Autowired
	private User currentUser;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean transferMoney(long fromAccountId, long toAccountId,
			long amount) {
		// get the accounts
		Account fromAccount = bankDAO.getAccount(fromAccountId);
		Account toAccount = bankDAO.getAccount(toAccountId);
		
		// do some simple validation
		boolean valid = bankValidator.validateTransfer(fromAccount, toAccount, amount);
		
		// if it's all good lets do the transfer
		if (valid) {
			bankDAO.debitAccount(fromAccount, amount);
			bankDAO.creditAccount(toAccount, amount);
			bankDAO.logTransaction(fromAccount, toAccount, amount);
		}
		
		return valid;
	}

	public void setBankDAO(BankDAO bankDAO) {
		this.bankDAO = bankDAO;
	}
	
	public void setBankValidator(BankValidator bankValidator) {
		this.bankValidator = bankValidator;
	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
}
