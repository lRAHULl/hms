package com.hms.exception;

/**
 *
 * @author Rahul.
 *
 */
public class ServerConnectionFailedException extends Exception {

	/**
	 * Serialized Id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor.
	 */
	public ServerConnectionFailedException() {
		super();
	}

	/**
	 *
	 * @param message            String.
	 * @param cause              Throwable.
	 * @param enableSuppression  boolean.
	 * @param writableStackTrace boolean.
	 */
	public ServerConnectionFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 *
	 * @param message String.
	 * @param cause   Throwable.
	 */
	public ServerConnectionFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 *
	 * @param message String.
	 */
	public ServerConnectionFailedException(String message) {
		super(message);
	}

	/**
	 *
	 * @param cause Throwable.
	 */
	public ServerConnectionFailedException(Throwable cause) {
		super(cause);
	}

}
