package org.bankofspring.helloworld;

/**
 * A basic Hello World type POJO, we pass what to say via a property setter
 * 
 * @author txr, axp
 */
public class HelloWorldProperty implements HelloWorld {
	private String prop;

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}
	
	public String sayHello() {
		return "Hello " + prop;
	}
}
