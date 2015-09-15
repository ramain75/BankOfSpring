package org.bankofspring.dao;

import java.util.List;

import org.bankofspring.model.Account;


/**
 * @author slc
 *
 */
public interface AccountDAO {
	Account getAccountByNumber( String accountName );
	boolean updateAccountBalance( Account account, Long amount );
	boolean updateAccount(Account account);
	boolean addNewAccount( Account account );
	List<Account> getAccountsForCustomer (int id);
}
