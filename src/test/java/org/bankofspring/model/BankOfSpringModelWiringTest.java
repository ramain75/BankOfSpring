package org.bankofspring.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BankOfSpringModelWiringTest {

	private ApplicationContext context;
	@Before
	public void setup() {
		context = new ClassPathXmlApplicationContext("BankOfSpringAppData.xml");
		
	}
	
	@Test
	public void testWiringCustomer1() {
		Customer customer1 = context.getBean("customer1",Customer.class);
		assertNotNull(customer1);
		assertEquals("user1",customer1.getUsername());
		assertEquals("test",customer1.getPassword());
		assertEquals("customerone",customer1.getName());
		assertEquals("customer1",customer1.getCustomerID());
		assertNotNull(customer1.getAccounts());
		List<Account> accounts = customer1.getAccounts();
		assertEquals(2, accounts.size());
		Account account1 = accounts.get(0);
		Account account2 = accounts.get(1);
		assertEquals("account1",account1.getAccountNumber());
		assertEquals("account1description",account1.getAccountDescription());
		assertEquals("account2",account2.getAccountNumber());
		assertEquals("account2description",account2.getAccountDescription());
	}

}
