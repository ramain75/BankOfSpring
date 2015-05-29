package org.bankofspring.audit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.bankofspring.model.Account;
import org.bankofspring.model.Customer;
import org.bankofspring.service.BankOfSpringService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:BankOfSpring.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AuditAnnotationTest {
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	@Qualifier("bankService")
	private BankOfSpringService service;
	
	private Customer getCustomer(String beanID) {
		Customer cust  = appContext.getBean(beanID,Customer.class);
		assertNotNull(cust);
		return cust;
	}
	
	@Test
	public void testDebitWithToAccount() {
		Customer customer1 = getCustomer("customer1");
		Customer customer2 = getCustomer("customer2");
		Account account1 = customer1.getAccount("account1");
		// ensure enough funds on account 1
		account1.setAccountBalance(200L);
		Account account3 = customer2.getAccount("account3");
		long balanceAccount1 = account1.getAccountBalance();
		long balanceAccount3 = account3.getAccountBalance();
		assertTrue(service.debit(customer1, account1,account3,100L));
		assertEquals(balanceAccount1 - 100L, account1.getAccountBalance());
		assertEquals(balanceAccount3 , account3.getAccountBalance());
		
	}
}
