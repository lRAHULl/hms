package com.hms.delegate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hms.exception.UsernameAlreadyExistsException;
import com.hms.helper.PatientHelper;
import com.hms.model.Patient;

/**
 *
 * This class contains all the business logic for fulfilling the requests of
 * PatientService class.
 *
 * @author Rahul
 *
 */
public class PatientDelegate {
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientDelegate.class);

	private PatientHelper patientHelper = new PatientHelper();

	/**
	 * This method calls the createPatient Helper method.
	 *
	 * @param patient object.
	 * @return patient object generated by createPatient DAO method.
	 * @throws UsernameAlreadyExistsException from createPatient DAO method.
	 */
	public Patient createPatient(Patient patient) throws UsernameAlreadyExistsException {
		LOGGER.info(
				"Enter the createPatient Delegate method with patient object with username: " + patient.getUsername());
		Patient createdPatient = patientHelper.createPatient(patient);
		createdPatient.setPassword(null);
		LOGGER.info("Enter the createPatient Delegate method with patient object with userid: "
				+ createdPatient.getUserId());
		return createdPatient;
	}
}