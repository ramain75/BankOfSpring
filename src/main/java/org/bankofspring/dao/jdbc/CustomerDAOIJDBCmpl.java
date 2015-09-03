package org.bankofspring.dao.jdbc;

import java.util.List;

import org.bankofspring.dao.CustomerDAO;
import org.bankofspring.dao.jdbc.rowmapper.CustomerRowMapper;
import org.bankofspring.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOIJDBCmpl implements CustomerDAO {
	private final String CUSTOMERS_SELECT = "SELECT c.id, c.name, c.email, c.description  from customer c order by c.id";
	
	@Autowired
	private SimpleJdbcTemplate jdbc;
	
	@Override
	public Customer getCustomerById(Integer id) {
		return jdbc.queryForObject( "SELECT id, name, email,description  FROM customer AS c  WHERE c.id = ?", new CustomerRowMapper(), id );
	}

	@Override
	public List<Customer> getCustomers() {
	return  jdbc.query(CUSTOMERS_SELECT, new CustomerRowMapper() );
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCustomer(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
