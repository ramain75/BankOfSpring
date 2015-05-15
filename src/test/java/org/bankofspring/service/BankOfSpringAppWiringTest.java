package org.bankofspring.service;

import static org.junit.Assert.*;
import org.bankofspring.service.BankOfSpringService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/*
 * test that BankOfSpringApp.xml wires up beans (bankService and validator) as expected
 */
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:BankOfSpring.xml")
public class BankOfSpringAppWiringTest {

	@Autowired
	@Qualifier("bankService")
	BankOfSpringService service;
	
	@Test
	public void testBankOfSpringContext() {
		assertNotNull(service);
		assertNotNull(service.getValidator());
	}

}
