package org.bankofspring.model;

import java.io.Serializable;

public class Customer extends User implements Serializable {

	private static final long serialVersionUID = -2307694962274336710L;

	private long customerId;
	
	private String name;

	/**
	 * Constructor
	 * @param username
	 * @param password
	 * @param customerId
	 * @param name
	 */
	public Customer(String username, String password, long customerId,
			String name) {
		super(username, password);
		this.customerId = customerId;
		this.name = name;
	}

	/**
	 * @return the customerId
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
