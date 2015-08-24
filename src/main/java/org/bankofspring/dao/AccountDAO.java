package org.bankofspring.dao;

import org.bankofspring.model.Account;


/**
 * @author slc
 *
 */
public interface AccountDAO {
	Account getAccountByNumber( String accountName );
	boolean updateAccountBalance( Account account, Long amount );
	boolean addNewAccount( Account account );
}
