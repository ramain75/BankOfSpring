package spring.training.weekone.helloworld;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class HelloWorlTest {
	// this is a convenient way of redirecting standard output , checkout http://stefanbirkner.github.io/system-rules/index.html
	
	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();
	@Test
	public void testHelloWorldPojo() {
		// loading spring application context. ClasspathXmlApplicationContext requires obviously the location of the xml to be known
		// on the classpath
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringHelloApp.xml");
		assertNotNull(context);
		HelloWorldPOJO helloWorld = (HelloWorldPOJO) context.getBean("HelloWorld"); //the id that we gave in the xml file
		assertNotNull(helloWorld);
		log.clear();
		helloWorld.sayHelloWorld();
		assertEquals("Hello World\n", log.getLog());
	}

}
