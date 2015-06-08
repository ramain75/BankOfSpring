package org.bankofspring.dao;

import java.util.HashMap;
import java.util.Map;

import org.bankofspring.dao.rowmapper.AccountRowMapper;
import org.bankofspring.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * @author slc
 *
 */
@Repository
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	private SimpleJdbcTemplate jdbc;
	
	/**
   *
   */
  public boolean debitAccount( Account account, Long amount ) {
  	Map<String, Object> params = new HashMap<String, Object>();
  	params.put( "amount", amount );
  	params.put( "accountNumber", account.getAccountNumber() );
  	int updates = jdbc.update( "UPDATE account SET balance = balance - :amount WHERE balance - :amount >= 0 AND number = :accountNumber", params );
  	return updates > 0;
  }

	/**
   *
   */
  public boolean creditAccount( Account account, Long amount ) {
  	Map<String, Object> params = new HashMap<String, Object>();
  	params.put( "amount", amount );
  	params.put( "accountNumber", account.getAccountNumber() );
  	int updates = jdbc.update( "UPDATE account SET balance = balance + :amount WHERE balance + :amount <= max_balance AND number = :accountNumber", params );
  	return updates > 0;
  }
  
	/**
  *
  */
 public Account getAccountByName( String accountName ) {
	 return jdbc.queryForObject( "SELECT number, description, balance, max_balance FROM account WHERE number = ?", new AccountRowMapper(), accountName );
 }

  
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
	  return jdbc;
  }
}
