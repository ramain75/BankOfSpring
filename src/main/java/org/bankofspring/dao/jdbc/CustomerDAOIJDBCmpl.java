package org.bankofspring.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.bankofspring.dao.CustomerDAO;
import org.bankofspring.dao.jdbc.rowmapper.CustomerRowMapper;
import org.bankofspring.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOIJDBCmpl implements CustomerDAO {
	private final String CUSTOMERS_SELECT = "SELECT c.id, c.name, c.username from customer c order by c.id";
	
	@Autowired
	private SimpleJdbcTemplate jdbc;
	
	@Override
	public Customer getCustomerById(Integer id) {
		return jdbc.queryForObject( "SELECT id, name, u.username, password FROM customer AS c LEFT JOIN user AS u ON c.username = u.username WHERE id = ?", new CustomerRowMapper(), id );
	}

	@Override
	public List<Customer> getCustomers() {
	return  jdbc.query(CUSTOMERS_SELECT, new RowMapper<Customer>() {
	@	Override
		public Customer mapRow(ResultSet res, int rowNum)	throws SQLException {
			int id = res.getInt(1);
			String name = res.getString(2);
			Customer customer = new Customer();
			customer.setName(name);
			customer.setId(id);
			return customer;
		}
	});
	}

}
