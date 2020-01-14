package com.hms.helper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hms.dao.DoctorDao;
import com.hms.exception.UserNotFoundException;
import com.hms.exception.UsernameAlreadyExistsException;
import com.hms.exceptionmapper.HmsBusinessException;
import com.hms.exceptionmapper.HmsSystemException;
import com.hms.model.Doctor;
import com.hms.model.Patient;

/**
 *
 * This Class is used to provide different helper methods to the Delegate layer
 * primarily the DoctorDelegate Class.
 *
 * @author Rahul.
 *
 */
public class DoctorHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorHelper.class);

	private DoctorDao doctorDao = new DoctorDao();

	/**
	 *
	 * This method passes the input as a doctor to the DoctorDao and gets the
	 * database created Doctor in return.
	 *
	 * @param doctor object from createDoctor Delegate method.
	 * @return doctor object generated by createDoctor DAO method.
	 * @throws HmsBusinessException           generic client exception.
	 * @throws HmsSystemException             generic system exception.
	 * @throws UsernameAlreadyExistsException custom exception.
	 */
	public Doctor createDoctor(Doctor doctor) throws HmsBusinessException, HmsSystemException {
		LOGGER.trace(
				"Entered the createDoctor Helper method with doctor object with username: " + doctor.getUsername());
		try {
			Doctor createdDoctor = doctorDao.createDoctor(doctor);
			createdDoctor.setPassword(null);
			LOGGER.trace("exited the createDoctor Helper with doctor object with id: " + createdDoctor.getUserId());
			return createdDoctor;
		} catch (UsernameAlreadyExistsException e) {
			throw new HmsBusinessException(e.getMessage());
		} catch (Exception e) {
			throw new HmsSystemException("System Fail");
		}
	}

	/**
	 * Outputs all the doctors from the DAO method.
	 *
	 * @return list of doctors to the delegate layer.
	 * @throws UserNotFoundException custom exception.
	 */
	public List<Doctor> readDoctors() throws UserNotFoundException {
		LOGGER.trace("Entered the readDoctors Helper method");
		List<Doctor> doctors = doctorDao.readDoctors();
		for (Doctor doctor : doctors) {
			doctor.setPassword(null);
		}
		LOGGER.trace("Exited the readDoctors Helper method");
		return doctors;
	}

	/**
	 * Outputs all the doctors from the DAO method.
	 *
	 * @param id of the doctor.
	 * @return doctor with userId = id to the delegate layer.
	 * @throws HmsBusinessException client exception.
	 * @throws HmsSystemException   system failure exception.
	 */
	public Doctor readDoctor(int id) throws HmsBusinessException, HmsSystemException {
		LOGGER.trace("Entered the readDoctor Helper method");
		Doctor doctor;
		try {
			doctor = doctorDao.readDoctor(id);
			if (doctor != null) {
				doctor.setPassword(null);
			}
		} catch (UserNotFoundException e) {
			throw new HmsBusinessException(e.getMessage());
		} catch (SQLException e) {
			throw new HmsSystemException("SQL exception occured");
		} catch (Exception e) {
			throw new HmsSystemException("System failed");
		}

		LOGGER.trace("Exited the readDoctor Helper method");
		return doctor;
	}

	/**
	 * This method is used to delete a given doctor's info in the DAO.
	 *
	 * @param doctor who needs to be deleted.
	 * @return true if doctor deleted else false.
	 * @throws HmsSystemException
	 * @throws HmsBusinessException
	 */
	public boolean deleteDoctor(Doctor doctor) throws HmsSystemException, HmsBusinessException {
		LOGGER.trace("Entered the deleteDoctor Helper method with id: " + doctor.getUserId());
		boolean status;
		try {
			status = doctorDao.deleteDoctor(doctor.getUserId());
		} catch (SQLException e) {
			throw new HmsSystemException("SQL Exception Ocurred");
		} catch (Exception e) {
			throw new HmsSystemException("System Error");
		}
		if (!status) {
			throw new HmsBusinessException("User with Id not found.");
		}
		LOGGER.trace("Exited the deletePatient Helper method");
		return status;
	}

	/**
	 *
	 * @return map of doctors and list of patients.
	 * @throws HmsSystemException   generic System Exception.
	 * @throws HmsBusinessException
	 */
	public Map<Integer, List<Patient>> patientsForDoctors() throws HmsSystemException, HmsBusinessException {
		Map<Integer, List<Patient>> map = null;
		try {
			map = doctorDao.patientsForDoctors();

		} catch (SQLException e) {
			throw new HmsSystemException("SQL Error");
		} catch (Exception e) {
			throw new HmsSystemException("System Error");
		}
		if (map == null) {
			throw new HmsBusinessException("No Users Found.");
		}
		return map;
	}
}
