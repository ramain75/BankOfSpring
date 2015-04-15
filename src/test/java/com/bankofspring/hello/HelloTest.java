package com.bankofspring.hello;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author slc
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:hello/hello-applicationContext.xml")
public class HelloTest {

	@Autowired
	private String article;
	
	@Autowired
	@Qualifier("annotationSimpleHelloImpl")
	private HelloService annotationHelloService;
	
	@Autowired
	@Qualifier("xmlSimpleHelloImpl")
	private HelloService xmlHelloService;
	
	@Test
	public void testAnnotationHelloService() {
		assertThat( annotationHelloService.sayHello(), containsString( article ) );
	}
	
	@Test
	public void testXMLHelloService() {
		assertThat( xmlHelloService.sayHello(), containsString( article ) );
	}
	
	@Test
	public void testBothServices() {
		assertThat( xmlHelloService.sayHello(), equalTo( annotationHelloService.sayHello() ) );
	}
}
