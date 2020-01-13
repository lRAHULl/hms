package com.hms.constants;

/**
 *
 * All the Response codes and messages needed by the application come here.
 *
 * @author Rahul.
 *
 */
public final class ResponseConstants {

	/**
	 * Utility classes should have private constructors.
	 */
	private ResponseConstants() {
	}

	public static final int SUCCESS = 200;
	public static final int SUCCESS_WITHOUT_RESPONSE = 204;
	public static final int CLIENT_ERROR = 400;
	public static final int SERVER_ERROR = 500;
}
