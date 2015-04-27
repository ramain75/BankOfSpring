package org.bankofspring.exception;

public class OperationDisallowedException extends Exception {
	private static final long serialVersionUID = -9085389527455283509L;
	
	public OperationDisallowedException( String message ) {
		super( message );
	}
}
