package org.bankofspring.validator;

import static org.junit.Assert.*;

import org.bankofspring.model.Account;
import org.bankofspring.model.BankOperationType;
import org.bankofspring.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
/**
 * 
 * Check validator functions as expect
 * we don't really need spring at this level (we only use it to load the validator)
 * we mock(usimg Mockito) the various dependent objects as we really only want to assert Validator itself
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:BankOfSpring.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BankOperationValidatorTest {
	
	@Mock
	Customer customer;
	@Mock
	Account fromAccount;
	@Mock
	Account toAccount;
	
	@Autowired
	BankOperationValidator validator;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks( this );
	}
	
	/*
	 * if the user is null we expect a runtime exception
	 */
	@Test(expected=RuntimeException.class)
	public void testValidatorNullUser() {
		long amount = 100L;
		validator.validateOperation(null, fromAccount, toAccount, amount, BankOperationType.CREDIT);
	}
	/*
	 * if the amount is negative, we expect a runtime exception
	 */
	@Test(expected=RuntimeException.class)
	public void testValidatorNegativeAmount() {
		long amount = -1;
		validator.validateOperation(customer, fromAccount, toAccount, amount, BankOperationType.CREDIT);
	}
	/*
	 * if the operation is null, we expect a runtime exception
	 */
	@Test(expected=RuntimeException.class)
	public void testValidatorNullBankOperationType() {
		long amount = 100L;
		validator.validateOperation(customer, fromAccount, toAccount, amount, null);
	}
	/*
	 * we do a debit and there is enough funds
	 */
	@Test
	public void testValidatorDebitHappyPath() {
		long amount = 100L;
		when(fromAccount.getAccountBalance()).thenReturn(200L);
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
		assertTrue("debit failed unexpectedly", validator.validateOperation(customer, fromAccount, toAccount, amount, BankOperationType.DEBIT));
		verify(fromAccount).getAccountBalance();
	}
	// we do a debit and not enough funds
	@Test
	public void testValidatorDebitNotEnough() {
		long amount = 101L;
		when(fromAccount.getAccountBalance()).thenReturn(100L);
		assertFalse("debit failed unexpectedly", validator.validateOperation(customer, fromAccount, toAccount, amount, BankOperationType.DEBIT));
		verify(fromAccount).getAccountBalance();
	}
	@Test
	public void testValidatorCreditHappyPath() {
		long amount = 100L;
		when(toAccount.getMaxBalanceAmount()).thenReturn(Long.MAX_VALUE);
		assertTrue("credit failed unexpectedly", validator.validateOperation(customer, fromAccount, toAccount, amount, BankOperationType.CREDIT));
		verify(toAccount).getAccountBalance();
		verify(toAccount).getMaxBalanceAmount();
	}
	@Test
	public void testValidatorCreditOverMaxBalance() {
		long amount = Long.MAX_VALUE;
		when(toAccount.getMaxBalanceAmount()).thenReturn(Long.MAX_VALUE);
		assertTrue("credit failed unexpectedly", validator.validateOperation(customer, fromAccount, toAccount, amount, BankOperationType.CREDIT));
		verify(toAccount).getAccountBalance();
		verify(toAccount).getMaxBalanceAmount();
	}
	
	private BankOperationValidator getBankOperationValidator() {
		assertNotNull(validator);
		return validator;
	}

}
