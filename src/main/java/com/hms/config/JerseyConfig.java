package com.hms.config;

import org.glassfish.jersey.server.ResourceConfig;

import com.hms.api.HelloApi;
import com.hms.api.PatientApi;

/**
 *
 * @author rahul The File is Used for configuring URL end points for the
 *         application using Jersey.
 *
 */
public class JerseyConfig extends ResourceConfig {
	/**
	 * The File Registered to scan by Jersey.
	 */
	public JerseyConfig() {
		register(HelloApi.class);
		register(PatientApi.class);
	}
}
