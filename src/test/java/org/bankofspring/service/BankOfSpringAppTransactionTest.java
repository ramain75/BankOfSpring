package org.bankofspring.service;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.dao.CustomerDAO;
import org.bankofspring.model.Account;
import org.bankofspring.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/*
 * This test class tests it all together
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:BankOfSpring-ds-test.xml", "classpath:BankOfSpring.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BankOfSpringAppTransactionTest {
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	@Qualifier("bankService")
	private BankOfSpringService service;
	
	@Autowired
	@Qualifier("jdbcAccountDao")
	private AccountDAO accountDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	ApplicationContext context;
	
	@Test
	public void testTransferToOtherAccount() throws Exception {
		Account account1 = getAccount("account1");
		Account account3 = getAccount("account3");
		long balanceAccount1 = account1.getAccountBalance();
		long balanceAccount3 = account3.getAccountBalance();
		System.out.println(balanceAccount1);
		System.out.println(balanceAccount3);
		connectAndRemoveTable();
		try {
		assertTrue(service.transfer(getCustomer(1), account3, account1,100L));
		fail();
		} catch (Exception e) {
			
		}
		// the amount should not have changed
		assertEquals(balanceAccount1 , getAccount("account1").getAccountBalance());
		assertEquals(balanceAccount3 , getAccount("account3").getAccountBalance());
		
	}
	
	private Customer getCustomer(Integer id) {
		Customer customer = customerDAO.getCustomerById(id);
		assertNotNull(customer);
		return customer;
	}
	
	private Account getAccount(String accountNumber) {
		Account account  = accountDAO.getAccountByNumber( accountNumber );
		assertNotNull(account);
		return account;
	}
	//we delete the account_transaction table to cause the error at the end of the transfer operation
	private void connectAndRemoveTable() throws Exception {
		    SimpleDriverDataSource db =  context.getBean("dataSource",SimpleDriverDataSource.class );
		    Connection conn = db.getConnection();
		    PreparedStatement statement = conn.prepareStatement("drop table account_transaction");
		    statement.execute();
		    conn.close();
	}
}
