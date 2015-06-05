package org.bankofspring.model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
	
	private Customer customer1;
	private Customer customer2;
	
	@Before
	public void setup() {
		customer1 = new Customer();
		customer1.setUsername("user");
		customer1.setPassword("test");
		customer1.setId(1);
		customer1.setName("customer1");
		customer2 = new Customer();
		customer2.setUsername("user");
		customer2.setPassword("test");
		customer2.setId(1);
		customer2.setName("customer1");
	}
	
	@Test
	public void testEquals() {
		assertEquals(customer1, customer2);
	}
	
	@Test
	public void testEqualsDifferentName() {
		customer2.setName("bob");
		assertNotEquals(customer1, customer2);
	}
	
	@Test
	public void testEqualsDifferentId() {
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
	public void testBuildCustomer() {
		Account account = new Account();
		account.setAccountNumber( "account1" );
		account.setAccountDescription( "account1description" );
		
		Map<String,Account> accountMap = new HashMap<String,Account>();
		accountMap.put(account.getAccountNumber(),account);
		
		assertEquals((Integer)1,customer1.getId());
		assertEquals("customer1",customer1.getName());
		assertEquals("user",customer1.getUsername());
		assertEquals("test",customer1.getPassword());
	}
}
