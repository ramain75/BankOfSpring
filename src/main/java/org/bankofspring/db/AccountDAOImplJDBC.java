package org.bankofspring.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.bankofspring.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImplJDBC implements AccountDAO {

	private static final String SQL_GET_ACCOUNT_BY_NUMBER = 
			"SELECT number, description, balance FROM account WHERE number = :number";
	
	@Autowired
	private SimpleJdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate( SimpleJdbcTemplate jdbcTemplate ) {
		this.jdbcTemplate = jdbcTemplate;
	}
			
	public Account getByNumber( String number ) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("number", number);
		
		return jdbcTemplate.queryForObject(SQL_GET_ACCOUNT_BY_NUMBER, new AccountRowMapper(), params);
		
	}

	class AccountRowMapper implements RowMapper<Account>
	{
		public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
			Account account = new Account(rs.getString("number"), rs.getString("description"), null);
			account.setAccountBalance(rs.getLong("balance"));
			return account;
		}
	}
	
}
