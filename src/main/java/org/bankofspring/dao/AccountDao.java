package org.bankofspring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bankofspring.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String GET_ACCOUNT_BY_ID = "SELECT * FROM account WHERE account_number = ?";
	private static final String UPDATE_ACCOUNT_BALANCE = "UPDATE account SET account_balance = ? WHERE account_number = ?";
	
	RowMapper<Account> accountRowMapper = new RowMapper<Account>() {
		@Override
		public Account mapRow( ResultSet rs, int rowNum ) throws SQLException {
			String accountNumber = rs.getString( "account_number" );
			String accountDescription = rs.getString( "account_description" );
			long accountBalance = rs.getLong( "account_balance" );
			long maxBalanceAmount = rs.getLong( "max_balance_amount" );
			
			Account account = new Account( accountNumber, accountDescription, null );
			account.setAccountBalance( accountBalance );
			account.setMaxBalanceAmount( maxBalanceAmount );
			return account;
		}
	};
	
	public Account getAccountById( String accountId ) {
		return jdbcTemplate.queryForObject( GET_ACCOUNT_BY_ID, new Object[] { accountId }, accountRowMapper );
	}
	
	public boolean updateAccountBalance( String accountId, long newBalance ) {
		int rowsUpdated = jdbcTemplate.update( UPDATE_ACCOUNT_BALANCE, new Object[] { newBalance, accountId } );
				
		if ( rowsUpdated > 1 ) {
			throw new IncorrectUpdateSemanticsDataAccessException(
					"updateAccountBalance expected to update 1 row, updated " + rowsUpdated );
		}
		
		return ( rowsUpdated == 1 );
	}

	public boolean debitAccount( Account account, long amount ) {
		return updateAccountBalance( account.getAccountNumber(), account.getAccountBalance() - amount );
	}
	
	public boolean creditAccount( Account account, long amount ) {
		return updateAccountBalance( account.getAccountNumber(), account.getAccountBalance() + amount );
	}
}
