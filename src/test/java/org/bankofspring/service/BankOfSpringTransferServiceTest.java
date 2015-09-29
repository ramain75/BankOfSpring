package org.bankofspring.service;

import static org.junit.Assert.*;

import java.util.List;

import org.bankofspring.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:BankOfSpring-ds-test.xml", "classpath:BankOfSpring.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BankOfSpringTransferServiceTest {
	@Autowired
	@Qualifier("bankTransferService")
	BankOfSpringTransferService service;
	@Test
	public void getAccountsForCustomerShouldReturnListOfAccounts () {
		List<Account> accounts = service.getListOfAccountsForCustomer(1);
		assertNotNull (accounts);
		assertEquals(accounts.size(), 3);
	}

}
