package org.bankofspring.dao.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.dao.jdbc.rowmapper.AccountRowMapper;
import org.bankofspring.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * @author slc
 *
 */
@Repository("jdbcAccountDao")
public class AccountDAOJDBCImpl implements AccountDAO {
	private static final String LIST_ACCOUNTS = "select number, description, balance, max_balance, ca.customer_id from"
			+ " account a join customer_account ca on (a.number = ca.number) where ca.customer_id = ?";
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
	  		  	
	  	int noUpdate = jdbc.update(
	  		"INSERT INTO account ( number, description, balance, max_balance )" +
	  		" VALUES ( :accountNumber, :description, :balance, :maxBalance )",
	  		params );
	    
	  	if (noUpdate == 1) {
	  		return addCustomerAccount(account.getAccountNumber(),account.getCustomerId());
	  	}
	  	return false;
	  	
	}
	
	private boolean addCustomerAccount (String accountNumber, int customerId) {
		Map<String, Object> params = new HashMap<String, Object>();
	  	params.put( "accountNumber", accountNumber );
	  	params.put( "customerId", customerId );
		int noUpdate = jdbc.update(
		  		"INSERT INTO customer_account ( customer_id, number)" +
		  		" VALUES ( :customerId, :accountNumber )",
		  		params );
		return noUpdate == 1 ;
	}
  
	@Override
	public Account getAccountByNumber( String accountNumber ) {
		try {
			return jdbc.queryForObject(
					"SELECT number, description, balance, max_balance, customer_id FROM account a join customer_account ca on (a.number = ca.number)  WHERE number = ?",
					new AccountRowMapper(), accountNumber );
		}
		catch ( EmptyResultDataAccessException e ) {
			return null; // Account not found
		}
	}

	@Override
	public List<Account> getAccountsForCustomer(int id) {
		// TODO Auto-generated method stub
		return jdbc.query(LIST_ACCOUNTS, new AccountRowMapper(),id);
	}

	@Override
	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}
}
