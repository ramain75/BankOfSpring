package org.bankofspring.dao;

import org.bankofspring.model.User;

public interface UserDAO {
	User getUserByName(String name);
}
