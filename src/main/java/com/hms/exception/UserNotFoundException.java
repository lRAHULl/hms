package com.hms.exception;

/**
 *
 * @author Rahul.
 *
 */
public class UserNotFoundException extends Exception {

	/**
	 * Serialized Id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor.
	 */
	public UserNotFoundException() {
		super();
	}

	/**
	 *
	 * @param message            String.
	 * @param cause              Throwable.
	 * @param enableSuppression  boolean.
	 * @param writableStackTrace boolean.
	 */
	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 *
	 * @param message String.
	 * @param cause   Throwable.
	 */
	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 *
	 * @param message String.
	 */
	public UserNotFoundException(String message) {
		super(message);
	}

	/**
	 *
	 * @param cause Throwable.
	 */
	public UserNotFoundException(Throwable cause) {
		super(cause);
	}

}
