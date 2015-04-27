package org.bankofspring.service;

import org.bankofspring.model.Account;
import org.bankofspring.model.AccountTransaction;
import org.bankofspring.model.User;

/**
 * Implementation of a Bank Service. Fairly simple currently but we will build
 * this up over the coming weeks.
 * 
 * @author ram
 *
 */
public class BankServiceImpl implements BankService {

	/**
	 * {@inheritDoc}
	 */
	public boolean debit(User loggedInUser, Account fromAccount,
			Account toAccount, long amount) {

		if (null == loggedInUser) {
			throw new RuntimeException(
					"Transactions can only be performed by an authorised User");
		}

		if (amount <= 0) {
			throw new RuntimeException(
					"Amount is invalid for a debit transaction");
		}

		if (null == fromAccount) {
			throw new RuntimeException(
					"fromAccount cannot be null for a debit transaction");
		}

		// Is there enough money in the account?
		if (fromAccount.getAccountBalance() <= amount) {
			return false;
		}

		// Negate the amount for a debit when creating the transaction
		AccountTransaction txn = new AccountTransaction(fromAccount, toAccount,
				-amount);
		return fromAccount.applyTransaction(txn);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean debit(User loggedInUser, Account fromAccount, long amount) {
		return debit(loggedInUser, fromAccount, null, amount);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean credit(User loggedInUser, Account toAccount,
			Account fromAccount, long amount) {

		if (null == loggedInUser) {
			throw new RuntimeException(
					"Transactions can only be performed by an authorised User");
		}

		if (amount <= 0) {
			throw new RuntimeException(
					"Amount is invalid for a credit transaction");
		}

		if (null == toAccount) {
			throw new RuntimeException(
					"toAccount cannot be null for a credit transaction");
		}

		// check the amount is not greater than MAX_BALANCE before adding to the
		// account balance
		// to prevent any possibility of the sum being larger than
		// Long.MAX_VALUE.
		if (amount > MAX_BALANCE
				|| ((toAccount.getAccountBalance() + amount) > MAX_BALANCE)) {
			return false;
		}

		AccountTransaction txn = new AccountTransaction(fromAccount, toAccount,
				amount);
		return toAccount.applyTransaction(txn);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean credit(User loggedInUser, Account toAccount, long amount) {
		return credit(loggedInUser, toAccount, null, amount);
	}

}
