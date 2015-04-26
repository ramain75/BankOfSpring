/**
 * 
 */
package org.bankofspring.entities;

import java.util.List;

/**
 * @author sean
 *
 */
public class Customer extends User {

	private long customerId;
	private String name;
	private List<Account> accounts;

	/**
	 * @return the customerId
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts
	 *            the accounts to set
	 */
	public void setAccounts(List<Account> accounts) {
		// make sure we set the reference and avoid circular dependencies
		for (Account account : accounts) {
			account.setCustomer(this);
		}
		this.accounts = accounts;
	}

}
