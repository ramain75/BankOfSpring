package org.bankofspring.dao.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import org.bankofspring.dao.AbstractAccountDAOTest;
import org.bankofspring.dao.AccountDAO;
import org.bankofspring.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:BankOfSpring-ds-test.xml", "classpath:BankOfSpring.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountDAOJDBCTest extends AbstractAccountDAOTest {

	@Autowired
	@Qualifier("jdbcAccountDao")
	private AccountDAO accountDAO;

	@Override
	protected AccountDAO getDao() {
		return this.accountDAO;
	}
	@Test
	public void testListAccountsByCustomer () {
		List<Account> accounts = accountDAO.getAccountsForCustomer(1);
		assertEquals(accounts.size(),3);
		accounts = accountDAO.getAccountsForCustomer(2);
		assertEquals(accounts.size(),2);
		accounts = accountDAO.getAccountsForCustomer(3);
		assertEquals(accounts.size(),0);
	}
	
}
