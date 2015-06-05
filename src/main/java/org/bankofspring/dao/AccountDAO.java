package org.bankofspring.dao;

import org.bankofspring.model.Account;


/**
 * @author slc
 *
 */
public interface AccountDAO {
	boolean debitAccount(Account account, Long amount);
	boolean creditAccount(Account account, Long amount);
	Account getAccountByName(String accountName);
}
