package org.bankofspring.dao;

import java.sql.SQLException;
import java.util.List;

import org.bankofspring.model.Account;
import org.junit.*;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:BankOfSpring.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BankOfSpringDAOTest {

	@Autowired
	AccountsDaoImpl accountsDao;
	
	/**
	 * Basic test to make sure we have a DB connection
	 * @throws SQLException 
	 */
	@Test
	public void testConnectionIsValid() throws SQLException{
		assertTrue(accountsDao.getDataSource().getConnection().isValid(1));
	}
	
	@Test
	public void testGetAccounts(){
		
		List<Account> accounts = accountsDao.getAccounts();
		
		assertEquals("We have accounts in the db", 4, accounts.size());
		assertEquals("First row should be account 1", "account1" ,accounts.get(0).getAccountNumber());
		assertEquals("First row should be account 2", "account1" ,accounts.get(0).getAccountNumber());
		assertEquals("First row should be account 3", "account1" ,accounts.get(0).getAccountNumber());
		assertEquals("First row should be account 4", "account1" ,accounts.get(0).getAccountNumber());	
	}
	
}
