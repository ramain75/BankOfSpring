package com.bankofspring.hello;

public class HelloWorldImpl implements GreetingService {

	private String greeting = "Hello, world!";
	
	public HelloWorldImpl() {
		super();
	}
	
	public HelloWorldImpl(String greeting) {
		this();
		this.greeting = greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public String greet() {
		return greeting;
	}

}
