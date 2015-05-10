package org.bankofspring.service;

import static org.junit.Assert.*;

import org.bankofspring.service.BankOfSpringService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/*
 * test that BankOfSpringApp.xml wires up beans (bankService and validator) as expected
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:BankOfSpringApp.xml")
public class BankOfSpringAppWiringTest {
	@Autowired
	BankOfSpringService service;
	@Test
	public void testBankOfSpringContext() {
		assertNotNull(service);
		assertNotNull(service.getValidator());
	}

}
