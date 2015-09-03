package org.bankofspring.dao.jdbc;


import static org.junit.Assert.*;

import org.bankofspring.dao.UserDAO;
import org.bankofspring.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:BankOfSpring-ds-test.xml", "classpath:BankOfSpring.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDAOJDBCTest {
	@Autowired
	private UserDAO userDAO;
	
	@Test
	public void testGetUserByName() {
		User user = userDAO.getUserByName("user1");
		assertNotNull(user);
		assertEquals("user1",user.getUsername());
		assertEquals("test",user.getPassword());
	}
	@Test(expected=Exception.class)
	public void testGetUnknwonUserThrowsException() {
		 userDAO.getUserByName("user56");
	}
}
