package org.bankofspring.model;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
public class CustomerTest {
	@Test
	public void testEquals() {
		Customer customer1 = new Customer("test","password","cust1", "customer1");
		Customer customer2 = new Customer("test","password","cust1", "customer1");
		assertEquals(customer1, customer2);
		
		customer1 = new Customer("test","password","cust1", "customer1");
		customer2 = new Customer("test","password","cust1", "customer2");
		assertNotEquals(customer1, customer2);
		
		customer1 = new Customer("test","password","cust1", "customer1");
		customer2 = new Customer("test","password","cust2", "customer1");
		assertNotEquals(customer1, customer2);
		
		customer1 = new Customer("test","password",null, "customer1");
		customer2 = new Customer("test","password",null, "customer1");
		assertEquals(customer1, customer2);
	}
	@Test
	public void testBuildCustomer() {
		Customer customer1 = new Customer("test","password", "customer1","cust1");
		
		ArrayList<Customer> listCustomers = new ArrayList<Customer>();
		listCustomers.add(customer1);
		Account account = new Account("account1", "account1description", listCustomers, 0L);
		
		Map<String,Account> accountMap = new HashMap<String,Account>();
		accountMap.put(account.getAccountNumber(),account);
		
		assertEquals("cust1",customer1.getCustomerID());
		assertEquals("customer1",customer1.getName());
		assertEquals("test",customer1.getUsername());
		assertEquals("password",customer1.getPassword());
		
	}
	/**
	 * test setting,adding and deleting accounts from customer
	 */
	@Test 
	public void testCustomerAccounts() {
		Customer customer1 = new Customer("test","password", "customer1","cust1");
		
		ArrayList<Customer> listCustomers = new ArrayList<Customer>();
		listCustomers.add(customer1);
		Account account = new Account("account1", "account1description", listCustomers, 0L);

		//test set account
		Map<String,Account> accountMap = new HashMap<String,Account>();
		accountMap.put(account.getAccountNumber(),account);
		customer1.setAccounts(accountMap);
		assertEquals(accountMap,customer1.getAccounts());
		assertEquals(account, customer1.getAccount("account1"));
		//test add account
		Account account2 = new Account("account2", "account2description", listCustomers, 0L);
		customer1.addAccount(account2);
		assertEquals(2,customer1.getAccounts().keySet().size());
		assertEquals(account2, customer1.getAccount("account2"));
		//test adding same account does not do anyrthing
		customer1.addAccount(account2);
		assertEquals(2,customer1.getAccounts().keySet().size());
		assertEquals(account2, customer1.getAccount("account2"));
		// test get account list
		List <Account> accounts = customer1.getAccountsList();
		assertEquals(2,accounts.size());
		assertTrue("does not contain expected account", accounts.contains(account));
		assertTrue("does not contain expected account", accounts.contains(account2));
		//test delete account
		customer1.deleteAccount("account1");
		assertEquals(1,customer1.getAccounts().keySet().size());
		assertNull(customer1.getAccount("account1"));
		//test delete unexisting account
		customer1.deleteAccount("account1");
		assertEquals(1,customer1.getAccounts().keySet().size());
		assertEquals(account2, customer1.getAccount("account2"));
	}
}
