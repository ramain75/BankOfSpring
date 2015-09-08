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
	boolean addNewAccount( Account account );

	List<Account> getAccountsForUsername( String username );
}
