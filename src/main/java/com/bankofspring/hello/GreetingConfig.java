package com.bankofspring.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreetingConfig {

	@Bean
	public GreetingService helloWorld() {
		return new HelloWorldImpl();
	}
	
	@Bean
	public GreetingService helloConstructor() {
		return new HelloWorldImpl("Hello, All! This greeting was set by the constructor.");
	}
	
	@Bean
	public GreetingService helloProperty() {
		HelloWorldImpl impl = new HelloWorldImpl("Hello, All! This greeting was set by the constructor.");
		impl.setGreeting("Hello, Everybody! This greeting was set by the setGreeting setter.");
		return impl;
	}
	
}
