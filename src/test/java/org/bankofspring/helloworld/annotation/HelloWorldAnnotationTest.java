package org.bankofspring.helloworld.annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.bankofspring.helloworld.HelloWorld;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This test class uses annotation itself to set up the Spring context
 * 
 * @author Thierry Roisin, Sean Clark, Alex Panayotopoulos
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:SpringHelloAppAnnotation.xml")
public class HelloWorldAnnotationTest {
	@Autowired
	private HelloWorld helloWorld;
	
	@Test
	public void test() {
		assertNotNull( "HelloWorld bean was null", helloWorld );
		assertEquals( "HelloWorld bean had unexpected class", HelloWorldAnnotation.class, helloWorld.getClass() );
		assertEquals( "Unexpected greeting", "Hello Stranger", helloWorld.sayHello() );
	}
}
