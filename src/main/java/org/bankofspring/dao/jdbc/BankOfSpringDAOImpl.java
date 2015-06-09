package org.bankofspring.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.bankofspring.dao.BankOfSpringDAO;
import org.bankofspring.model.Account;
import org.bankofspring.model.AccountTransaction;
import org.bankofspring.model.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

public class BankOfSpringDAOImpl implements BankOfSpringDAO {
	
	private final String  CUSTOMER_SELECT = "SELECT c.id, c.name, c.username, u.password from customer c inner join user u on (c.username = u.username)";
	private final String  CUSTOMERS_SELECT = CUSTOMER_SELECT +  " order by c.id";
	private final String  SINGLE_CUSTOMER_SELECT =  CUSTOMER_SELECT + "where c.id = ?";
		private final String  CUSTOMERS_FOR_ACCOUNT_SELECT = "SELECT c.id, c.name, c.username, u.password from customer c "+
							"inner join user u on (c.username = u.username) inner join customer_account a "
							+ "on (a.customer_id = c.id) where a.number =?";
	private final String TXN_ACCOUNT_SELECT = "select id,from_account_number, to_account_number, time, amount from account_transaction where"
			+ " id = ?";
	private final String TXN_ACCOUNT_ADD = "insert into account_transaction (from_account_number, to_account_number,  amount, time) values " +
			"( :from_account_number,:to_account_number,:amount,:time)";

	private final String  ACCOUNT_SELECT =  "SELECT number, description,balance from account where number=?";
	private final String ACCOUNT_UPDATE = "UPDATE account set balance=? where number = ?";
	
	private SimpleJdbcTemplate simpleTemplate;
	private NamedParameterJdbcTemplate namedTemplate;
	
	public SimpleJdbcTemplate getSimpleTemplate() {
		return simpleTemplate;
	}

	public void setSimpleTemplate(SimpleJdbcTemplate simpleTemplate) {
		this.simpleTemplate = simpleTemplate;
	}
	
	public NamedParameterJdbcTemplate getNamedTemplate() {
		return namedTemplate;
	}

	public void setNamedTemplate(NamedParameterJdbcTemplate namedTemplate) {
		this.namedTemplate = namedTemplate;
	}
	
	@Override
	public List<Customer> getCustomers() {
		 return getSimpleTemplate().query(CUSTOMERS_SELECT, new RowMapper<Customer>() {

			@Override
			public Customer mapRow(ResultSet res, int rowNum)
					throws SQLException {
				String id = res.getString(1);
				String name = res.getString(2);
				String userName = res.getString(3);
				String password = res.getString(4);
				Customer customer = new Customer(userName,password,name,id);
				return customer;
				
			}
			
		});
	}

	@Override
	public List<Customer> getCustomersForAccount(String accountNumber) {
		 return getSimpleTemplate().query(CUSTOMERS_FOR_ACCOUNT_SELECT, new RowMapper<Customer>() {

			@Override
			public Customer mapRow(ResultSet res, int rowNum)
					throws SQLException {
				String id = res.getString(1);
				String name = res.getString(2);
				String userName = res.getString(3);
				String password = res.getString(4);
				Customer customer = new Customer(userName,password,name,id);
				return customer;
				
			}
			
		}, accountNumber);
	}

	@Override
	public Account getAccount(String accountNumber) {
		return getSimpleTemplate().queryForObject(ACCOUNT_SELECT, new RowMapper<Account>() {
		
			@Override
			public Account mapRow(ResultSet res, int rowNum)
					throws SQLException {
				String id = res.getString(1);
				String description = res.getString(2);
				long balance = res.getLong(3);
				Account account = new Account(id,description,null);
				account.setAccountBalance(balance);
				List<Customer> customers = getCustomersForAccount(account.getAccountNumber());
				account.setOwningCustomers(customers);
				return account;
				
			}
			
		},accountNumber);
	}


	@Override
	public Customer getCustomer(String customerId) {
		return getSimpleTemplate().queryForObject(SINGLE_CUSTOMER_SELECT, new RowMapper<Customer>() {
			
			@Override
			public Customer mapRow(ResultSet res, int rowNum)
					throws SQLException {
				String id = res.getString(1);
				String name = res.getString(2);
				String userName = res.getString(3);
				String password = res.getString(4);
				Customer customer = new Customer(userName,password,name,id);
				return customer;
			}
		},customerId);
	}


	@Override
	public Account updateAccountBalance(String accountNumber, long balance) {
		 getSimpleTemplate().update(ACCOUNT_UPDATE, balance,accountNumber);
		 return getAccount(accountNumber);
	}

	@Override
	public AccountTransaction getAccountTransaction(int id) {
return getSimpleTemplate().queryForObject(TXN_ACCOUNT_SELECT, new RowMapper<AccountTransaction>() {
			
			@Override
			public AccountTransaction mapRow(ResultSet res, int rowNum)
					throws SQLException {
			
				int id = res.getInt(1);
				String from_account_number = res.getString(2);
				String to_account_number = res.getString(3);
				Date txnDate = res.getDate(4);
				long  txnAmount = res.getLong(5);
				Account fromAccount = null;
				Account toAccount = null;
				if (from_account_number != null) {
					fromAccount = getAccount(from_account_number);
				}
				if (to_account_number != null) {
					toAccount = getAccount(to_account_number);
				}
				AccountTransaction txn = new AccountTransaction(id, toAccount, fromAccount, txnAmount);
				txn.setTransactionDate(txnDate);
				return txn;
			}
		},id);
	}

	@Override
	public AccountTransaction createAccountTransaction(
			String fromAccountNumber, String toAccountNumber, long amount) {
		 MapSqlParameterSource source = new MapSqlParameterSource();
		 source.addValue("from_account_number",fromAccountNumber);
		 source.addValue("to_account_number",toAccountNumber);
		 source.addValue("amount",amount);
		 source.addValue("time", new Date());
		 GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		 getNamedTemplate().update(TXN_ACCOUNT_ADD, source,keyHolder);
		 return getAccountTransaction(keyHolder.getKey().intValue());
	}

	

}
