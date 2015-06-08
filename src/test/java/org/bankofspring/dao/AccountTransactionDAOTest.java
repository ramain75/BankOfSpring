package org.bankofspring.dao;

import static org.junit.Assert.assertEquals;

import org.bankofspring.model.Account;
import org.bankofspring.model.AccountTransaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:BankOfSpring-ds-test.xml", "classpath:BankOfSpring.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountTransactionDAOTest {

	@Autowired
	private AccountTransactionDAO accountTransactionDAO;
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private SimpleJdbcTemplate jdbcTemplate;
	
	private Account account1;
	private Account account3;
	
	@Before
	public void setup() {
		account1 = accountDAO.getAccountByName("account1");
		account3 = accountDAO.getAccountByName("account3");
	}
	
	@Test
	public void testCreate() throws Exception {
		assertEquals( SimpleJdbcTestUtils.countRowsInTable( jdbcTemplate, "account_transaction" ), 1);
		accountTransactionDAO.create( new AccountTransaction( account1, account3, 5 ) );
		assertEquals( SimpleJdbcTestUtils.countRowsInTable( jdbcTemplate, "account_transaction" ), 2);
	}
	
}
