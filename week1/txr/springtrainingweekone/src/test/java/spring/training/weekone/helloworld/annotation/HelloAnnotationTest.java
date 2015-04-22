package spring.training.weekone.helloworld.annotation;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.training.weekone.helloworld.HelloAnythingConstruct;

public class HelloAnnotationTest {
	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringHelloAppAnnotation.xml");
		assertNotNull(context);
		HelloAnnotation hello = (HelloAnnotation) context.getBean("helloAnnotation"); 
		assertNotNull(hello);
		log.clear();
		hello.sayTheThing();
		assertEquals("Hello Stranger\n", log.getLog());
	}

}
