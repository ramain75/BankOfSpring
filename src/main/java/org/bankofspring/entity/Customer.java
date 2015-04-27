package org.bankofspring.entity;

/**
 * Customer object for the Bank of Spring.
 * 
 * @author axp
 */
public class Customer extends User {
	private int customerId;
	private String name;
	
	public int getCustomerId() {
		return this.customerId;
	}
	
	public void setCustomerId( int customerId ) {
		this.customerId = customerId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
	
}
