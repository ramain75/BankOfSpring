package org.bankofspring.audit;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bankofspring.model.*;
import org.bankofspring.service.BankOfSpringService;
import org.bankofspring.service.BankOfSpringServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class AuditTest {
	private ApplicationContext context;
	@Before
	public void setup() {
		context = new ClassPathXmlApplicationContext("BankOfSpring2.xml");
		
	}
	/**
	 * check that Customer2 wiring is as expected in terms of customer and accounts
	 */
	
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
		BankOfSpringService service = context.getBean("bankService",BankOfSpringService.class);
		assertNotNull(service);
		service.debit(customer1, account1, 10L);
		service.debit(customer1, account1, account2, 10L);
	}
	
	private void checkAccount(Account account, String number, String description, long maxBalance, long balance, List<Customer> customers) {
		assertEquals(number,account.getAccountNumber());
		assertEquals(description,account.getAccountDescription());
		assertEquals(maxBalance,account.getMaxBalanceAmount());
		assertEquals(balance,account.getAccountBalance());
		assertEquals(customers.size(),account.getOwningCustomers().size());
	}
}