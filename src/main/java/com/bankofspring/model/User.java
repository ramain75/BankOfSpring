package com.bankofspring.model;

/**
 * Class to represent a bank User
 * @author malcolmmurray
 *
 */
public class User {

	private String username;
	private String password;
	
	
	public User(String username, String password){
		setUsername(username);
		setPassword(password);
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
