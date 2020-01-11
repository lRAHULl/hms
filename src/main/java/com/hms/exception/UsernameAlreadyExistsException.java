package com.hms.exception;

/**
 * A custom client error to denote that the userName already exists.
 *
 * @author Rahul
 *
 */
public class UsernameAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	public UsernameAlreadyExistsException() {
		super();
	}

	/**
	 * This method is invoked by throwing the exception with 4 parameters.
	 *
	 * @param message            String.
	 * @param cause              Throwable.
	 * @param enableSuppression  boolean.
	 * @param writableStackTrace boolean.
	 */
	public UsernameAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * This method is invoked by throwing the exception with 2 parameters.
	 *
	 * @param message String.
	 * @param cause   Throwable.
	 */
	public UsernameAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * This method is invoked by throwing the exception with 1 message parameter.
	 *
	 * @param message String.
	 */
	public UsernameAlreadyExistsException(String message) {
		super(message);
	}

	/**
	 * This method is invoked by throwing the exception with 1 Throwable parameters.
	 *
	 * @param cause Throwable.
	 */
	public UsernameAlreadyExistsException(Throwable cause) {
		super(cause);
	}

}
