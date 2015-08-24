package org.bankofspring.dao.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.dao.jdbc.rowmapper.AccountRowMapper;
import org.bankofspring.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * @author slc
 *
 */
@Repository("jdbcAccountDao")
public class AccountDAOJDBCImpl implements AccountDAO {

	@Autowired
	private SimpleJdbcTemplate jdbc;
	
	/**
	  * @param jdbc the jdbc to set
	  */
	public void setJdbc( SimpleJdbcTemplate jdbc ) {
		this.jdbc = jdbc;
	}
 
	/**
	  * @return the jdbc
	  */
	public SimpleJdbcTemplate getJdbc() {
		return this.jdbc;
	}

	@Override
	public boolean updateAccountBalance( Account account, Long amount ) {
		if ( ( account == null ) || ( amount == null ) ) {
			return false;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
	  	params.put( "amount", amount );
	  	params.put( "accountNumber", account.getAccountNumber() );
	  	int updates = jdbc.update( "UPDATE account SET balance = :amount WHERE number = :accountNumber", params );
	  	return ( updates == 1 );
	}
	
	@Override
	public boolean addNewAccount( Account account ) {
		if ( ( account == null ) ||
				( account.getAccountNumber() == null ) ||
				( account.getAccountDescription() == null ) ) {
			
			return false;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
	  	params.put( "accountNumber", account.getAccountNumber() );
	  	params.put( "description", account.getAccountDescription() );
	  	params.put( "balance", account.getAccountBalance() );
	  	params.put( "maxBalance", account.getMaxBalanceAmount() );
	  	
	  	int updates = jdbc.update(
	  		"INSERT INTO account ( number, description, balance, max_balance )" +
	  		" VALUES ( :accountNumber, :description, :balance, :maxBalance )",
	  		params );
	  	
	  	return ( updates == 1 );
	}
  
	@Override
	public Account getAccountByNumber( String accountNumber ) {
		try {
			return jdbc.queryForObject(
					"SELECT number, description, balance, max_balance FROM account WHERE number = ?",
					new AccountRowMapper(), accountNumber );
		}
		catch ( EmptyResultDataAccessException e ) {
			return null; // Account not found
		}
	}

	@Override
	public List<Account> getCustomerAccounts(Integer customer) {
		return jdbc.query("select number, description, balance, max_balance FROM account acc LEFT JOIN customer_account ca ON acc.number = ca.number WHERE customer_id = ?", new AccountRowMapper(), customer);
	}
}
