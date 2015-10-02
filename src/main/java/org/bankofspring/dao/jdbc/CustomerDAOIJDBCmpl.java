package org.bankofspring.dao.jdbc;

import org.bankofspring.dao.CustomerDAO;
import org.bankofspring.dao.jdbc.rowmapper.CustomerRowMapper;
import org.bankofspring.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("jdbcCustomerDao")
public class CustomerDAOIJDBCmpl implements CustomerDAO {

	@Autowired
	private SimpleJdbcTemplate jdbc;
	
	@Override
	public Customer getCustomerById(Integer id) {
		return jdbc.queryForObject( "SELECT id, name, u.username, password FROM customer AS c LEFT JOIN user AS u ON c.username = u.username WHERE id = ?", new CustomerRowMapper(), id );
	}

}
