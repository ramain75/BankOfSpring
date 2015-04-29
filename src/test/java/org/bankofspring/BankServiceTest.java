package org.bankofspring;

import static org.junit.Assert.*;

import org.bankofspring.model.Account;
import org.bankofspring.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:BankOfSpringApp.xml")

public class BankServiceTest {

	@Autowired
	private BankService service;
	
	@Autowired
	@Qualifier("customer1")
	private Customer customer1;
	
	@Autowired
	@Qualifier("account1")
	private Account account1;
	
	@Autowired
	@Qualifier("account2")
	private Account account2;
	
	
	@Test
	@DirtiesContext //force reload of context (otherwise amount for account changes)
	public void testCredit() {
		long amount = 166L;
		assertEquals(100L,account1.getAccountAmount());
		assertEquals(account1, customer1.getAccounts().get(0));
		assertTrue(service.credit(customer1, account1, amount));
		assertEquals(266L,account1.getAccountAmount());
	}
	@Test
	@DirtiesContext 
	public void testCreditWithBothAccount() {
		long amount = 166L;
		assertEquals(100L,account1.getAccountAmount());
		assertEquals(account1, customer1.getAccounts().get(0));
		assertTrue(service.credit(customer1,account2, account1, amount));
		assertEquals(266L,account1.getAccountAmount());
	}
	@Test
	@DirtiesContext 
	public void testCreditOver() {
		long amount = Account.MAX_BALANCE;
		assertEquals(100L,account1.getAccountAmount());
		assertEquals(account1, customer1.getAccounts().get(0));
		assertFalse(service.credit(customer1, account1, amount));
		assertEquals(100L,account1.getAccountAmount());
	}
	@Test
	@DirtiesContext 
	public void testCreditNegative() {
		long amount = -1L;
		boolean thrown = false;
		assertEquals(100L,account1.getAccountAmount());
		assertEquals(account1, customer1.getAccounts().get(0));
		try{
			assertFalse(service.credit(customer1, account1, amount));
		} catch (RuntimeException rte) {
			thrown = true;
		}
		assertTrue(thrown);
		assertEquals(100L,account1.getAccountAmount());
		assertEquals(50L,account2.getAccountAmount());
	}
	@Test
	@DirtiesContext 
	public void testDebit() {
		long amount = 66L;
		//check accounts before operation
		assertEquals(100L,account1.getAccountAmount());
		assertEquals(50L,account2.getAccountAmount());
		
		assertEquals(account1, customer1.getAccounts().get(0));
		assertTrue(service.debit(customer1, account1, amount));
		//check accounts after operation
		assertEquals(34L,account1.getAccountAmount());
		assertEquals(50L,account2.getAccountAmount()); // untouched
	}
	@Test
	@DirtiesContext 
	public void testDebitWithBothAccounts() {
		long amount = 66L;
		//check accounts before operation
		assertEquals(100L,account1.getAccountAmount());
		assertEquals(50L,account2.getAccountAmount());
		
		assertEquals(account1, customer1.getAccounts().get(0));
		assertTrue(service.debit(customer1, account1, account2, amount));
		//check accounts after operation
		assertEquals(34L,account1.getAccountAmount());
		assertEquals(50L,account2.getAccountAmount()); // untouched
	}
	@Test
	@DirtiesContext 
	public void testDebitNegative() {
		long amount = -1L;
		boolean thrown = false;
		assertEquals(100L,account1.getAccountAmount());
		assertEquals(account1, customer1.getAccounts().get(0));
		try{
			assertFalse(service.debit(customer1, account1, amount));
		} catch (RuntimeException rte) {
			thrown = true;
		}
		assertTrue(thrown);
		assertEquals(100L,account1.getAccountAmount());
		assertEquals(50L,account2.getAccountAmount());
	}
	@Test
	@DirtiesContext 
	public void testDebitNotEnough() {
		long amount = 300L;
		assertEquals(100L,account1.getAccountAmount());
		assertEquals(account1, customer1.getAccounts().get(0));
		assertFalse(service.debit(customer1, account1, amount));
		assertEquals(100L,account1.getAccountAmount());
		assertEquals(50L,account2.getAccountAmount());
	}

}
