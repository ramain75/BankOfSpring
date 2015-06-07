package org.bankofspring.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.bankofspring.dao.BankOfSpringDAO;
import org.bankofspring.model.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class BankOfSpringDAOImpl implements BankOfSpringDAO {
	private final String  CUSTOMER_SELECT = "SELECT c.id, c.name, c.username, u.password from customer c inner join user u on (c.username = u.username) order by c.id";
	private SimpleJdbcTemplate template;
	
	
	public SimpleJdbcTemplate getTemplate() {
		return template;
	}


	public void setTemplate(SimpleJdbcTemplate template) {
		this.template = template;
	}


	@Override
	public List<Customer> getCustomers() {
		 return getTemplate().query(CUSTOMER_SELECT, new RowMapper<Customer>() {

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

}
