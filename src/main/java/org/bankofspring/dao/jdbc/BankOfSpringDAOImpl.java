package org.bankofspring.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.bankofspring.dao.BankOfSpringDAO;
import org.bankofspring.model.Account;
import org.bankofspring.model.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class BankOfSpringDAOImpl implements BankOfSpringDAO {
	private final String  CUSTOMER_SELECT = "SELECT c.id, c.name, c.username, u.password from customer c inner join user u on (c.username = u.username)";
	private final String  CUSTOMERS_SELECT = CUSTOMER_SELECT +  " order by c.id";
	private final String  SINGLE_CUSTOMER_SELECT =  CUSTOMER_SELECT + "where c.id = ?";
	private final String  ACCOUNT_SELECT =  "SELECT number, description,balance from account where number=?";
	private final String  CUSTOMERS_FOR_ACCOUNT = "SELECT c.id, c.name, c.username, u.password from customer c "+
							"inner join user u on (c.username = u.username) inner join customer_account a "
							+ "on (a.customer_id = c.id) where a.number =?";
	private SimpleJdbcTemplate template;
	
	
	public SimpleJdbcTemplate getTemplate() {
		return template;
	}


	public void setTemplate(SimpleJdbcTemplate template) {
		this.template = template;
	}

	
	@Override
	public List<Customer> getCustomers() {
		 return getTemplate().query(CUSTOMERS_SELECT, new RowMapper<Customer>() {

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
		 return getTemplate().query(CUSTOMERS_FOR_ACCOUNT, new RowMapper<Customer>() {

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
		return getTemplate().queryForObject(ACCOUNT_SELECT, new RowMapper<Account>() {
		
			@Override
			public Account mapRow(ResultSet res, int rowNum)
					throws SQLException {
				String id = res.getString(1);
				String description = res.getString(2);
				long balance = res.getLong(3);
				Account account = new Account(id,description,null);
				account.setAccountBalance(balance);
				return account;
				
			}
			
		},accountNumber);
	}


	@Override
	public Customer getCustomer(String customerId) {
		return getTemplate().queryForObject(SINGLE_CUSTOMER_SELECT, new RowMapper<Customer>() {
			
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

}
