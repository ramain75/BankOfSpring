package org.bankofspring.model;

import org.junit.Test;

public class AccountTest {

	/*
	 * applying a null accountTransaction does nothing
	 */
	@Test
	public void testApplyNullTransaction() {
		Account account = new Account();
		account.setAccountNumber( "account1" );
		account.setAccountDescription( "account2" );
	}
	/*
	 * test adding customer to an account
	 */
	@Test
	public void testAddCustomer() {
		Customer customer1 = new Customer();
		customer1.setId(1);
		customer1.setName("cust1");
		Account account = new Account();
		account.setAccountNumber( "account1" );
		account.setAccountDescription( "account2" );
	}
	/*
	 * test setting list of customers owning the an account (empty list, null list,...)
	 */
	@Test
	public void testSetOwningCustomers() {
		Customer customer1 = new Customer();
		customer1.setId(1);
		customer1.setName("cust1");
		Account account = new Account();
		account.setAccountNumber( "account1" );
		account.setAccountDescription( "account2" );
	}
}
