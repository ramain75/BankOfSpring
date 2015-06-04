package org.bankofspring.db;

import org.bankofspring.model.AccountTransaction;

public interface AccountTransactionDAO {

	AccountTransaction getById( String id );
}
