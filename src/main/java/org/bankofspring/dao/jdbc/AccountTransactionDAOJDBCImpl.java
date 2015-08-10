package org.bankofspring.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import org.bankofspring.dao.AccountTransactionDAO;
import org.bankofspring.model.AccountTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author slc
 *
 */
@Repository
public class AccountTransactionDAOJDBCImpl implements AccountTransactionDAO {

	@Autowired
	private SimpleJdbcTemplate jdbc;
	
	/**
	 *
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public boolean create( AccountTransaction accountTransaction ) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "from", accountTransaction.getFromAccount() != null ? accountTransaction.getFromAccount().getAccountNumber() : null );
		params.put( "to", accountTransaction.getToAccount() != null ? accountTransaction.getToAccount().getAccountNumber() : null );
		params.put( "amount", accountTransaction.getTransactionAmount() );
		params.put( "time", accountTransaction.getTransactionDate() );
		int updates = jdbc.update( "INSERT INTO account_transaction (from_account_number, to_account_number, amount, time) VALUES (:from, :to, :amount, :time)", params );
		return updates != 0;
	}

}
