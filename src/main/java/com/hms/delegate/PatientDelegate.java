package com.hms.delegate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hms.exception.UsernameAlreadyExistsException;
import com.hms.helper.PatientHelper;
import com.hms.model.Patient;

/**
 *
 * This class contains all the business logic for fulfilling the requests of
 * PatientApi class.
 *
 * @author Rahul
 *
 */
public class PatientDelegate {
	private static final Logger LOGGER = LogManager.getLogger(PatientDelegate.class);

	private PatientHelper patientHelper = new PatientHelper();

	/**
	 * This method calls the createPatient Helper method.
	 *
	 * @param patient object.
	 * @return patient object generated by createPatient DAO method.
	 * @throws UsernameAlreadyExistsException from createPatient DAO method.
	 */
	public Patient createPatient(Patient patient) throws UsernameAlreadyExistsException {
		LOGGER.traceEntry(patient.toString());
		Patient createdPatient;
		try {
			createdPatient = patientHelper.createPatient(patient);
			createdPatient.setPassword(null);
			LOGGER.traceExit(createdPatient.toString());
			return createdPatient;
		} catch (UsernameAlreadyExistsException e) {
			throw new UsernameAlreadyExistsException("Username already exists", e);
		}

	}

	/**
	 * Outputs a list of patient objects.
	 *
	 * @return list of patients.
	 */
	public List<Patient> readPatients() {
		LOGGER.traceEntry();
		List<Patient> patients = patientHelper.readPatients();
		for (Patient patient : patients) {
			patient.setPassword(null);
		}
		LOGGER.traceExit(patients.toString());
		return patients;
	}

	/**
	 * Outputs a of patient object with userId = id.
	 *
	 * @param id userId from patientService.
	 * @return patients with userId = id.
	 */
	public Patient readPatient(int id) {
		LOGGER.traceEntry(Integer.toString(id));
		Patient patient = patientHelper.readPatient(id);
		patient.setPassword(null);
		LOGGER.traceExit(patient.toString());
		return patient;
	}

	/**
	 * This method is used to update a given patient's info in the Helper.
	 *
	 * @param patient who needs to be updated.
	 * @return true if patient updated else throw an exception.
	 */
	public boolean updatePatient(Patient patient) {
		LOGGER.traceEntry(patient.toString());
		boolean status = patientHelper.updatePatient(patient);
		if (status) {
			LOGGER.traceExit(status);
			return status;
		} else {
//			Exception should be thrown.
			return false;
		}
	}

	/**
	 * This method is used to delete a given patient in the Helper.
	 *
	 * @param patient who needs to be delete.
	 * @return true if patient deleted else throw an exception.
	 */
	public boolean deletePatient(Patient patient) {
		LOGGER.traceEntry(patient.toString());
		boolean status = patientHelper.deletePatient(patient);
		if (status) {
			LOGGER.traceExit(status);
			return status;
		} else {
//			Exception should be thrown.
			return false;
		}
	}
}
