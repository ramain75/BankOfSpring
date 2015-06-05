package org.bankofspring.dao;

import org.bankofspring.dao.rowmapper.AccountRowMapper;
import org.bankofspring.dao.rowmapper.CustomerRowMapper;
import org.bankofspring.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SimpleJdbcTemplate jdbc;
	
	@Override
	public Customer getCustomerById(Integer id) {
		return jdbc.queryForObject( "SELECT id, name FROM customer WHERE id = ?", new CustomerRowMapper(), id );
	}

}
