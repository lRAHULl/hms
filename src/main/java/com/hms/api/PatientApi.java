package com.hms.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hms.constants.ResponseConstants;
import com.hms.delegate.PatientDelegate;
import com.hms.exception.UsernameAlreadyExistsException;
import com.hms.model.CustomResponse;
import com.hms.model.Patient;

/**
 *
 * This Class used to map all the REST endPoints of patients to the server.
 *
 * @author Rahul.
 *
 */
@Path("/patients")
public class PatientApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(PatientApi.class);

	private PatientDelegate patientDelegate = new PatientDelegate();

	/**
	 * POST /patients/ creates a patient.
	 *
	 * @param patient object.
	 * @return JSON response.
	 * @throws UsernameAlreadyExistsException custom exception.
	 */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CustomResponse createPatient(Patient patient) throws UsernameAlreadyExistsException {
		LOGGER.info("Entered the createPatient Service with patient object of username: " + patient.getUsername());
		Patient createdPatient = patientDelegate.createPatient(patient);
		CustomResponse response = new CustomResponse();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(createdPatient);
		LOGGER.info("Entered the createPatient Service with response status: " + response.getStatus());
		return response;
	}
}
