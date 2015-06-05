package org.bankofspring.dao;

import org.bankofspring.model.Account;


/**
 * @author slc
 *
 */
public interface AccountDAO {
	public boolean debitAccount(Account account, Long amount);
	public boolean creditAccount(Account account, Long amount);
}
