package com.bankofspring.service;

import static org.junit.Assert.*;

import org.bankofspring.service.BankOfSpringService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BankOfSpringAppWiringTest {

	private ApplicationContext context;
	@Before
	public void setup() {
		context = new ClassPathXmlApplicationContext("BankOfSpringApp.xml");
		
	}
	@Test
	public void testBankOfSpringContext() {
		BankOfSpringService service = context.getBean("bankService",BankOfSpringService.class);
		assertNotNull(service);
		assertNotNull(service.getValidator());
	}

}
