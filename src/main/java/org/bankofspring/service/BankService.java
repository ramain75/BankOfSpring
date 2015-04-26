/**
 * 
 */
package org.bankofspring.service;

/**
 * @author sean
 *
 */
public interface BankService {
	
	/**
	 * Transfer money from one account to the other.
	 * 
	 * @param fromAccountId
	 *            the account ID to take money from
	 * @param toAccountId
	 *            the account ID to put money into
	 * @param amount
	 *            the amount to transfer
	 * @return whether the transfer was successful or not (dunno if this is
	 *         strictly required)
	 */
	boolean transferMoney(long fromAccountId, long toAccountId, long amount);
}
