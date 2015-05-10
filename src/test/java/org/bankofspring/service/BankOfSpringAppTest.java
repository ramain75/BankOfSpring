package org.bankofspring.service;

import static org.junit.Assert.*;

import org.bankofspring.model.Account;
import org.bankofspring.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/*
 * This test class tests it all together
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:BankOfSpringApp.xml","classpath:BankOfSpringAppData.xml"})
//needed otherwise account balance are not reset between tests and this causes failures
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BankOfSpringAppTest {
	
	@Autowired
	BankOfSpringService service;
	
	@Autowired
	@Qualifier("customer1")
	Customer customer1;
	
	@Autowired
	@Qualifier("customer2")
	Customer customer2;

	/*
	 * credit an account with fromAccount unknown: a cash credit
	 * ensure balance of the toAccount is increased by the credited account
	 */
	@Test
	public void testCashCredit() {
		Account account1 = customer1.getAccount("account1");
		long balance = account1.getAccountBalance();
		assertTrue(service.credit(customer1, account1, 100L));
		assertEquals(balance + 100L, account1.getAccountBalance());
		
	}
	/**
	 * credit an account with a known fromAccount: a credit
	 * ensure balance of the toAccount is increased by the credited account
	 * the fromAccount will NOT be debited, this will be done in a further week
	 */
	@Test
	public void testCreditToOtherAccount() {
		Account account1 = customer1.getAccount("account1");
		Account account3 = customer2.getAccount("account3");
		long balanceAccount1 = account1.getAccountBalance();
		long balanceAccount3 = account3.getAccountBalance();
		assertTrue(service.credit(customer1, account1, account3,100L));
		assertEquals(balanceAccount1 + 100L, account1.getAccountBalance());
		assertEquals(balanceAccount3 , account3.getAccountBalance());
	}
	/**
	 * credit with negative amount which should thrown a runtime exception to demonstrate
	 * that validator is called. 
	 * @see org.bankofspring.validator.BankOperationValidatorTest 
	 */
	@Test(expected=RuntimeException.class)
	public void testCreditInvalidAmountFails() {
		Account account1 = customer1.getAccount("account1");
		Account account3 = customer2.getAccount("account3");
		assertFalse(service.credit(customer1, account1, -100)); 
	}
	
	
	/**
	 * credit to another account with max long value which should not work
	 */
	@Test
	public void testCreditToOtherAccountFailsDueToMaxValue() {
		Account account1 = customer1.getAccount("account1");
		Account account3 = customer2.getAccount("account3");
		long balanceAccount1 = account1.getAccountBalance();
		long balanceAccount3 = account3.getAccountBalance();
		assertFalse(service.credit(customer1, account1, Long.MAX_VALUE));
		assertEquals(balanceAccount1, account1.getAccountBalance());
		assertEquals(balanceAccount3 , account3.getAccountBalance());
	}
	/**
	 * debit from an account (no to account)
	 */
	@Test
	public void testCashDebit() {
		Account account1 = customer1.getAccount("account2");
		//set the balance to 200 to ensure there is enough fund for the debit
		account1.setAccountBalance(200L);
		assertEquals(200L,account1.getAccountBalance());
		long balance = account1.getAccountBalance();
		assertTrue(service.debit(customer1, account1, 100L));
		assertEquals(balance - 100L, account1.getAccountBalance());
		
	}
	/**
	 * Test debit an account with destination account
	 * Note that destination account will not be credited with amount at this stage (for a further week)
	 * 
	 */
	@Test
	public void testDebitWithToAccount() {
		Account account1 = customer1.getAccount("account1");
		// ensure enough funds on account 1
		account1.setAccountBalance(200L);
		Account account3 = customer2.getAccount("account3");
		long balanceAccount1 = account1.getAccountBalance();
		long balanceAccount3 = account3.getAccountBalance();
		assertTrue(service.debit(customer1, account1,account3,100L));
		assertEquals(balanceAccount1 - 100L, account1.getAccountBalance());
		assertEquals(balanceAccount3 , account3.getAccountBalance());
		
	}
	/**
	 * ensure debit fails, returns false is account has not enough money
	 */
	@Test
	public void testDebitWithNotEnoughFunds() {
		
		Account account1 = customer1.getAccount("account1");
		Account account3 = customer2.getAccount("account3");
		long balanceAccount1 = account1.getAccountBalance();
		assertEquals("unexpected value for balance account1", 0L,balanceAccount1);
		long balanceAccount3 = account3.getAccountBalance();
		assertFalse(service.debit(customer1, account1,account3,100L));
		assertEquals(0L, account1.getAccountBalance());
		assertEquals(balanceAccount3 , account3.getAccountBalance());
		
	}
	
	
	
}
