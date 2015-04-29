package org.bankofspring;

import static org.junit.Assert.*;

import org.bankofspring.model.Account;
import org.bankofspring.model.BankOperationType;
import org.bankofspring.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:BankOfSpringApp.xml")
public class BankOperationvalidatorTest {
	@Autowired
	private BankOperationValidatorImpl validator;
	
	@Mock
	private User user;
	@Mock
	private Account toAccount;
	@Mock
	private Account fromAccount;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this); //to make sure mock annotations work as using springrunner
	}
	 
	@Test(expected=RuntimeException.class)
	public void testUserNull() {
		validator.validateUser(null);
	}
	@Test
	public void testUserValid() {
		validator.validateUser(new User());
	}
	@Test(expected=RuntimeException.class)
	public void testValidateAmountNull() {
		validator.validateAmount(-1);
	}
	@Test
	public void testValidateAmounts() {
		validator.validateAmount(0);
		validator.validateAmount(100L);
	}
	@Test
	public void testCredit() {
		long amount = 100L;
		when(toAccount.getAccountAmount()).thenReturn(0L);
		assertTrue(validator.validateCredit(toAccount, amount));
		verify(toAccount,times(1)).getAccountAmount();
	}
	@Test
	public void testCreditOverBalance() {
		long amount = Account.MAX_BALANCE;
		when(toAccount.getAccountAmount()).thenReturn(1L);
		assertFalse(validator.validateCredit(toAccount, amount));
		verify(toAccount,times(1)).getAccountAmount();
	}
	@Test
	public void testDebit() {
		long amount = 100L;
		when(fromAccount.getAccountAmount()).thenReturn(150L);
		assertTrue(validator.validateDebit(fromAccount, amount));
		verify(fromAccount,times(1)).getAccountAmount();
	}
	@Test
	public void testDebitNotEnough() {
		long amount = 100L;
		when(fromAccount.getAccountAmount()).thenReturn(0L);
		assertFalse(validator.validateDebit(fromAccount, amount));
		verify(fromAccount,times(1)).getAccountAmount();
	}
	@Test
	public void testValidateOperationCredit() {
		long amount = 100L;
		when(toAccount.getAccountAmount()).thenReturn(0L);
		assertTrue(validator.validateOperation(user, fromAccount, toAccount, amount, BankOperationType.CREDIT));
		verify(toAccount,times(1)).getAccountAmount();
		
	}
	@Test
	public void testValidateOperationDebit() {
		long amount = 100L;
		when(fromAccount.getAccountAmount()).thenReturn(150L);
		assertTrue(validator.validateOperation(user, fromAccount, toAccount, amount, BankOperationType.DEBIT));
		verify(fromAccount,times(1)).getAccountAmount();
		
	}
	@Test
	public void testValidateOperationNullt() {
		long amount = 100L;
		when(fromAccount.getAccountAmount()).thenReturn(150L);
		assertFalse(validator.validateOperation(user, fromAccount, toAccount, amount, null));
		verify(fromAccount,never()).getAccountAmount();
		verify(toAccount,never()).getAccountAmount();
		
	}

}
