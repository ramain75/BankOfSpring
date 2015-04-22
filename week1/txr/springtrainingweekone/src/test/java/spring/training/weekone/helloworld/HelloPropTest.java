package spring.training.weekone.helloworld;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloPropTest {

	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();
	@Test
	public void testHelloAnythingConstruct() {
		// loading spring application context. ClasspathXmlApplicationContext requires obviously the location of the xml to be known
		// on the classpath
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringHelloApp.xml");
		assertNotNull(context);
		HelloProp hello = (HelloProp) context.getBean("HelloProp"); //the id that we gave in the xml file
		assertNotNull(hello);
		log.clear();
		hello.sayHelloProp();
		assertEquals("Hello Property\n", log.getLog());
	}

}
