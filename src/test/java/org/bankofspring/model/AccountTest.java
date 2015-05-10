package org.bankofspring.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AccountTest {

	/*
	 * applying a null accountTransaction does nothing
	 */
	@Test
	public void testApplyNullTransaction() {
		Account account = new Account("account1", "account2",null);
		assertTrue(account.getTransactions().isEmpty());
		account.applyTransaction(null);
		assertTrue(account.getTransactions().isEmpty());
	}
	/*
	 * test adding customer to an account
	 */
	@Test
	public void testAddCustomer() {
		Customer customer1 = new Customer("user","test","customer1","cust1");
		Account account = new Account("account1", "account2",null);
		account.addCustomer(customer1);
		assertEquals(1,account.getOwningCustomers().size());
		//re-add customer shoudl not change anything
		account.addCustomer(customer1);
		assertEquals(1,account.getOwningCustomers().size());
		
		//adding null customer does not do anything
		account.addCustomer(null);
		assertEquals(1,account.getOwningCustomers().size());
	}
	/*
	 * test setting list of customers owning the an account (empty list, null list,...)
	 */
	@Test
	public void testSetOwningCustomers() {
		Customer customer1 = new Customer("user","test","customer1","cust1");
		Account account = new Account("account1", "account2",null);
		//add empty list
		List <Customer> custs = new ArrayList<Customer>();
		account.setOwningCustomers(custs);
		assertEquals(0,account.getOwningCustomers().size());

		// add list with one cust
		custs = new ArrayList<Customer>();
		custs.add(customer1);
		account.setOwningCustomers(custs);
		assertEquals(1,account.getOwningCustomers().size());
		
		//setting a null list does not do anything 
		account.setOwningCustomers(null);
		assertEquals(1,account.getOwningCustomers().size());
		
		//adding null customer does not do anything
		account.addCustomer(null);
		assertEquals(1,account.getOwningCustomers().size());
		
		
	}
}
