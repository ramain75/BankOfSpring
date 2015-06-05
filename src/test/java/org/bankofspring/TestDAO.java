package org.bankofspring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.dao.AccountTransactionDAO;
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
public class TestDAO {

	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private AccountTransactionDAO accountTransactionDAO;
	
	@Autowired
	private SimpleJdbcTemplate jdbcTemplate;
	
	private Account account1 = new Account();
	private Account account2 = new Account();
	private Account account3 = new Account();
	
	@Before
	public void setup() {
		account1.setAccountNumber( "account1" );
		account2.setAccountNumber( "account2" );
		account3.setAccountNumber( "account3" );
	}
	
	@Test
	public void test() throws Exception {
		accountDAO.debitAccount( account3, 100L );
	}
	
	@Test
	public void testTable() throws Exception {
		assertEquals( SimpleJdbcTestUtils.countRowsInTable( jdbcTemplate, "account_transaction" ), 1);
		accountTransactionDAO.create( new AccountTransaction( account1, account2, 5 ) );
		assertEquals( SimpleJdbcTestUtils.countRowsInTable( jdbcTemplate, "account_transaction" ), 2);
		System.out.println(jdbcTemplate.queryForList( "select * from account_transaction" ));
	}
	
	@Test
	public void testGetAccount() throws Exception {
		Account ac1 = accountDAO.getAccountByName( "account3" );
		assertNotNull( ac1 );
		System.out.println(ac1);
	}
}
