package org.bankofspring.dao.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bankofspring.dao.CustomerDAO;
import org.bankofspring.dao.jdbc.rowmapper.CustomerRowMapper;
import org.bankofspring.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOIJDBCmpl implements CustomerDAO {
	private final String CUSTOMERS_SELECT = "SELECT c.id, c.name, c.email, c.description  from customer c order by c.id";
	private final String CUSTOMER_INSERT = "INSERT INTO CUSTOMER (name,email,description) values " +
			"(:name,:email,:description)";
	@Autowired
	private SimpleJdbcTemplate jdbc;
	@Autowired
	private NamedParameterJdbcTemplate namedTemplate;
	
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
		Map<String,Object > map = new HashMap<String,Object>();
		map.put("name", customer.getName());
		map.put("email", customer.getEmail());
		map.put("description", customer.getDescription());
		map.put("id", customer.getId());
		int result = jdbc.update("UPDATE CUSTOMER SET name = :name, email = :email, description = :description where id = :id",map);
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCustomer(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer addCustomer(String name,String email,String description) {
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("name",name);
		source.addValue("email",email);
		source.addValue("description",description);
		this.namedTemplate.update(CUSTOMER_INSERT,source,keyHolder);
		return getCustomerById(keyHolder.getKey().intValue());
	}

}
