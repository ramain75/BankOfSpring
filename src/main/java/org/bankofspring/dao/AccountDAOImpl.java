package org.bankofspring.dao;

import org.bankofspring.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author slc
 *
 */
@Repository
public class AccountDAOImpl extends AbstractDao implements AccountDAO {

	@Autowired
	private SimpleJdbcTemplate jdbc;

	/**
	 * Debit an account
	 * @param account
	 * @param amount
	 */
	public boolean debitAccount(Account account, Long amount) {
		try {
			if (account.debitAccount(amount)) {
				getHibernateTemplate().update(account);
				return true;
			}
		} catch (NullPointerException npe) {
			System.err.println("Incorrect Amount: " + npe.getMessage());
		}
		return false;

	}

	/**
	 * Credit an account
	 * @param account
	 * @param amount
	 */
	public boolean creditAccount(Account account, Long amount) {
		try {
			if (account.creditAccount(amount)) {
				getHibernateTemplate().update(account);
				return true;
			}
		} catch (NullPointerException npe) {
			System.err.println("Incorrect Amount: " + npe.getMessage());
		}
		return false;
	}

	/**
	 * Get an account by its account number
	 * 
	 * @param accountNumber
	 * @return account object
	 */
	public Account getAccountByName(String accountNumber) {
		return getHibernateTemplate().get(Account.class, accountNumber);
	}

}
