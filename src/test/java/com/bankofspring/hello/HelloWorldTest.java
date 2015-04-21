package com.bankofspring.hello;

import static org.junit.Assert.*;

import javax.naming.Context;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldTest {

	private ApplicationContext appContext;
	
	@Before
	public void setup(){
		appContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
	}
	
	@Test
	public void testHelloWorld() {
		
		HelloWorldBean helloWorld = (HelloWorldBean)appContext.getBean("helloBean");
		
		assertTrue("I am the Spring Framework - Yay!!!".equals(helloWorld.getName()));
		
		System.out.println("Hello "+helloWorld.getName());
		
		
	}

}
