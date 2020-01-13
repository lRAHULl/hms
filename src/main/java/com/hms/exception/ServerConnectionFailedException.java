package com.hms.exception;

public class ServerConnectionFailedException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ServerConnectionFailedException() {
		super();
	}

	public ServerConnectionFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServerConnectionFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerConnectionFailedException(String message) {
		super(message);
	}

	public ServerConnectionFailedException(Throwable cause) {
		super(cause);
	}

}
