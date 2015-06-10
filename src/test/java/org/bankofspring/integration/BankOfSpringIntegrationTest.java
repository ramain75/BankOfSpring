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
		//check how many txn in database : should be 1
		assertEquals(1,getNumberOfTransactions());
		Account account1 = dao.getAccount("account1");
		assertNotNull(account1);
		long balance = account1.getAccountBalance();
		assertEquals(0,balance);
		// check balance also in database
		long balanceDB = getBalanceOfAccount("account1");
		assertEquals(balance,balanceDB);
		
		Customer cust = account1.getOwningCustomers().get(0);
		assertNotNull(cust);
		assertEquals("customer one", cust.getName());
		assertTrue(service.credit(cust, account1, 200L));
		assertEquals(balance + 200L, account1.getAccountBalance());
		
		assertEquals(2,getNumberOfTransactions());
		balanceDB = getBalanceOfAccount("account1");
		assertEquals(account1.getAccountBalance(),balanceDB);
		
		AccountTransaction txn = dao.getAccountTransaction(2);
		assertEquals(200L,txn.getTransactionAmount());
		assertEquals(account1,txn.getToAccount());
		assertNull(txn.getFromAccount());
	}
	@Test
	public void testCredit() {
		//check how many txn in database : should be 1
		assertEquals(1,getNumberOfTransactions());
		Account account1 = dao.getAccount("account1");
		Account account3 = dao.getAccount("account3");
		assertNotNull(account1);
		assertNotNull(account3);
		long balance = account1.getAccountBalance();
		assertEquals(0,balance);
		// check balance also in database
		long balanceDB = getBalanceOfAccount("account1");
		assertEquals(balance,balanceDB);
		
		long balance3 = account3.getAccountBalance();
		assertEquals(100,balance3);
		// check balance also in database
		long balanceDB3 = getBalanceOfAccount("account3");
		assertEquals(balance3,balanceDB3);
		
		Customer cust = account1.getOwningCustomers().get(0);
		assertNotNull(cust);
		assertEquals("customer one", cust.getName());
		assertTrue(service.credit(cust, account1, account3,50L));
		assertEquals(balance + 50L, account1.getAccountBalance());
		
		assertEquals(2,getNumberOfTransactions());
		balanceDB = getBalanceOfAccount("account1");
		assertEquals(account1.getAccountBalance(),balanceDB);
		
		AccountTransaction txn = dao.getAccountTransaction(2);
		assertEquals(50L,txn.getTransactionAmount());
		assertEquals(account1,txn.getToAccount());
		assertEquals(account3,txn.getFromAccount());
	}
	private int getNumberOfTransactions() {
		SimpleJdbcTemplate template = new SimpleJdbcTemplate(ds);
		return template.queryForInt("select count(*) from account_transaction");
	}
	
	private int getBalanceOfAccount(String account) {
		SimpleJdbcTemplate template = new SimpleJdbcTemplate(ds);
		return template.queryForInt("select balance from account where number =?", account);
	}
}
