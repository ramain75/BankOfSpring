package org.bankofspring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Integration test for the BankService. Tests
 * <code>boolean debit(User loggedInUser, Account fromAccount, long amount)</code>
 * 
 * @author ram
 *
 */
public class BankServiceDebitAccountThreeParamsTest extends
		AbstractBankServiceTest {

	@Test
	public void testDebitAccount() {
		assertEquals(
				"Trying to credit £10.00 to an account with balance £0 should succeed",
				true, bankService.credit(janice, account10000001, 1000L));
		assertEquals("Account balance should be £10.00 after credit", 1000L,
				account10000001.getAccountBalance());
		assertEquals(
				"Trying to debit £4.00 to an account with balance £10.00 should succeed",
				true, bankService.debit(janice, account10000001, 400L));
		assertEquals("Account balance should be £6.00 after debit", 600L,
				account10000001.getAccountBalance());

	}

	@Test(expected = RuntimeException.class)
	public void testDebitAccountWithNoUser() {
		try {
			bankService.debit(null, account10000001, 1000L);
		} catch (RuntimeException re) {
			assertEquals("RuntimeException message not as expected",
					"Transactions can only be performed by an authorised User",
					re.getMessage());
			throw re;
		}
	}

	@Test(expected = RuntimeException.class)
	public void testDebitAccountWithZeroAmount() {
		try {
			bankService.debit(janice, account10000001, 0L);
		} catch (RuntimeException re) {
			assertEquals("RuntimeException message not as expected",
					"Amount is invalid for a debit transaction",
					re.getMessage());
			throw re;
		}
	}

	@Test(expected = RuntimeException.class)
	public void testDebitAccountWithNoAccount() {
		try {
			bankService.debit(janice, null, 1000L);
		} catch (RuntimeException re) {
			assertEquals("RuntimeException message not as expected",
					"fromAccount cannot be null for a debit transaction",
					re.getMessage());
			throw re;
		}
	}

	@Test
	public void testDebitAccountWithLargeAmount() {
		assertEquals(
				"Trying to credit £10.00 to an account with balance £0.00 should succeed",
				true, bankService.credit(janice, account10000001, 1000L));
		assertEquals("Account balance should be £10.00 after credit", 1000L,
				account10000001.getAccountBalance());

		assertEquals(
				"Trying to debit £10.01 from an account with balance £10.00 should fail",
				false, bankService.debit(janice, account10000001, 1001L));
		assertEquals(
				"Account balance should still be £10.00 after failed debit",
				1000L, account10000001.getAccountBalance());

	}

	@Test
	public void testDebitAccountWithBalance() {
		assertEquals(
				"Trying to credit £10.00 to an account with balance £0 should succeed",
				true, bankService.credit(janice, account10000001, 1000L));
		assertEquals("Account balance should be £10.00 after credit", 1000L,
				account10000001.getAccountBalance());

		assertEquals(
				"Trying to debit £10.00 from an account with balance £10.00 should succeed",
				false, bankService.debit(janice, account10000001, 1000L));
		assertEquals("Account balance should be £10.00 after failed debit",
				1000L, account10000001.getAccountBalance());

	}

	@Test
	public void testDebitAccountWithJustLessThanBalance() {
		assertEquals(
				"Trying to credit £10.00 to an account with balance £0 should succeed",
				true, bankService.credit(janice, account10000001, 1000L));
		assertEquals("Account balance should be £10.00 after credit", 1000L,
				account10000001.getAccountBalance());

		assertEquals(
				"Trying to debit £9.99 from an account with balance £10.00 should succeed",
				true, bankService.debit(janice, account10000001, 999L));
		assertEquals("Account balance should be £0.01 after debit", 1L,
				account10000001.getAccountBalance());

	}

}
