package org.bankofspring.service;

import org.bankofspring.dao.UserDAO;
import org.bankofspring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SecurityService implements UserDetailsService {

	@Autowired
	private UserDAO userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User u = userDao.getUserByUsername(username);
		if (u == null) {
			throw new UsernameNotFoundException("No username: " + username);
		}
		return u;
	}

}
