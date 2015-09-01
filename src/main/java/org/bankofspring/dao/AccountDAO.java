package org.bankofspring.dao;

import java.util.List;

import org.bankofspring.model.Account;


/**
 * @author slc
 *
 */
public interface AccountDAO {
	List<Account> listAccounts();
	Account getAccountByNumber( String accountName );
	boolean updateAccountBalance( Account account, Long amount );
	boolean addNewAccount( Account account );
}
