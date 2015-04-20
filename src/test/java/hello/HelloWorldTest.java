package hello;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bankofspring.hello.GreetingService;

public class HelloWorldTest {

	@Test
	public void testNoArguments() {
		ApplicationContext context = new ClassPathXmlApplicationContext("greeting.xml");
		
		GreetingService helloWorld = (GreetingService) context.getBean("helloWorld");
		
		assertEquals("Default message not as expected", "Hello, world!", helloWorld.greet());
	}
	
	@Test
	public void testConstructor() {
		ApplicationContext context = new ClassPathXmlApplicationContext("greeting.xml");
		
		GreetingService helloWorld = (GreetingService) context.getBean("helloConstructor");
		
		assertEquals("Default message not as expected", "Hello, All! This greeting was set by the constructor.", helloWorld.greet());
	}

	@Test
	public void testProperty() {
		ApplicationContext context = new ClassPathXmlApplicationContext("greeting.xml");
		
		GreetingService helloWorld = (GreetingService) context.getBean("helloProperty");
		
		assertEquals("Default message not as expected", "Hello, Everybody! This greeting was set by the setGreeting setter.", helloWorld.greet());
	}
}
