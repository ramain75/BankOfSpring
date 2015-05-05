/**
 * 
 */
package org.bankofspring.exception;

/**
 * @author sean
 *
 */
public class BankAppException extends RuntimeException {

	public BankAppException() {
	}

	public BankAppException(String message, Throwable cause) {
		super(message, cause);
	}

	public BankAppException(String message) {
		super(message);
	}

	public BankAppException(Throwable cause) {
		super(cause);
	}

}
