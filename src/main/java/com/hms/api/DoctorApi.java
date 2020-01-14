package com.hms.api;

import java.util.List;
import java.util.Map;

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
import com.hms.exceptionmapper.HmsBusinessException;
import com.hms.exceptionmapper.HmsSystemException;
import com.hms.model.Doctor;
import com.hms.model.Patient;
import com.hms.model.ResponseBody;

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
	 * @throws HmsSystemException   generic system exception.
	 * @throws HmsBusinessException generic client exception.
	 */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody createDoctor(Doctor doctor) throws HmsSystemException, HmsBusinessException {
		LOGGER.trace("Entered the createDoctor Service with patient object of username: " + doctor.getUsername());
		Doctor createdDoctor = doctorDelegate.createDoctor(doctor);
		ResponseBody response = new ResponseBody();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(createdDoctor);
		LOGGER.trace("Entered the createDoctor Service with response status: " + response.getStatus());
		return response;
	}

	/**
	 * GET /doctors/ shows all doctors.
	 *
	 * @return JSON Response with data as a list of doctors.
	 * @throws HmsBusinessException generic client exception.
	 * @throws HmsSystemException   generic system exception.
	 */
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody readDoctors() throws HmsBusinessException, HmsSystemException {
		LOGGER.trace("Enter the readDoctors Service method");
		List<Doctor> doctors = doctorDelegate.readDoctors();
		ResponseBody response = new ResponseBody();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(doctors);
		LOGGER.trace("Exit the readDoctors Service method");
		return response;
	}

	/**
	 * GET /doctors/{id} shows doctor with userId = id.
	 *
	 * @param id taken from the url {id}.
	 * @return JSON Response with data as a doctor with the given id.
	 * @throws HmsBusinessException generic client exception.
	 * @throws HmsSystemException   system failure exception.
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody readDoctor(@PathParam("id") int id) throws HmsBusinessException, HmsSystemException {
		LOGGER.trace("Enter the readDoctor Service method with id: " + id);
		Doctor doctor = doctorDelegate.readDoctor(id);
		ResponseBody response = new ResponseBody();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(doctor);
		LOGGER.trace("Exit the readDoctor Service method");
		return response;

	}

	/**
	 * DELETE /doctors/ Delete the given patient.
	 *
	 * @param doctor who needs to be deleted.
	 * @return JSON response of the data and statusCode.
	 * @throws HmsBusinessException generic client exception.
	 * @throws HmsSystemException   generic system exception.
	 */
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDoctor(Doctor doctor) throws HmsBusinessException, HmsSystemException {
		LOGGER.trace("Enter the deleteDoctor Service method with id: " + doctor.getUserId());
		boolean status = doctorDelegate.deleteDoctor(doctor);
		LOGGER.trace("Exit the deleteDoctor Service method");
		return Response.status(ResponseConstants.SUCCESS_WITHOUT_RESPONSE).entity(status).build();
	}

	/**
	 * GET /{id}/patients for the given doctor.
	 *
	 * @param id from the pathParam which is the id of the doctor.
	 * @return response of the data if successful.
	 * @throws HmsSystemException   Generic System Exception.
	 * @throws HmsBusinessException Generic Client Exception.
	 */
	@GET
	@Path("/{id}/patients")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody getPatientsForDoctor(@PathParam("id") int id) throws HmsSystemException, HmsBusinessException {
		LOGGER.trace("Enter the getPatientsForDoctor method with doctorId: " + id);
		List<Patient> patients = doctorDelegate.patientsForDoctor(id);
		ResponseBody response = new ResponseBody();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(patients);
		LOGGER.trace("Exit the getPatientsForDoctor method");
		return response;
	}

	/**
	 * GET /getPatients/ all patients for all doctors.
	 *
	 * @return Response of the data if successful.
	 * @throws HmsSystemException   Generic System Exception.
	 * @throws HmsBusinessException Generic Client Exception.
	 */
	@GET
	@Path("/getPatients")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseBody patientsForDoctors() throws HmsSystemException, HmsBusinessException {
		LOGGER.trace("Enter the patientsForDoctors method.");
		Map<Integer, List<Patient>> map = doctorDelegate.patientsForDoctors();
		ResponseBody response = new ResponseBody();
		response.setStatus(ResponseConstants.SUCCESS);
		response.setData(map);
		LOGGER.trace("Exit the patientsForDoctors method.");
		return response;
	}
}
