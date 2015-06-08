package org.bankofspring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.bankofspring.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class AccountsDaoImpl extends SimpleJdbcDaoSupport implements AccountsDao {
	

	@Autowired
	private JdbcTemplate jdbcTemplate;
		
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
}
