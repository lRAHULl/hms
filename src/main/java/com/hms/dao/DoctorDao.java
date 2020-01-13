package com.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hms.config.DbConfig;
import com.hms.constants.NumberConstants;
import com.hms.constants.QueryConstants;
import com.hms.exception.UserNotFoundException;
import com.hms.exception.UsernameAlreadyExistsException;
import com.hms.model.Doctor;
import com.hms.model.Patient;

/**
 *
 * This Class performs CRUD Operations for Doctor in the Database.
 *
 * @author Rahul
 *
 */
public class DoctorDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorDao.class);

	/**
	 *
	 * This method creates a doctor in the database.
	 *
	 * @param doctor object.
	 * @return The Doctor object with the generated userId.
	 * @throws UsernameAlreadyExistsException custom exception.
	 */
	public Doctor createDoctor(Doctor doctor) throws UsernameAlreadyExistsException {
		LOGGER.trace("Enter the createDoctor with id: " + doctor.getUserId());
		PreparedStatement userInsertStatement, doctorInsertStatement;
		DbConfig dbConfig = null;
		Connection connection = null;

		try {
			dbConfig = new DbConfig();
			connection = dbConfig.getConnection();
			connection.setAutoCommit(false);
			userInsertStatement = connection.prepareStatement(QueryConstants.USER_INSERT_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			userInsertStatement.setString(NumberConstants.ONE, doctor.getUsername());
			userInsertStatement.setString(NumberConstants.TWO, doctor.getPassword());
			userInsertStatement.setInt(NumberConstants.THREE, NumberConstants.TWO);
			userInsertStatement.setString(NumberConstants.FOUR, doctor.getFirstName());
			userInsertStatement.setString(NumberConstants.FIVE, doctor.getLastName());
			userInsertStatement.setInt(NumberConstants.SIX, doctor.getAge());
			int rowsAffected = userInsertStatement.executeUpdate();

			if (rowsAffected == NumberConstants.ONE) {
				ResultSet generatedResultSet = userInsertStatement.getGeneratedKeys();
				int userId = NumberConstants.ZERO;
				if (generatedResultSet.next()) {
					userId = generatedResultSet.getInt(NumberConstants.ONE);
				}
				doctorInsertStatement = connection.prepareStatement(QueryConstants.DOCTOR_INSERT_QUERY);
				doctorInsertStatement.setInt(NumberConstants.ONE, userId);
				doctorInsertStatement.setString(NumberConstants.TWO, doctor.getSpecilization());
				doctorInsertStatement.setInt(NumberConstants.THREE, doctor.getExperience());

				int doctorRowsAffected = doctorInsertStatement.executeUpdate();

				if (doctorRowsAffected != NumberConstants.ZERO) {
					connection.commit();
					doctor.setUserId(userId);
					if (connection != null) {
						dbConfig.closeConnection();
					}
					return doctor;
				} else {
					connection.rollback();
					throw new UsernameAlreadyExistsException("User with username already existd");
				}
			} else {
				connection.rollback();
				throw new UsernameAlreadyExistsException("User with username already existd");
			}
		} catch (SQLException e) {
			LOGGER.trace("Exited the createPatient DAO method");
			throw new UsernameAlreadyExistsException("User with username already existd");
		} finally {
			if (connection != null) {
				dbConfig.closeConnection();
			}
		}

	}

	/**
	 * Gets the List of Doctors from the Database.
	 *
	 * @return list of Doctors from the database.
	 * @throws UserNotFoundException Custom Exception for no user with that id.
	 */
	public List<Doctor> readDoctors() throws UserNotFoundException {
		LOGGER.trace("Inside the readDoctors DAO method");
		DbConfig dbConfig = null;
		Connection connection = null;
		ResultSet resultSet;
		PreparedStatement doctorReadStatement;
		Doctor doctor = null;
		List<Doctor> doctors = null;

		try {
			dbConfig = new DbConfig();
			connection = dbConfig.getConnection();
			connection.setAutoCommit(false);
			doctorReadStatement = connection.prepareStatement(QueryConstants.DOCTORS_READ_QUERY);
			resultSet = doctorReadStatement.executeQuery();

			while (resultSet.next()) {
				if (doctors == null) {
					doctors = new ArrayList<Doctor>();
				}
				doctor = new Doctor();
				doctor.setUsername(resultSet.getString(QueryConstants.USERNAME));
				doctor.setPassword(resultSet.getString(QueryConstants.PASSWORD));
				doctor.setUserId(resultSet.getInt(QueryConstants.PK_USER_ID));
				doctor.setRoleId(resultSet.getInt(QueryConstants.FK_ROLE_ID));
				doctor.setFirstName(resultSet.getString(QueryConstants.FIRST_NAME));
				doctor.setLastName(resultSet.getString(QueryConstants.LAST_NAME));
				doctor.setAge(resultSet.getInt(QueryConstants.AGE));
				doctor.setDoctorId(resultSet.getInt(QueryConstants.PK_DOCTOR_ID));
				doctor.setSpecilization(resultSet.getString(QueryConstants.SPECIALIZATION));
				doctor.setExperience(resultSet.getInt(QueryConstants.EXPERIENCE));
				doctors.add(doctor);
			}
			if (connection != null) {
				dbConfig.closeConnection();
			}
			LOGGER.trace("Exited the readDoctors DAO method");
			return doctors;
		} catch (SQLException e) {
			throw new UserNotFoundException("User with id not found");
		} finally {
			LOGGER.trace("Inside Finally block");
			if (connection != null) {
				dbConfig.closeConnection();
			}
		}
	}

	/**
	 * Gets the Doctor with a given id from the Database.
	 *
	 * @param id of the Doctor.
	 * @return Doctor with id from the database.
	 * @throws UserNotFoundException Custom Exception for no user with that id.
	 */
	public Doctor readDoctor(int id) throws UserNotFoundException {
		LOGGER.trace("Inside the readDoctor DAO method");
		DbConfig dbConfig = null;
		Connection connection = null;
		ResultSet resultSet;
		PreparedStatement doctorReadStatement;
		Doctor doctor = null;

		try {
			dbConfig = new DbConfig();
			connection = dbConfig.getConnection();
			connection.setAutoCommit(false);
			doctorReadStatement = connection.prepareStatement(QueryConstants.DOCTOR_READ_QUERY);
			doctorReadStatement.setInt(NumberConstants.ONE, id);
			resultSet = doctorReadStatement.executeQuery();

			if (resultSet.next()) {
				doctor = new Doctor();
				doctor.setUsername(resultSet.getString(QueryConstants.USERNAME));
				doctor.setPassword(resultSet.getString(QueryConstants.PASSWORD));
				doctor.setUserId(resultSet.getInt(QueryConstants.PK_USER_ID));
				doctor.setRoleId(resultSet.getInt(QueryConstants.FK_ROLE_ID));
				doctor.setFirstName(resultSet.getString(QueryConstants.FIRST_NAME));
				doctor.setLastName(resultSet.getString(QueryConstants.LAST_NAME));
				doctor.setAge(resultSet.getInt(QueryConstants.AGE));
				doctor.setDoctorId(resultSet.getInt(QueryConstants.PK_DOCTOR_ID));
				doctor.setSpecilization(resultSet.getString(QueryConstants.SPECIALIZATION));
				doctor.setExperience(resultSet.getInt(QueryConstants.EXPERIENCE));
			}
			if (connection != null) {
				dbConfig.closeConnection();
			}
			LOGGER.trace("Exited the readDoctor DAO method");
			return doctor;
		} catch (SQLException e) {
			throw new UserNotFoundException("User with id not found");
		} finally {
			LOGGER.trace("Inside Finally block");
			if (connection != null) {
				dbConfig.closeConnection();
			}
		}
	}

	/**
	 * This method is used to delete a given doctor in the database.
	 *
	 * @param id of the doctor who needs to be deleted.
	 * @return true if doctor deleted else false.
	 */
	public boolean deleteDoctor(int id) {
		LOGGER.trace("Inside the deleteDoctor DAO method for patient with id: " + id);
		DbConfig dbConfig = null;
		Connection connection = null;
		PreparedStatement userDeleteStatement, doctorDeleteStatement;

		try {
			dbConfig = new DbConfig();
			connection = dbConfig.getConnection();
			connection.setAutoCommit(false);
			userDeleteStatement = connection.prepareStatement(QueryConstants.USER_DELETE_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			userDeleteStatement.setInt(NumberConstants.ONE, id);
			int rowsAffected = userDeleteStatement.executeUpdate();
			if (rowsAffected == NumberConstants.ONE) {
				doctorDeleteStatement = connection.prepareStatement(QueryConstants.DOCTOR_DELETE_QUERY);
				doctorDeleteStatement.setInt(NumberConstants.ONE, id);
				int rowsAffectedPatient = doctorDeleteStatement.executeUpdate();
				if (rowsAffectedPatient == NumberConstants.ONE) {
					connection.commit();
					if (connection != null) {
						dbConfig.closeConnection();
					}
					LOGGER.trace("Exited the deleteDoctor DAO method");
					return true;
				} else {
					connection.rollback();
					return false;
				}
			} else {
				connection.rollback();
			}

		} catch (SQLException e) {

		} finally {
			if (connection != null) {
				dbConfig.closeConnection();
			}
		}
		LOGGER.trace("Exited the deleteDoctor DAO method");
		return false;
	}

	/**
	 * This method returns a list of patient ids for the given doctor.
	 *
	 * @param id of the doctor.
	 * @return list of ids of patients for the given doctor.
	 */
	public List<Integer> getPatientsForAGivenDoctor(int id) {
		LOGGER.trace("Inside the getPatientsForAGivenDoctor method for doctorId: " + id);
		DbConfig dbConfig = null;
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		List<Integer> patientIds = new ArrayList<Integer>();

		try {
			dbConfig = new DbConfig();
			connection = dbConfig.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(QueryConstants.PATIENT_IDS_FOR_A_GIVEN_DOCTOR);
			preparedStatement.setInt(NumberConstants.ONE, id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				patientIds.add(resultSet.getInt(NumberConstants.ONE));
			}
			return patientIds;
		} catch (SQLException e) {
		} finally {
			if (connection != null) {
				dbConfig.closeConnection();
			}
		}
		LOGGER.trace("Exited the getPatientsForAGivenDoctor DAO method");
		return null;

	}

	/**
	 * This method returns a list of patients for the given comma-separated user
	 * ids.
	 * 
	 * @param ids as a string for the Database.
	 * @return list of patients for the given ids.
	 */
	public List<Patient> getPatientsWithIds(String ids) {
		LOGGER.trace("Inside the readPatients DAO method");
		DbConfig dbConfig = null;
		Connection connection = null;
		ResultSet resultSet;
		PreparedStatement patientsReadStatement;
		Patient patient = null;
		List<Patient> patients = new ArrayList<Patient>();

		try {
			dbConfig = new DbConfig();
			connection = dbConfig.getConnection();
			connection.setAutoCommit(false);
			patientsReadStatement = connection.prepareStatement(QueryConstants.PATIENTS_WITH_IDS);
			patientsReadStatement.setString(NumberConstants.ONE, ids);
			resultSet = patientsReadStatement.executeQuery();

			while (resultSet.next()) {
				patient = new Patient();
				patient.setUsername(resultSet.getString(QueryConstants.USERNAME));
				patient.setPassword(resultSet.getString(QueryConstants.PASSWORD));
				patient.setUserId(resultSet.getInt(QueryConstants.PK_USER_ID));
				patient.setRoleId(resultSet.getInt(QueryConstants.FK_ROLE_ID));
				patient.setFirstName(resultSet.getString(QueryConstants.FIRST_NAME));
				patient.setLastName(resultSet.getString(QueryConstants.LAST_NAME));
				patient.setAge(resultSet.getInt(QueryConstants.AGE));
				patient.setPatientId(resultSet.getInt(QueryConstants.PK_PATIENT_ID));
				patient.setPatientHeight(resultSet.getInt(QueryConstants.PATIENT_HEIGHT));
				patient.setPatientWeight(resultSet.getInt(QueryConstants.PATIENT_WEIGHT));
				patient.setDoorNo(resultSet.getString(QueryConstants.DOOR_NO));
				patient.setStreet(resultSet.getString(QueryConstants.STREET));
				patient.setCity(resultSet.getString(QueryConstants.CITY));
				patient.setBloodGroup(resultSet.getString(QueryConstants.BLOOD_GROUP));
				patients.add(patient);
			}
			if (connection != null) {
				dbConfig.closeConnection();
			}
			LOGGER.trace("Exited the readPatients DAO method");
			return patients;
		} catch (SQLException e) {

		} finally {
			LOGGER.trace("Inside Finally block");
			if (connection != null) {
				dbConfig.closeConnection();
			}
		}
		return null;

	}
}
