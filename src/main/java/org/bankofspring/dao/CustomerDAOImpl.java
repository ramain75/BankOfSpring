package org.bankofspring.dao;

import org.bankofspring.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl extends AbstractDao implements CustomerDAO {

	@Autowired
	private SimpleJdbcTemplate jdbc;
	
	@Override
	public Customer getCustomerById(Integer id) {
		return getHibernateTemplate().get(Customer.class, id);
	}

}
