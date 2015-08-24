package org.bankofspring.dao;

import org.bankofspring.model.AccountTransaction;


/**
 * @author slc
 *
 */
public interface AccountTransactionDAO {

	boolean create(AccountTransaction accountTransaction);
}
