package org.bankofspring.dao.jdbc;

import static org.junit.Assert.*;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.model.Account;
import org.junit.Before;
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
public class AccountDAOJDBCTest {

	@Autowired
	private AccountDAO accountDAO;
	
	private Account account1;
	private Account account3;
	
	@Before
	public void setup() {
		account1 = accountDAO.getAccountByName("account1");
		account3 = accountDAO.getAccountByName("account3");
	}
	
	@Test
	public void testCreditAccount() throws Exception {
		assertTrue(accountDAO.creditAccount(account1, 100L));
		assertEquals(account1.getAccountBalance() + 100, accountDAO.getAccountByName("account1").getAccountBalance());
	}
	
	@Test
	public void testCreditAccountTooMuch() throws Exception {
		assertFalse(accountDAO.creditAccount(account1, 1000000001L));
		assertEquals(account1.getAccountBalance(), accountDAO.getAccountByName("account1").getAccountBalance());
	}
	
	@Test
	public void testCreditAccountNull() throws Exception {
		assertFalse(accountDAO.creditAccount(account1, null));
		assertEquals(account1.getAccountBalance(), accountDAO.getAccountByName("account1").getAccountBalance());
	}
	
	@Test
	public void testDebitAccount() throws Exception {
		assertTrue(accountDAO.debitAccount(account3, 100L));
		assertEquals(account3.getAccountBalance() - 100, accountDAO.getAccountByName("account3").getAccountBalance());
	}
	
	@Test
	public void testDebitAccountTooMuch() throws Exception {
		assertFalse(accountDAO.debitAccount(account3, 101L));
		assertEquals(account3.getAccountBalance(), accountDAO.getAccountByName("account3").getAccountBalance());
	}
	
	@Test
	public void testDebitAccountNull() throws Exception {
		assertFalse(accountDAO.debitAccount(account3, null));
		assertEquals(account3.getAccountBalance(), accountDAO.getAccountByName("account3").getAccountBalance());
	}
	
	@Test
	public void testGetAccount() {
		Account testAccount = accountDAO.getAccountByName("account3");
		assertEquals(100L, testAccount.getAccountBalance());
		assertEquals("account3description", testAccount.getAccountDescription());
		assertEquals("account3", testAccount.getAccountNumber());
		assertEquals(1000000000, testAccount.getMaxBalanceAmount()); 
	}
}
