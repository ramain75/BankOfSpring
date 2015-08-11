package org.bankofspring.service;

/**
 * Exception caused by an invalid Transfer.
 * @author ram
 *
 */
public class TransferException extends RuntimeException {

	private static final long serialVersionUID = -3016564588536474744L;

	public TransferException() {
	}

	public TransferException(String arg0) {
		super(arg0);
	}

	public TransferException(Throwable arg0) {
		super(arg0);
	}

	public TransferException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
