package org.bankofspring.model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
	
	private Customer customer1;
	private Customer customer2;
	
	@Before
	public void setup() {
		customer1 = new Customer();
		customer1.setId(1);
		customer1.setName("customer1");
		customer1.setEmail("customer1@gmail.com");
		customer1.setDescription("blahblah");
		customer2 = new Customer();
		customer2.setId(1);
		customer2.setName("customer1");
		customer1.setEmail("customer1@gmail.com");
		customer1.setDescription("blahblah");
	}
	
	@Test
	public void testEquals() {
		assertEquals(customer1, customer2);
	}
	
	@Test
	public void testNotEqualsDifferentName() {
		customer2.setName("bob");
		assertNotEquals(customer1, customer2);
	}
	
	@Test
	public void testNotEqualsDifferentId() {
		customer2.setId(2);
		assertNotEquals(customer1, customer2);
	}
	
	@Test
	public void testEqualsNoId() {
		customer1.setId(null);
		customer2.setId(null);
		assertEquals(customer1, customer2);
	}
	@Test
	public void testEqualsDifferentEmail() {
		customer1.setEmail("customer1@gmail.com");
		customer2.setEmail("customer2@hotmail.com");
		assertEquals(customer1, customer2);
	}
	@Test
	public void testEqualsDifferentDescriptionl() {
		customer1.setDescription("I am customer 1");
		customer2.setDescription("I am NOT customer 1");
		assertEquals(customer1, customer2);
	}
	
	@Test
	public void testBuildCustomer() {
		Account account = new Account();
		account.setAccountNumber( "account1" );
		account.setAccountDescription( "account1description" );
		
		Map<String,Account> accountMap = new HashMap<String,Account>();
		accountMap.put(account.getAccountNumber(),account);
		
		assertEquals((Integer)1,customer1.getId());
		assertEquals("customer1",customer1.getName());
		
	}
}
