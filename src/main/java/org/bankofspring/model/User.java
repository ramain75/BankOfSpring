package org.bankofspring.model;

import java.io.Serializable;

/**
 * Model of a user of the system.
 * 
 * @author ram
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 3920264004327932544L;

	/**
	 * The username
	 */
	private String username;

	/**
	 * We'll pretend for a moment that storing a password in plain text is not evil!
	 * Don't do this IRL.
	 */
	private String password;

	/**
	 * Constructor.
	 * 
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
