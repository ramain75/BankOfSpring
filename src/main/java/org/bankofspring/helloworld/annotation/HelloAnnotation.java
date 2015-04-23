package org.bankofspring.helloworld.annotation;

import org.bankofspring.helloworld.HelloWorld;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * org.bankofspring.helloworld.HelloWorldProperty with annotation as 
 * a (very) gentle introduction to wiring via annotation
 * 
 * @author Thierry Roisin
 */
// marks this POJO as a spring component , candidate to be "discovered"
@Component
public class HelloAnnotation implements HelloWorld {
	@Value("Stranger")
	private String theThing;
	
	protected void setTheThing( String theThing ) {
		this.theThing = theThing ;
	}
	
	protected String getTheThing() {
		return this.theThing;
	}
	
	public String sayHello() {
		return "Hello " + this.theThing;
	}
}
