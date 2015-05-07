package org.bankofspring.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		Map<String, Account> accounts = customer1.getAccounts();
		assertEquals(2, accounts.size());
		
		Account account1 = accounts.get("account1");
		List<Customer> expectedCust = new ArrayList<Customer>();
		expectedCust.add(customer1);
		checkAccount(account1,"account1","account1description", 170000000L,0L, expectedCust);
		
		Account account2 = accounts.get("account2");
		checkAccount(account2,"account2","account2description", 100000000L,0L, expectedCust);
		
	}
	
	private void checkAccount(Account account, String number, String description, long maxBalance, long balance, List<Customer> customers) {
		assertEquals(number,account.getAccountNumber());
		assertEquals(description,account.getAccountDescription());
		assertEquals(maxBalance,account.getMaxBalanceAmount());
		assertEquals(balance,account.getAccountBalance());
		assertEquals(customers.size(),account.getOwningCustomers().size());
	}
	
	@Test
	public void testWiringCustomer2() {
		Customer customer2 = context.getBean("customer2",Customer.class);
		Customer customer1 = context.getBean("customer1",Customer.class);
		assertNotNull(customer2);
		assertEquals("user2",customer2.getUsername());
		assertEquals("test",customer2.getPassword());
		assertEquals("customertwo",customer2.getName());
		assertEquals("customer2",customer2.getCustomerID());
		assertNotNull(customer2.getAccounts());
		Map<String, Account> accounts = customer2.getAccounts();
		assertEquals(2, accounts.size());
		
		Account account3 = accounts.get("account3");
		List<Customer> expectedCust = new ArrayList<Customer>();
		expectedCust.add(customer2);
		checkAccount(account3,"account3","account3description", 150000000L,100L, expectedCust);
		expectedCust = new ArrayList<Customer>();
		expectedCust.add(customer2);
		expectedCust.add(customer1);
		Account account4 = accounts.get("account4");
		checkAccount(account4,"account4","account4description", 100000000L,150L, expectedCust);
		
	}

}
