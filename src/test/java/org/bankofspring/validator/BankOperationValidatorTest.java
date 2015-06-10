package org.bankofspring.validator;

import static org.junit.Assert.*;

import org.bankofspring.model.Account;
import org.bankofspring.model.BankOperationType;
import org.bankofspring.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
/**
 * 
 * Check validator functions as expect
 * we don't really need spring at this level (we only use it to load the validator)
 * we mock(usimg Mockito) the various dependent objects as we really only want to assert Validator itself
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BankOperationValidatorTest {
	
	@Mock
	Customer customer;
	@Mock
	Account fromAccount;
	@Mock
	Account toAccount;
	
	private ApplicationContext context;
	@Before
	public void setup() {
		context = new ClassPathXmlApplicationContext("BankOfSpring.xml", "BankOfSpring-emptydao.xml");
		
	}
	/*
	 * if the user is null we expect a runtime exception
	 */
	@Test(expected=RuntimeException.class)
	public void testValidatorNullUser() {
		long amount = 100L;
		BankOperationValidator validator = getBankOperationValidator();
		validator.validateOperation(null, fromAccount, toAccount, amount, BankOperationType.CREDIT);
	}
	/*
	 * if the amount is negative, we expect a runtime exception
	 */
	@Test(expected=RuntimeException.class)
	public void testValidatorNegativeAmount() {
		long amount = -1;
		BankOperationValidator validator = getBankOperationValidator();
		validator.validateOperation(customer, fromAccount, toAccount, amount, BankOperationType.CREDIT);
	}
	/*
	 * if the operation is null, we expect a runtime exception
	 */
	@Test(expected=RuntimeException.class)
	public void testValidatorNullBankOperationType() {
		long amount = 100L;
		BankOperationValidator validator = getBankOperationValidator();
		validator.validateOperation(customer, fromAccount, toAccount, amount, null);
	}
	/*
	 * we do a debit and there is enough funds
	 */
	@Test
	public void testValidatorDebitHappyPath() {
		long amount = 100L;
		when(fromAccount.getAccountBalance()).thenReturn(200L);
		BankOperationValidator validator = getBankOperationValidator();
		assertTrue("debit failed unexpectedly", validator.validateOperation(customer, fromAccount, toAccount, amount, BankOperationType.DEBIT));
		verify(fromAccount).getAccountBalance();
	}
	/*
	 * we do a debit and there is just enough funds: debit amount == account balance
	 */
	@Test
	public void testValidatorDebitJustEnough() {
		long amount = 100L;
		when(fromAccount.getAccountBalance()).thenReturn(100L);
		BankOperationValidator validator = getBankOperationValidator();
		assertTrue("debit failed unexpectedly", validator.validateOperation(customer, fromAccount, toAccount, amount, BankOperationType.DEBIT));
		verify(fromAccount).getAccountBalance();
	}
	// we do a debit and not enough funds
	@Test
	public void testValidatorDebitNotEnough() {
		long amount = 101L;
		when(fromAccount.getAccountBalance()).thenReturn(100L);
		BankOperationValidator validator = getBankOperationValidator();
		assertFalse("debit failed unexpectedly", validator.validateOperation(customer, fromAccount, toAccount, amount, BankOperationType.DEBIT));
		verify(fromAccount).getAccountBalance();
	}
	@Test
	public void testValidatorCreditHappyPath() {
		long amount = 100L;
		when(toAccount.getMaxBalanceAmount()).thenReturn(Long.MAX_VALUE);
		BankOperationValidator validator = getBankOperationValidator();
		assertTrue("credit failed unexpectedly", validator.validateOperation(customer, fromAccount, toAccount, amount, BankOperationType.CREDIT));
		verify(toAccount).getAccountBalance();
		verify(toAccount).getMaxBalanceAmount();
	}
	@Test
	public void testValidatorCreditOverMaxBalance() {
		long amount = Long.MAX_VALUE;
		when(toAccount.getMaxBalanceAmount()).thenReturn(Long.MAX_VALUE);
		BankOperationValidator validator = getBankOperationValidator();
		assertTrue("credit failed unexpectedly", validator.validateOperation(customer, fromAccount, toAccount, amount, BankOperationType.CREDIT));
		verify(toAccount).getAccountBalance();
		verify(toAccount).getMaxBalanceAmount();
	}
	
	private BankOperationValidator getBankOperationValidator() {
		BankOperationValidator validator = context.getBean("validator", BankOperationValidatorImpl.class);
		assertNotNull(validator);
		return validator;
	}

}
