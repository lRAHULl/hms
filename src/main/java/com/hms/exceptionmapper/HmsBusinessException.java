package com.hms.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.hms.constants.ResponseConstants;

/**
 *
 * This Class is used to represent all the logical and business exceptions.
 *
 * @author Rahul.
 *
 */
@Provider
public class HmsBusinessException extends Exception implements ExceptionMapper<HmsBusinessException> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public HmsBusinessException() {
		super();
	}

	public HmsBusinessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HmsBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public HmsBusinessException(String message) {
		super(message);
	}

	public HmsBusinessException(Throwable cause) {
		super(cause);
	}

	@Override
	public Response toResponse(HmsBusinessException exception) {
		return Response.status(ResponseConstants.CLIENT_ERROR).entity(exception.getMessage()).type("text/plain")
				.build();
	}

}
