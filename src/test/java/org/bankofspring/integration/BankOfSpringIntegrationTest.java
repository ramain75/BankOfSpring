package org.bankofspring.integration;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.bankofspring.dao.BankOfSpringDAO;
import org.bankofspring.model.Account;
import org.bankofspring.model.AccountTransaction;
import org.bankofspring.model.Customer;
import org.bankofspring.service.BankOfSpringService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:BankOfSpring.xml",
		"classpath:BankOfSpring-ds-test.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BankOfSpringIntegrationTest {

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private DataSource ds;
	@Autowired
	private BankOfSpringDAO dao;

	@Autowired
	@Qualifier("bankService")
	private BankOfSpringService service;

	@Test
	public void testCashCredit() {
		// check how many txn in database : should be 1
		assertEquals(1, getNumberOfTransactions());

		// check and get account
		Account account1 = checkAccount("account1", 0);
		long balance = account1.getAccountBalance();
		// check customer
		Customer cust = account1.getOwningCustomers().get(0);
		assertNotNull(cust);
		assertEquals("customer one", cust.getName());

		// call service credit
		assertTrue(service.credit(cust, account1, 200L));
		assertEquals(balance + 200L, account1.getAccountBalance());

		// check balance of account again
		account1 = checkAccount("account1", 200L);

		// check number of transactions and content of last transaction
		assertEquals(2, getNumberOfTransactions());
		AccountTransaction txn = dao.getAccountTransaction(2);
		assertEquals(200L, txn.getTransactionAmount());
		assertEquals(account1, txn.getToAccount());
		assertNull(txn.getFromAccount());
	}

	@Test
	public void testCredit() {
		// check how many txn in database : should be 1
		assertEquals(1, getNumberOfTransactions());

		// check and get both accounts
		Account account1 = checkAccount("account1", 0);
		long balanceAccount1 = account1.getAccountBalance();
		Account account3 = checkAccount("account3", 100);

		// check customer account1
		Customer cust = account1.getOwningCustomers().get(0);
		assertNotNull(cust);
		assertEquals("customer one", cust.getName());
		assertTrue(service.credit(cust, account1, account3, 50L));
		assertEquals(balanceAccount1 + 50L, account1.getAccountBalance());

		// check number of transactions
		assertEquals(2, getNumberOfTransactions());
		// check acccount 1 has been credited
		account1 = checkAccount("account1", 50L);
		// account 3 has not been debited !!! TODO
		account3 = checkAccount("account3", 100);

		AccountTransaction txn = dao.getAccountTransaction(2);
		assertEquals(50L, txn.getTransactionAmount());
		assertEquals(account1, txn.getToAccount());
		assertEquals(account3, txn.getFromAccount());
	}

	@Test
	public void testDebit() {
		// check how many txn in database : should be 1
		assertEquals(1, getNumberOfTransactions());
		
		// check and get both accounts
		Account account1 = checkAccount("account1", 0);
		Account account3 = checkAccount("account3", 100);
		long balanceAccount3 = account3.getAccountBalance();
		// check customer account3
		Customer cust = account3.getOwningCustomers().get(0);
		assertNotNull(cust);
		assertEquals("customer two", cust.getName());
		assertTrue(service.debit(cust, account3, account1, 50L));
		assertEquals(balanceAccount3 - 50L, account3.getAccountBalance());

		// check number of transactions
		assertEquals(2, getNumberOfTransactions());
		// check acccount 3 has been debited
		account3 = checkAccount("account3", 50L);
		// account 1 has not been credited !!! TODO
		account1 = checkAccount("account1", 0);

		AccountTransaction txn = dao.getAccountTransaction(2);
		assertEquals(50L, txn.getTransactionAmount());
		assertEquals(account1, txn.getToAccount());
		assertEquals(account3, txn.getFromAccount());
	}

	@Test
	public void testCashDebit() {
		// check how many txn in database : should be 1
		assertEquals(1, getNumberOfTransactions());
		
		// check account
		Account account3 = checkAccount("account3", 100);
		long balanceAccount3 = account3.getAccountBalance();
		// check customer account3
		Customer cust = account3.getOwningCustomers().get(0);
		assertNotNull(cust);
		assertEquals("customer two", cust.getName());
		assertTrue(service.debit(cust, account3, null, 50L));
		assertEquals(balanceAccount3 - 50L, account3.getAccountBalance());

		// check number of transactions
		assertEquals(2, getNumberOfTransactions());
		// check acccount 3 has been debited
		account3 = checkAccount("account3", 50L);
		AccountTransaction txn = dao.getAccountTransaction(2);
		assertEquals(50L, txn.getTransactionAmount());
		assertNull(txn.getToAccount());
		assertEquals(account3, txn.getFromAccount());
	}

	private int getNumberOfTransactions() {
		SimpleJdbcTemplate template = new SimpleJdbcTemplate(ds);
		return template.queryForInt("select count(*) from account_transaction");
	}

	private int getBalanceOfAccount(String account) {
		SimpleJdbcTemplate template = new SimpleJdbcTemplate(ds);
		return template.queryForInt(
				"select balance from account where number =?", account);
	}

	private Account checkAccount(String accountNumber, long expectedBalance) {
		Account account = dao.getAccount(accountNumber);
		assertNotNull(account);
		long balance = account.getAccountBalance();
		assertEquals(expectedBalance, balance);
		// check balance also in database
		long balanceDB = getBalanceOfAccount(accountNumber);
		assertEquals(balance, balanceDB);
		return account;
	}
}
