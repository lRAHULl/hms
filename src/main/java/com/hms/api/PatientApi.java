package com.hms.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hms.constants.ResponseConstants;
import com.hms.delegate.PatientDelegate;
import com.hms.exception.UsernameAlreadyExistsException;
import com.hms.model.ResponseBody;
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
	public ResponseBody createPatient(Patient patient) throws UsernameAlreadyExistsException {
		LOGGER.info("Entered the createPatient Service with patient object of username: " + patient.getUsername());
		Patient createdPatient = patientDelegate.createPatient(patient);
		ResponseBody response = new ResponseBody();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(createdPatient);
		LOGGER.info("Entered the createPatient Service with response status: " + response.getStatus());
		return response;
	}

	/**
	 * GET /patients/ shows all patient.
	 *
	 * @return JSON Response with data as a list of patients.
	 */
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody readPatients() {
		LOGGER.info("Enter the readPatients Service method");
		List<Patient> patients = patientDelegate.readPatients();
		ResponseBody response = new ResponseBody();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(patients);
		LOGGER.info("Exit the readPatients Service method");
		return response;
	}

	/**
	 * GET /patients/{id} shows patient with userId = id.
	 *
	 * @param id taken from the url {id}.
	 * @return JSON Response with data as a patient with the given id.
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody readPatient(@PathParam("id") int id) {
		LOGGER.info("Enter the readPatient Service method with id: " + id);
		Patient patient = patientDelegate.readPatient(id);
		ResponseBody response = new ResponseBody();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(patient);
		LOGGER.info("Exit the readPatient Service method");
		return response;

	}

	/**
	 * PUT /patients/ Update the info of given patient.
	 *
	 * @param patient who needs to be updated.
	 * @return JSON response of the data and statusCode.
	 */
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody updatePatient(Patient patient) {
		LOGGER.info("Enter the updatePatient Service method with id: " + patient.getUserId());
		boolean status = patientDelegate.updatePatient(patient);
		ResponseBody response = new ResponseBody();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(status);
		LOGGER.info("Exit the updatePatient Service method");
		return response;

	}

	/**
	 * DELETE /patients/ Delete the given patient.
	 *
	 * @param patient who needs to be deleted.
	 * @return JSON response of the data and statusCode.
	 */
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePatient(Patient patient) {
		LOGGER.info("Enter the deletePatient Service method with id: " + patient.getUserId());
		boolean status = patientDelegate.deletePatient(patient);
		LOGGER.info("Exit the deletePatient Service method");
		return Response.status(ResponseConstants.SUCCESS_WITHOUT_RESPONSE).entity(status).build();
	}
}
