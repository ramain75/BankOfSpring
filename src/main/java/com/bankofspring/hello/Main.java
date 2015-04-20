package com.bankofspring.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ram
 */
public class Main {

	/**
	 * @param args for the program
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("greeting.xml");
		
		GreetingService helloWorld = (GreetingService) context.getBean("helloWorld");
		System.out.println(helloWorld.greet());
		
		GreetingService helloConstructor = (GreetingService) context.getBean("helloConstructor");
		System.out.println(helloConstructor.greet());

		GreetingService helloProperty = (GreetingService) context.getBean("helloProperty");
		System.out.println(helloProperty.greet());
	}

}
