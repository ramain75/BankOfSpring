package org.bankofspring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.bankofspring.model.Account;
import org.bankofspring.model.AccountTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class AccountsDaoImpl extends SimpleJdbcDaoSupport implements AccountsDao {
	
	
	public List<Account> getAccounts(){
		
		String getAccountsSql = "SELECT * FROM account";
			
		return getSimpleJdbcTemplate().query(getAccountsSql, new RowMapper<Account>() {

			@Override
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				Account account = new Account(rs.getString("number"), rs.getString("description"), null, rs.getLong("balance"));
				return account;
			}
		});
	}

	@Override
	public boolean saveTransaction(AccountTransaction transaction) {
		boolean success = saveTransaction(transaction);
		
		if(success){
			success = saveAccountDetails(transaction);
		}
		
		return success;
	}
	
	//Add the transaction record to the 
	private boolean saveTransactionDetails(AccountTransaction transaction){
		String txnUpdateSql = "INSERT into account_transaction (from_account_number, to_account_number, amount"
				+ " VALUES(?,?,?)";
		
		try{
			getSimpleJdbcTemplate().update(txnUpdateSql, new Object[]{
					transaction.getFromAccount().getAccountNumber(),
					transaction.getToAccount().getAccountNumber(),
					transaction.getTransactionAmount()
					});
			
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
	
	private boolean saveAccountDetails(AccountTransaction transaction){
		 return false;
	}
}
