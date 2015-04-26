/**
 * 
 */
package org.bankofspring.exception;

/**
 * @author sean
 *
 */
public class AccountNotFoundException extends BankAppException {

	public AccountNotFoundException() {
	}

	public AccountNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountNotFoundException(String message) {
		super(message);
	}

	public AccountNotFoundException(Throwable cause) {
		super(cause);
	}
}
