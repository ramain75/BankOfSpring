package org.bankofspring.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
/*
 * test that BankOfSpringApp.xml wires up beans (bankService and validator) as expected
 */
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:BankOfSpring.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BankOfSpringAppWiringTest {

	@Autowired (required = false)
	@Qualifier("bankService")
	BankOfSpringService service;
	
	@Test
	public void testBankOfSpringContext() {
		assertNotNull(service);
	}

}
