package org.bankofspring;

import static org.junit.Assert.assertEquals;

import org.bankofspring.service.BankService;
import org.junit.Test;

/**
 * Integration test for the BankService. Tests
 * <code>credit(User loggedInUser, Account toAccount, Account fromAccount, long amount)</code>
 * 
 * @author ram
 *
 */
public class BankServiceCreditAccountFourParamsTest extends
		AbstractBankServiceTest {

	@Test
	public void testCreditAccount() {
		assertEquals(
				"Trying to credit £10.00 to an account with balance £0 should succeed",
				true, bankService.credit(janice, account10000001,
						account12345678, 1000L));
		assertEquals("Account balance should be £10.00 after credit", 1000L,
				account10000001.getAccountBalance());
	}

	@Test(expected = RuntimeException.class)
	public void testCreditAccountWithNoUser() {
		try {
			bankService.credit(null, account10000001, account12345678, 1000L);
		} catch (RuntimeException re) {
			assertEquals("RuntimeException message not as expected",
					"Transactions can only be performed by an authorised User",
					re.getMessage());
			assertEquals(
					"Account balance should still be £0.00 after failed credit",
					0L, account10000001.getAccountBalance());
			throw re;
		}
	}

	@Test(expected = RuntimeException.class)
	public void testCreditAccountWithZeroAmount() {
		try {
			bankService.credit(janice, account10000001, account12345678, 0L);
		} catch (RuntimeException re) {
			assertEquals("RuntimeException message not as expected",
					"Amount is invalid for a credit transaction",
					re.getMessage());
			assertEquals(
					"Account balance should still be £0.00 after failed credit",
					0L, account10000001.getAccountBalance());
			throw re;
		}
	}

	@Test(expected = RuntimeException.class)
	public void testCreditAccountWithNoAccount() {
		try {
			bankService.credit(janice, null, account12345678, 1000L);
		} catch (RuntimeException re) {
			assertEquals("RuntimeException message not as expected",
					"toAccount cannot be null for a credit transaction",
					re.getMessage());
			assertEquals(
					"Account balance should still be £0.00 after failed credit",
					0L, account10000001.getAccountBalance());
			throw re;
		}
	}

	@Test
	public void testCreditAccountWithLargeAmount() {
		assertEquals(
				"Trying to credit £1,000,000.01 to an account with balance £0 should fail",
				false, bankService.credit(janice, account10000001,
						account12345678, BankService.MAX_BALANCE + 1L));
		assertEquals(
				"Account balance should still be £0.00 after failed credit",
				0L, account10000001.getAccountBalance());

	}

	@Test
	public void testCreditAccountWithMaxBalance() {
		assertEquals(
				"Trying to credit £1,000,000.00 to an account with balance £0 should succeed",
				true, bankService.credit(janice, account10000001,
						account12345678, BankService.MAX_BALANCE));
		assertEquals("Account balance should be £1,000,000.00 after credit",
				100000000L, account10000001.getAccountBalance());

	}

	@Test
	public void testCreditAccountWithJustUnderMaxBalance() {
		assertEquals(
				"Trying to credit £999,999.99 to an account with balance £0 should succeed",
				true, bankService.credit(janice, account10000001,
						account12345678, BankService.MAX_BALANCE - 1L));
		assertEquals("Account balance should be £999,999.99 after credit",
				99999999L, account10000001.getAccountBalance());

	}

}
