package org.bankofspring.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.bankofspring.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImplJDBC implements CustomerDAO {

	private static final String SQL_GET_CUSTOMER_BY_ID = 
			"SELECT c.username, u.password, c.name, c.id FROM customer c JOIN user u ON c.username = u.username WHERE id = :id";
	
	@Autowired
	private SimpleJdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate( SimpleJdbcTemplate jdbcTemplate ) {
		this.jdbcTemplate = jdbcTemplate;
	}
			
	public Customer getById( String strId ) {
		
		Long id = null;
		try {
			id = Long.parseLong(strId);
		}
		catch (NumberFormatException nfe) {
			return null;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		return jdbcTemplate.queryForObject(SQL_GET_CUSTOMER_BY_ID, new CustomerRowMapper(), params);
		
	}

	class CustomerRowMapper implements RowMapper<Customer>
	{
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer(rs.getString("username"), rs.getString("password"), rs.getString("name"), String.valueOf(rs.getLong("id")));
			return customer;
		}
	}
	
}
