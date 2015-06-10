package org.bankofspring.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.bankofspring.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:BankOfSpring.xml", "classpath:BankOfSpring-ds-test.xml", "classpath:BankOfSpring-dao.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountDAOTest {

	@Autowired
	private AccountDAO dao;
	
	@Test
	public void testGetById() throws Exception {
		Account account = dao.getByNumber("account3");
		assertNotNull("Expected account3 to be loaded", account);
		assertEquals("Account Number not as expected", "account3", account.getAccountNumber());
		assertEquals("Account Description not as expected", "account3description", account.getAccountDescription());
		assertEquals("Account Balance not as expected", 100L, account.getAccountBalance());
		assertNotNull("Owning customers not expected to be null", account.getOwningCustomers());
		assertEquals("Owning customers not expected to be loaded at this point", 0, account.getOwningCustomers().size());
		assertNotNull("Transactions not expected to be null", account.getTransactions());
		assertEquals("Transactions not expected to be loaded at this point", 0, account.getTransactions().size());
	}
}
