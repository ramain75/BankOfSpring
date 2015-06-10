
package org.bankofspring.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations={"classpath:BankOfSpring.xml","classpath:BankOfSpring-ds-test.xml"})
public class BankOfSpringModelWiringTest {

	@Autowired
	Customer customer1;

	@Autowired
	Customer customer2;

	/**
	 * check that Customer2 wiring is as expected in terms of customer and accounts
	 */

	@Test
	public void testWiringCustomer1() {
		assertNotNull( customer1 );
		assertEquals( "user1", customer1.getUsername() );
		assertEquals( "test", customer1.getPassword() );
		assertEquals( "customerone", customer1.getName() );
		assertEquals( "customer1", customer1.getCustomerID() );
		assertNotNull( customer1.getAccounts() );
		Map<String, Account> accounts = customer1.getAccounts();
		assertEquals( 3, accounts.size() );

		Account account1 = accounts.get( "account1" );
		List<Customer> expectedCust = new ArrayList<Customer>();
		expectedCust.add( customer1 );
		checkAccount( account1, "account1", "account1description", 170000000L, 0L, expectedCust );

		Account account2 = accounts.get( "account2" );
		checkAccount( account2, "account2", "account2description", 100000000L, 0L, expectedCust );

	}

	private void checkAccount( Account account, String number, String description, long maxBalance, long balance, List<Customer> customers ) {
		assertEquals( number, account.getAccountNumber() );
		assertEquals( description, account.getAccountDescription() );
		assertEquals( maxBalance, account.getMaxBalanceAmount() );
		assertEquals( balance, account.getAccountBalance() );
		assertEquals( customers.size(), account.getOwningCustomers().size() );
	}

	/**
	 * check that Customer2 wiring is as expected in terms of customer and accounts
	 */
	@Test
	public void testWiringCustomer2() {
		assertNotNull( customer2 );
		assertEquals( "user2", customer2.getUsername() );
		assertEquals( "test", customer2.getPassword() );
		assertEquals( "customertwo", customer2.getName() );
		assertEquals( "customer2", customer2.getCustomerID() );
		assertNotNull( customer2.getAccounts() );
		Map<String, Account> accounts = customer2.getAccounts();
		assertEquals( 2, accounts.size() );

		Account account3 = accounts.get( "account3" );
		List<Customer> expectedCust = new ArrayList<Customer>();
		expectedCust.add( customer2 );

		checkAccount( account3, "account3", "account3description", 150000000L, 100L, expectedCust );
		expectedCust = new ArrayList<Customer>();
		expectedCust.add( customer2 );
		expectedCust.add( customer1 );

		Account account4 = accounts.get( "account4" );
		checkAccount( account4, "account4", "account4description", 100000000L, 150L, expectedCust );
	}

	/**
	 * test the accounttransaction created
	 */
	@Test
	public void testTransaction() {
		assertNotNull( customer2 );
		assertNotNull( customer2.getAccounts() );
		Map<String, Account> accounts = customer2.getAccounts();
		assertEquals( 2, accounts.size() );
		Account account4 = accounts.get( "account4" );
		assertEquals( "invalid size of transactions", 1, account4.getTransactions().size() );
		AccountTransaction txn = account4.getTransactions().get( 0 );
		assertNotNull( txn );
		assertNull( txn.getFromAccount() );
		assertEquals( account4, txn.getToAccount() );
		assertEquals( 100L, txn.getTransactionAmount() );
		assertNotNull( txn.getTransactionDate() );
		// test date spEL worked
		Date expectedDate = new GregorianCalendar( 2015, 1, 1 ).getTime();
		assertEquals( expectedDate, txn.getTransactionDate() );
	}

}
