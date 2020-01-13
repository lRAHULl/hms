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

	public HmsSystemException() {
		super();
	}

	public HmsSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HmsSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public HmsSystemException(String message) {
		super(message);
	}

	public HmsSystemException(Throwable cause) {
		super(cause);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Response toResponse(HmsSystemException exception) {
		return Response.status(ResponseConstants.SERVER_ERROR).entity("Something went wrong").type("text/plain")
				.build();

	}

}
