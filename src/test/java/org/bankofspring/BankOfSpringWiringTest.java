package org.bankofspring;

import static org.junit.Assert.*;

import org.bankofspring.model.Account;
import org.bankofspring.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:BankOfSpringApp.xml")
//
// for info the annotations above and the autowire annotations below allow us to dispense with
//  ApplicationContext context = new ClassPathXmlApplicationContext("BankOfSpringApp.xml");
//	Customer customer1 = context.getBean("customer1", Customer.class);
// and so for for every bean defined in the spring context

public class BankOfSpringWiringTest {
	
	@Autowired
	@Qualifier("customer1")
	private Customer customer1;
	
	@Autowired
	@Qualifier("customer2")
	private Customer customer2;
	
	@Autowired
	private BankServiceImpl service;
	
	@Test
	public void testContextBasic() {

		// check first customer, we expect 2 bank accounts linked to this customer
		assertNotNull(customer1);
		assertEquals("TestCustomer1",customer1.getCustomerName());
		assertEquals("User1",customer1.getUserName());
		assertEquals("CUSTOMER-1",customer1.getCustomerID());
		assertEquals(2,customer1.getAccounts().size());
		//check first bank account
		Account account1Cust1 = customer1.getAccounts().get(0);
		assertNotNull(account1Cust1);
		assertEquals("account1cust1", account1Cust1.getAccountNumber());
		assertEquals(100, account1Cust1.getAccountAmount());
		assertEquals(customer1, account1Cust1.getCustomer());
		//check second bank account
		Account account2Cust1 = customer1.getAccounts().get(1);
		assertNotNull(account2Cust1);
		assertEquals("account2cust1", account2Cust1.getAccountNumber());
		assertEquals(50, account2Cust1.getAccountAmount());
		assertEquals(customer1, account2Cust1.getCustomer());
		
		//customer2
		assertNotNull(customer2);
		assertEquals("TestCustomer2",customer2.getCustomerName());
		assertEquals("User2",customer2.getUserName());
		assertEquals("CUSTOMER-2",customer2.getCustomerID());
		assertEquals(1,customer2.getAccounts().size());
		//check bank account
		Account account1Cust2 = customer2.getAccounts().get(0);
		assertNotNull(account1Cust2);
		assertEquals("account1cust2", account1Cust2.getAccountNumber());
		assertEquals(125L, account1Cust2.getAccountAmount());
		assertEquals(customer2, account1Cust2.getCustomer());
	}
	
	public void testContextServiceAndValidator() {
		assertNotNull(service);
		assertNotNull(service.getValidator());
	}

}
