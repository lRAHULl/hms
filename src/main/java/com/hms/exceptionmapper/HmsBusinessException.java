package com.hms.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	 * Serialization id.
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(HmsBusinessException.class);

	/**
	 * Empty constructor.
	 */
	public HmsBusinessException() {
		super();
	}

	/**
	 *
	 * @param message            String.
	 * @param cause              Throwable.
	 * @param enableSuppression  boolean.
	 * @param writableStackTrace boolean.
	 */
	public HmsBusinessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 *
	 * @param message String.
	 * @param cause   Throwable.
	 */
	public HmsBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 *
	 * @param message String.
	 */
	public HmsBusinessException(String message) {
		super(message);
	}

	/**
	 *
	 * @param cause Throwable.
	 */
	public HmsBusinessException(Throwable cause) {
		super(cause);
	}

	/**
	 * @return response object.
	 */
	@Override
	public Response toResponse(HmsBusinessException exception) {
		LOGGER.error(exception.getLocalizedMessage());
		return Response.status(ResponseConstants.CLIENT_ERROR).entity(exception.getMessage()).type("text/plain")
				.build();
	}

}
