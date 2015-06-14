package org.bankofspring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.dao.CustomerDAO;
import org.bankofspring.model.Account;
import org.bankofspring.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/*
 * This test class tests it all together
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:BankOfSpring-ds-test.xml", "classpath:BankOfSpring.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BankOfSpringAppTest {
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	@Qualifier("bankService")
	private BankOfSpringService service;
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	/*
	 * credit an account with fromAccount unknown: a cash credit
	 * ensure balance of the toAccount is increased by the credited account
	 */
	@Test
	public void testCashCredit() {
		Account account1 = getAccount("account1");
		long balance = account1.getAccountBalance();
		assertTrue(service.deposit(getCustomer(1), account1, 100L));
		assertEquals(balance + 100L, getAccount("account1").getAccountBalance());
		
	}
	/**
	 * credit an account with a known fromAccount: a credit
	 * ensure balance of the toAccount is increased by the credited account
	 * the fromAccount will NOT be debited, this will be done in a further week
	 */
	@Test
	public void testTransferToOtherAccount() {
		Account account1 = getAccount("account1");
		Account account3 = getAccount("account3");
		long balanceAccount1 = account1.getAccountBalance();
		long balanceAccount3 = account3.getAccountBalance();
		System.out.println(balanceAccount1);
		System.out.println(balanceAccount3);
		assertTrue(service.transfer(getCustomer(1), account3, account1,100L));
		assertEquals(balanceAccount1 + 100L, getAccount("account1").getAccountBalance());
		assertEquals(balanceAccount3 - 100L, getAccount("account3").getAccountBalance());
	}
	/**
	 * credit with negative amount which should thrown a runtime exception to demonstrate
	 * that validator is called. 
	 * @see org.bankofspring.validator.BankOperationValidatorTest 
	 */
	@Test(expected=RuntimeException.class)
	public void testCreditInvalidAmountFails() {
		Account account1 = getAccount("account1");
		assertFalse(service.deposit(getCustomer(1), account1, -100)); 
	}
	
	
	/**
	 * credit to another account with max long value which should not work
	 */
	@Test
	public void testTransferToOtherAccountFailsDueToMaxValue() {
		Account account1 = getAccount("account1");
		Account account3 = getAccount("account3");
		long balanceAccount1 = account1.getAccountBalance();
		long balanceAccount3 = account3.getAccountBalance();
		assertFalse(service.deposit( getCustomer(1), account1, Long.MAX_VALUE));
		assertEquals(balanceAccount1, account1.getAccountBalance());
		assertEquals(balanceAccount3 , account3.getAccountBalance());
	}
	/**
	 * debit from an account (no to account)
	 */
	@Test
	public void testCashDebit() {
		Account account3 = getAccount("account3");
		long balance = account3.getAccountBalance();
		assertTrue(service.withdraw(getCustomer(1), account3, 100L));
		assertEquals(balance - 100L, getAccount("account3").getAccountBalance());
		
	}

	/**
	 * ensure debit fails, returns false is account has not enough money
	 */
	@Test
	public void testTransferWithNotEnoughFunds() {
		Account account1 = getAccount("account1");
		Account account3 = getAccount("account3");
		long balanceAccount1 = account1.getAccountBalance();
		assertEquals("unexpected value for balance account1", 0L,balanceAccount1);
		long balanceAccount3 = account3.getAccountBalance();
		assertFalse(service.transfer(getCustomer(1), account1, account3, 100L));
		assertEquals(0L, account1.getAccountBalance());
		assertEquals(balanceAccount3 , account3.getAccountBalance());
		
	}
	
	private Customer getCustomer(Integer id) {
		Customer customer = customerDAO.getCustomerById(id);
		assertNotNull(customer);
		return customer;
	}
	
	private Account getAccount(String accountNumber) {
		Account account  = accountDAO.getAccountByName( accountNumber );
		assertNotNull(account);
		return account;
	}
	
}
