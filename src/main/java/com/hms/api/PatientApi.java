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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hms.constants.ResponseConstants;
import com.hms.delegate.PatientDelegate;
import com.hms.exception.UsernameAlreadyExistsException;
import com.hms.model.Patient;
import com.hms.model.ResponseBody;

/**
 *
 * This Class used to map all the REST endPoints of patients to the server.
 *
 * @author Rahul.
 *
 */
@Path("/patients")
public class PatientApi {

	private static final Logger LOGGER = LogManager.getLogger(PatientApi.class);

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
		LOGGER.traceEntry(patient.toString());
		Patient createdPatient = patientDelegate.createPatient(patient);
		ResponseBody response = new ResponseBody();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(createdPatient);
		LOGGER.traceExit(response.toString());
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
		LOGGER.traceEntry();
		List<Patient> patients = patientDelegate.readPatients();
		ResponseBody response = new ResponseBody();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(patients);
		LOGGER.traceExit(patients.toString());
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
		LOGGER.traceEntry(Integer.toString(id));
		Patient patient = patientDelegate.readPatient(id);
		ResponseBody response = new ResponseBody();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(patient);
		LOGGER.traceExit(response.toString());
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
		LOGGER.traceEntry(patient.toString());
		boolean status = patientDelegate.updatePatient(patient);
		ResponseBody response = new ResponseBody();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(status);
		LOGGER.traceExit(response.toString());
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
		LOGGER.traceEntry(patient.toString());
		boolean status = patientDelegate.deletePatient(patient);
		LOGGER.traceExit(status);
		return Response.status(ResponseConstants.SUCCESS_WITHOUT_RESPONSE).entity(status).build();
	}
}
