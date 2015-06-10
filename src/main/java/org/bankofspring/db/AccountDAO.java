package org.bankofspring.db;

import org.bankofspring.model.Account;

public interface AccountDAO {

	Account getByNumber( String number );
}
