package com.hms.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hms.constants.ResponseConstants;
import com.hms.delegate.DoctorDelegate;
import com.hms.exception.UsernameAlreadyExistsException;
import com.hms.exceptionmapper.HmsBusinessException;
import com.hms.exceptionmapper.HmsSystemException;
import com.hms.model.CustomResponse;
import com.hms.model.Doctor;
import com.hms.model.Patient;

/**
 * This Class Maps all the doctors end points to the server.
 *
 * @author Rahul.
 *
 */
@Path("/doctors")
public class DoctorApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorApi.class);

	private DoctorDelegate doctorDelegate = new DoctorDelegate();

	/**
	 * POST /doctors/ creates a patient.
	 *
	 * @param doctor object.
	 * @return JSON response.
	 * @throws UsernameAlreadyExistsException custom exception.
	 * @throws HmsSystemException             generic system exception.
	 */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CustomResponse createDoctor(Doctor doctor) throws UsernameAlreadyExistsException, HmsSystemException {
		LOGGER.info("Entered the createDoctor Service with patient object of username: " + doctor.getUsername());
		Doctor createdDoctor = doctorDelegate.createDoctor(doctor);
		CustomResponse response = new CustomResponse();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(createdDoctor);
		LOGGER.info("Entered the createDoctor Service with response status: " + response.getStatus());
		return response;
	}

	/**
	 * GET /doctors/ shows all doctors.
	 *
	 * @return JSON Response with data as a list of doctors.
	 * @throws HmsBusinessException generic client exception.
	 */
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomResponse readDoctors() throws HmsBusinessException {
		LOGGER.info("Enter the readDoctors Service method");
		List<Doctor> doctors = doctorDelegate.readDoctors();
		CustomResponse response = new CustomResponse();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(doctors);
		LOGGER.info("Exit the readDoctors Service method");
		return response;
	}

	/**
	 * GET /doctors/{id} shows doctor with userId = id.
	 *
	 * @param id taken from the url {id}.
	 * @return JSON Response with data as a doctor with the given id.
	 * @throws HmsBusinessException generic client exception.
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomResponse readDoctor(@PathParam("id") int id) throws HmsBusinessException {
		LOGGER.info("Enter the readDoctor Service method with id: " + id);
		Doctor doctor = doctorDelegate.readDoctor(id);
		CustomResponse response = new CustomResponse();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(doctor);
		LOGGER.info("Exit the readDoctor Service method");
		return response;

	}

	/**
	 * DELETE /doctors/ Delete the given patient.
	 *
	 * @param doctor who needs to be deleted.
	 * @return JSON response of the data and statusCode.
	 */
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDoctor(Doctor doctor) {
		LOGGER.info("Enter the deleteDoctor Service method with id: " + doctor.getUserId());
		boolean status = doctorDelegate.deleteDoctor(doctor);
		LOGGER.info("Exit the deleteDoctor Service method");
		return Response.status(ResponseConstants.SUCCESS_WITHOUT_RESPONSE).entity(status).build();
	}

	/**
	 * GET /{id}/patients for the given doctor.
	 *
	 * @param id from the pathParam which is the id of the doctor.
	 * @return response of the data if successful.
	 */
	@GET
	@Path("/{id}/patients")
	@Produces(MediaType.APPLICATION_JSON)
	public CustomResponse getPatientsForADoctor(@PathParam("id") int id) {
		List<Patient> patients = doctorDelegate.getPatientsForADoctor(id);
		CustomResponse response = new CustomResponse();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(patients);
		return response;
	}
}
