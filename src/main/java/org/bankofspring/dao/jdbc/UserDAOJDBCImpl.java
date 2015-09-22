package org.bankofspring.dao.jdbc;

import org.bankofspring.dao.UserDAO;
import org.bankofspring.dao.jdbc.rowmapper.UserRowMapper;
import org.bankofspring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("jdbcUserDao")
public class UserDAOJDBCImpl implements UserDAO {
	@Autowired
	private SimpleJdbcTemplate jdbc;
	@Override
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		return jdbc.queryForObject( "SELECT username, password FROM user AS u  WHERE u.username = ?", new UserRowMapper(), name );
	}

}
