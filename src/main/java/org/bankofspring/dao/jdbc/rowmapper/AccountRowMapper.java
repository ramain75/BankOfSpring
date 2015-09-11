package org.bankofspring.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bankofspring.model.Account;
import org.springframework.jdbc.core.RowMapper;


/**
 * @author slc
 *
 */
public class AccountRowMapper implements RowMapper<Account> {

	/**
	 *
	 */
	public Account mapRow( ResultSet rs, int rowNum ) throws SQLException {
		Account account = new Account();
		account.setAccountNumber( rs.getString( "number" ) );
		account.setAccountDescription( rs.getString( "description" ) );
		account.setAccountBalance( rs.getLong( "balance" ) );
		account.setMaxBalanceAmount( rs.getLong( "max_balance") );
		account.setCustomerId(rs.getInt("customer_id"));
		return account;
	}

}
