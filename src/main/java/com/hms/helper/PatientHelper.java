package com.hms.helper;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hms.dao.PatientDao;
import com.hms.exception.UsernameAlreadyExistsException;
import com.hms.model.Patient;

/**
 *
 * This Class is used to provide different helper methods to the Delegate layer
 * primarily the PatientDelegate Class.
 *
 * @author Rahul
 *
 */
public class PatientHelper {

	private static final Logger LOGGER = LogManager.getLogger(PatientHelper.class);

	private PatientDao patientDao = new PatientDao();

	/**
	 *
	 * This method passes the input as a patient to the PatientDao and gets the
	 * database created Patient in return.
	 *
	 * @param patient object from createPatient Delegate method.
	 * @return patient object generated by createPatient DAO method.
	 * @throws UsernameAlreadyExistsException custom exception.
	 */
	public Patient createPatient(Patient patient) throws UsernameAlreadyExistsException {
		LOGGER.traceEntry(patient.toString());
		Patient createdPatient = patientDao.createPatient(patient);
		LOGGER.traceExit(createdPatient.toString());
		return createdPatient;
	}

	/**
	 * Outputs all the Patients from the DAO method.
	 *
	 * @return list of patients to the delegate layer.
	 */
	public List<Patient> readPatients() {
		LOGGER.traceEntry();
		List<Patient> patients = patientDao.readPatients();
		LOGGER.traceExit(patients.toString());
		return patients;
	}

	/**
	 * Gets the Patient with a given id from the PatientDAO.
	 *
	 * @param id of the patient.
	 * @return Patient with id from the PatientDAO.
	 */
	public Patient readPatient(int id) {
		LOGGER.traceEntry(Integer.toString(id));
		Patient patient = patientDao.readPatient(id);
		LOGGER.traceExit(patient.toString());
		return patient;
	}

	/**
	 * This method is used to update a given patient's info in the DAO.
	 *
	 * @param patient who needs to be updated.
	 * @return true if patient updated else false.
	 */
	public boolean updatePatient(Patient patient) {
		LOGGER.traceEntry(patient.toString());
		boolean status = patientDao.updatePatient(patient);
		LOGGER.traceExit(status);
		return status;
	}

	/**
	 * This method is used to delete a given patient's info in the DAO.
	 *
	 * @param patient who needs to be deleted.
	 * @return true if patient deleted else false.
	 */
	public boolean deletePatient(Patient patient) {
		LOGGER.traceEntry(patient.toString());
		boolean status = patientDao.deletePatient(patient.getUserId());
		LOGGER.traceExit(status);
		return status;
	}

}
