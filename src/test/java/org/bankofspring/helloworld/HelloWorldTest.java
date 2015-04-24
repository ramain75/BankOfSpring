package org.bankofspring.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Test Pojo, Property and Constructor HelloWorld beans.
 * 
 * @author txr, axp
 */
public class HelloWorldTest {
	private static ApplicationContext context;
	
	private HelloWorld helloWorld; 
	
	/**
	 * Loading spring application context. ClasspathXmlApplicationContext requires obviously the location of the
	 * xml to be known on the classpath
	 */
	@BeforeClass
	public static void setupClass() {
		context = new ClassPathXmlApplicationContext( "SpringHelloApp.xml" );
	}
	
	@Test
	public void testHelloWorldPojo() {
		assertNotNull( "Application context was null", context );
		
		helloWorld = (HelloWorld) context.getBean( "HelloPojo" ); // the id that we gave in the xml file
		assertNotNull( "HelloWorld bean was null", helloWorld );
		assertEquals( "HelloWorld bean had unexpected class", HelloWorldPOJO.class, helloWorld.getClass() );
		
		assertEquals( "Unexpected greeting", "Hello World", helloWorld.sayHello() );
	}
	
	@Test
	public void testHelloWorldProperty() {
		assertNotNull( "Application context was null", context );
		
		helloWorld = (HelloWorld) context.getBean( "HelloProp" ); // the id that we gave in the xml file
		assertNotNull( "HelloWorld bean was null", helloWorld );
		assertEquals( "HelloWorld bean had unexpected class", HelloWorldProperty.class, helloWorld.getClass() );
		
		assertEquals( "Unexpected greeting", "Hello Property", helloWorld.sayHello() );
	}
	
	@Test
	public void testHelloWorldConstructor() {
		assertNotNull( "Application context was null", context );
		
		helloWorld = (HelloWorld) context.getBean( "HelloConstruct" ); // the id that we gave in the xml file
		assertNotNull( "HelloWorld bean was null", helloWorld );
		assertEquals( "HelloWorld bean had unexpected class", HelloWorldConstructor.class, helloWorld.getClass() );
		
		assertEquals( "Unexpected greeting", "Hello You!", helloWorld.sayHello() );
	}
}
