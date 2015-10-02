package org.bankofspring.dao.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bankofspring.model.Customer;
import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setId(rs.getInt("id"));
		customer.setName(rs.getString("name"));
		customer.setUsername(rs.getString("username"));
		customer.setPassword(rs.getString("password"));
		return customer;
	}

}
