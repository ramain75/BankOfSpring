package org.bankofspring.helloworld;

/**
 * A basic Hello World type POJO, we pass what to say via the constructor
 * 
 * @author Thierry Roisin, Alex Panayotopoulos
 */
public class HelloWorldConstructor implements HelloWorld {
	private String whatToSay;
	
	public HelloWorldConstructor( String whatToSay ) {
		this.whatToSay = whatToSay;
	}
	
	public String sayHello() {
		return "Hello " + whatToSay;
	}
}
