package com.hms.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Class is used for testing the Configuration of Jersey.
 *
 * @author Rahul.
 *
 */
@Path("/api")
public class HelloApi {

	/**
	 * Maps the method to /hello.
	 *
	 * @return String message.
	 */
	@GET
	@Path("/hello")
	public String hello() {
		return "Hello, rest!";
	}
}
