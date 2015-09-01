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
@ContextConfiguration({"classpath:BankOfSpring-ds.xml", "classpath:BankOfSpring.xml"})
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
	public void testListAccounts() {
		List<Account> accounts = getDao().listAccounts();
		
		assertNotNull( accounts );
		assertEquals( 4, accounts.size() );
		
		// Assert each account
		assertEquals( "account1", accounts.get(0).getAccountNumber() );
		assertEquals( "account2", accounts.get(1).getAccountNumber() );
		assertEquals( "account3", accounts.get(2).getAccountNumber() );
		assertEquals( "account4", accounts.get(3).getAccountNumber() );

		// More information about account 3
		assertEquals( "account3description", accounts.get(2).getAccountDescription() );
		assertEquals( 100L, accounts.get(2).getAccountBalance() );
		assertEquals( 1000000000, accounts.get(2).getMaxBalanceAmount() );
	}
}
