package org.bankofspring.dao;

import java.util.List;

import org.bankofspring.model.User;

public interface UserDAO {

	List<User> getAllUsers();

	User getUserByUsername(String username);
}
