
package org.bankofspring;

import static org.junit.Assert.assertEquals;

import org.bankofspring.dao.BankDAO;
import org.bankofspring.entities.Account;
import org.bankofspring.service.BankService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( { "classpath:BankOfSpringApp.xml",
    "classpath:dao/BankOfSpringApp-dao.xml" } )
@DirtiesContext( classMode = ClassMode.AFTER_EACH_TEST_METHOD )
public class BankTest {

	@Autowired
	private BankService bankService;

	@Autowired
	private BankDAO bankDAO;

	private Account account0;
	private Account account1;
	private Account account2;
	private Account account3;

	@Before
	public void setup() {
		account0 = bankDAO.getAccount( 100000 );
		account1 = bankDAO.getAccount( 100001 );
		account2 = bankDAO.getAccount( 100002 );
		account3 = bankDAO.getAccount( 100003 );
	}

	private void assertAmount( long accountId, long expectedAmount ) {
		assertEquals( "The expected amount is incorrect for account: "
		    + accountId, expectedAmount, bankDAO.getAccount( accountId )
		    .getAccountAmount() );
	}

	/**
	 * Try to transfer money from one account to another. Ensure that this is correctly moved and that
	 * the transactions are correctly added.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTransfer() throws Exception {
		// check account amounts
		assertAmount( 100001, 100000 );
		assertAmount( 100002, 1 );

		// transfer
		bankService.transferMoney( 100001, 100002, 100000 );

		// check amounts after
		assertAmount( 100001, 0 );
		assertAmount( 100002, 100001 );

		// check account transactions
		assertEquals( "Check 1 debit transaction", account1
		    .getTransactions().size(), 1 );
		assertEquals( "Check 1 credit transaction", account2
		    .getTransactions().size(), 1 );

	}

	@Test
	public void testTransferNoMoney() throws Exception {
		assertAmount( 100000, 1000 );
		assertAmount( 100003, 100 );

		// transfer
		bankService.transferMoney( 100000, 100003, 1001 );

		// check amounts after
		assertAmount( 100000, 1000 );
		assertAmount( 100003, 100 );

		// check account transactions
		assertEquals( "Check 0 debit transaction", account0
		    .getTransactions().size(), 0 );
		assertEquals( "Check 0 credit transaction", account3
		    .getTransactions().size(), 0 );
	}

	@Test
	public void testTransferSameAccount() throws Exception {
		assertAmount( 100000, 1000 );

		// transfer
		bankService.transferMoney( 100000, 100000, 999 );

		// check amounts after
		assertAmount( 100000, 1000 );

		// check account transactions
		assertEquals( "Check 0 debit transaction", account0
		    .getTransactions().size(), 0 );
		assertEquals( "Check 0 credit transaction", account3
		    .getTransactions().size(), 0 );
	}
}
