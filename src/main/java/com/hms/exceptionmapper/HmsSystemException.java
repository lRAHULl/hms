package com.hms.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.hms.constants.ResponseConstants;

/**
 *
 * This Class is used to represent all the system generated exceptions.
 *
 * @author Rahul.
 *
 */
@Provider
public class HmsSystemException extends Exception implements ExceptionMapper<HmsSystemException> {

	/**
	 * Default Constructor.
	 */
	public HmsSystemException() {
		super();
	}

	/**
	 *
	 * @param message            String.
	 * @param cause              Throwable.
	 * @param enableSuppression  boolean.
	 * @param writableStackTrace boolean.
	 */
	public HmsSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 *
	 * @param message String.
	 * @param cause   Throwable.
	 */
	public HmsSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 *
	 * @param message String.
	 */
	public HmsSystemException(String message) {
		super(message);
	}

	/**
	 *
	 * @param cause Throwable.
	 */
	public HmsSystemException(Throwable cause) {
		super(cause);
	}

	/**
	 * Serialized Id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return Response of the System exception.
	 */
	@Override
	public Response toResponse(HmsSystemException exception) {
		return Response.status(ResponseConstants.SERVER_ERROR).entity("Something went wrong").type("text/plain")
				.build();

	}

}
