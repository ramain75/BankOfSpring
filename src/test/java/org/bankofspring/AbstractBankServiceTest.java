package org.bankofspring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.bankofspring.model.Account;
import org.bankofspring.model.Customer;
import org.bankofspring.model.User;
import org.bankofspring.service.BankService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Abstract integration test for the BankService.
 * 
 * Note that I've had to add the @DirtiesContext because the classes in the
 * Spring Context are changed if the debit or credit transactions are
 * successful.
 * 
 * @author ram
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:BankOfSpringApp.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractBankServiceTest {

	@Autowired
	protected BankService bankService;

	@Autowired
	protected User janice;

	@Autowired
	protected Customer tonyThompson;

	@Autowired
	@Qualifier("account10000001")
	protected Account account10000001;

	@Autowired
	@Qualifier("account12345678")
	protected Account account12345678;

	@Before
	public void testAutowiringWentOK() {
		assertNotNull(bankService);
		assertNotNull(janice);
		assertNotNull(tonyThompson);
		assertNotNull(account10000001);
		assertNotNull(account12345678);

		assertEquals("Expected autowired BankService to be a BankServiceImpl",
				"org.bankofspring.service.BankServiceImpl", bankService
						.getClass().getName());
		assertEquals("Expected janice to be the User", "janice",
				janice.getUsername());
		assertEquals("Expected tonyThompson to be the Customer",
				"tonyThompson", tonyThompson.getUsername());
		assertEquals("Current account number should be 10000001", 10000001L,
				account10000001.getAccountNumber());
		assertEquals("Initial current account balance should be £0.00", 0L,
				account10000001.getAccountBalance());
		assertEquals("Savings account number should be 12345678", 12345678L,
				account12345678.getAccountNumber());
		assertEquals("Initial savings account balance should be £0.00", 0L,
				account12345678.getAccountBalance());
		assertEquals(
				"Expected Tony Thompson to be the Customer for Account 10000001",
				tonyThompson, account10000001.getCustomer());
		assertEquals(
				"Expected Tony Thompson to be the Customer for Account 12345678",
				tonyThompson, account12345678.getCustomer());
	}

}
