package com.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hms.config.DbConfig;
import com.hms.constants.NumberConstants;
import com.hms.constants.QueryConstants;
import com.hms.exception.UsernameAlreadyExistsException;
import com.hms.model.Patient;

/**
 *
 * This Class performs CRUD Operations for Patient in the Database.
 *
 * @author Rahul
 *
 */
public class PatientDao {

	private static final Logger LOGGER = LogManager.getLogger(PatientDao.class);

	/**
	 * This method creates a patient in the database.
	 *
	 * @param patient object.
	 * @return The patient object with the generated userId.
	 * @throws UsernameAlreadyExistsException custom exception.
	 * @throws SQLException
	 */
	public Patient createPatient(Patient patient) throws UsernameAlreadyExistsException {
		LOGGER.info("Entered the createPatient DAO method");
		PreparedStatement userStatement, patientStatement;
		DbConfig dbConfig = null;
		Connection connection = null;

		try {
			dbConfig = new DbConfig();
			connection = dbConfig.getConnection();
			connection.setAutoCommit(false);
			userStatement = connection.prepareStatement(QueryConstants.USER_INSERT_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			userStatement.setString(NumberConstants.ONE, patient.getUsername());
			userStatement.setString(NumberConstants.TWO, patient.getPassword());
			userStatement.setInt(NumberConstants.THREE, NumberConstants.ONE);
			userStatement.setString(NumberConstants.FOUR, patient.getFirstName());
			userStatement.setString(NumberConstants.FIVE, patient.getLastName());
			userStatement.setInt(NumberConstants.SIX, patient.getAge());
			int rowsAffected = userStatement.executeUpdate();

			if (rowsAffected == NumberConstants.ONE) {
				ResultSet generatedResultSet = userStatement.getGeneratedKeys();
				int userId = NumberConstants.ZERO;
				if (generatedResultSet.next()) {
					userId = generatedResultSet.getInt(NumberConstants.ONE);
				}
				patientStatement = connection.prepareStatement(QueryConstants.PATIENT_INSERT_QUERY);
				patientStatement.setInt(NumberConstants.ONE, userId);
				patientStatement.setInt(NumberConstants.TWO, patient.getPatientHeight());
				patientStatement.setInt(NumberConstants.THREE, patient.getPatientWeight());
				patientStatement.setString(NumberConstants.FOUR, patient.getDoorNo());
				patientStatement.setString(NumberConstants.FIVE, patient.getStreet());
				patientStatement.setString(NumberConstants.SIX, patient.getCity());
				patientStatement.setString(NumberConstants.SEVEN, patient.getBloodGroup());

				int patientRowsAffected = patientStatement.executeUpdate();

				if (patientRowsAffected != NumberConstants.ZERO) {
					connection.commit();
					patient.setUserId(userId);
					if (connection != null) {
						dbConfig.closeConnection();
					}
					return patient;
				} else {
					connection.rollback();
					throw new UsernameAlreadyExistsException("User with username already existd");
				}
			} else {
				connection.rollback();
				throw new UsernameAlreadyExistsException("User with username already existd");
			}
		} catch (SQLException e) {
			LOGGER.info("Exited the createPatient DAO method");
			throw new UsernameAlreadyExistsException("User with username already existd");
		} finally {
			if (connection != null) {
				dbConfig.closeConnection();
			}
		}

	}

	/**
	 * Gets the List of Patients from the Database.
	 *
	 * @return list of Patients from the database.
	 */
	public List<Patient> readPatients() {
		LOGGER.info("Inside the readPatients DAO method");
		DbConfig dbConfig = null;
		Connection connection = null;
		ResultSet resultSet;
		PreparedStatement patientsReadStatement;
		Patient patient = null;
		List<Patient> patients = null;

		try {
			dbConfig = new DbConfig();
			connection = dbConfig.getConnection();
			connection.setAutoCommit(false);
			patientsReadStatement = connection.prepareStatement(QueryConstants.PATIENTS_READ_QUERY);
			resultSet = patientsReadStatement.executeQuery();

			while (resultSet.next()) {
				if (patients == null) {
					patients = new ArrayList<Patient>();
				}
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
			LOGGER.info("Exited the readPatients DAO method");
			return patients;
		} catch (SQLException e) {

		} finally {
			LOGGER.info("Inside Finally block");
			if (connection != null) {
				dbConfig.closeConnection();
			}
		}
		return null;
	}

	/**
	 * Gets the Patient with a given id from the Database.
	 *
	 * @param id of the patient.
	 * @return Patient with id from the database.
	 */
	public Patient readPatient(int id) {
		LOGGER.info("Inside the readPatient DAO method with id: " + id);
		DbConfig dbConfig = null;
		Connection connection = null;
		ResultSet resultSet;
		PreparedStatement patientReadStatement;
		Patient patient = null;

		try {
			dbConfig = new DbConfig();
			connection = dbConfig.getConnection();
			connection.setAutoCommit(false);
			patientReadStatement = connection.prepareStatement(QueryConstants.PATIENT_READ_QUERY);
			patientReadStatement.setInt(NumberConstants.ONE, id);
			resultSet = patientReadStatement.executeQuery();

			if (resultSet.next()) {
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
			}
			if (connection != null) {
				dbConfig.closeConnection();
			}
			LOGGER.info("Exited the readPatient DAO method");
			return patient;
		} catch (SQLException e) {

		} finally {
			LOGGER.info("Inside Finally block");
			if (connection != null) {
				dbConfig.closeConnection();
			}
		}
		return null;
	}

	/**
	 * This method is used to update a given patient's info in the database.
	 *
	 * @param patient who needs to be updated.
	 * @return true if patient updated else false.
	 */
	public boolean updatePatient(Patient patient) {
		LOGGER.info("Inside the updatePatient DAO method for patient with id: " + patient.getUserId());
		DbConfig dbConfig = null;
		Connection connection = null;
		PreparedStatement userUpdateStatement, patientUpdateStatement;

		try {
			dbConfig = new DbConfig();
			connection = dbConfig.getConnection();
			connection.setAutoCommit(false);
			userUpdateStatement = connection.prepareStatement(QueryConstants.USER_UPDATE_QUERY);
			userUpdateStatement.setString(NumberConstants.ONE, patient.getPassword());
			userUpdateStatement.setInt(NumberConstants.TWO, patient.getAge());
			userUpdateStatement.setInt(NumberConstants.THREE, patient.getUserId());

			int rowsAffected = userUpdateStatement.executeUpdate();
			if (rowsAffected == 1) {
				patientUpdateStatement = connection.prepareStatement(QueryConstants.PATIENT_UPDATE_QUERY);
				patientUpdateStatement.setInt(NumberConstants.ONE, patient.getPatientHeight());
				patientUpdateStatement.setInt(NumberConstants.TWO, patient.getPatientWeight());
				patientUpdateStatement.setInt(NumberConstants.THREE, patient.getUserId());

				rowsAffected = patientUpdateStatement.executeUpdate();
				if (rowsAffected == 1) {
					connection.commit();
					if (connection != null) {
						dbConfig.closeConnection();
					}
					LOGGER.info("Exited the updatePatient DAO method");
					return true;
				} else {
					connection.rollback();
				}
			} else {
				connection.rollback();
			}
		} catch (SQLException e) {
			return false;
		} finally {
			if (connection != null) {
				dbConfig.closeConnection();
				LOGGER.info("Exited the updatePatient DAO method");
			}
		}

		return false;
	}

	/**
	 * This method is used to delete a given patient in the database.
	 *
	 * @param id of the patient who needs to be deleted.
	 * @return true if patient deleted else false.
	 */
	public boolean deletePatient(int id) {
		LOGGER.info("Inside the deletePatient DAO method for patient with id: " + id);
		DbConfig dbConfig = null;
		Connection connection = null;
		PreparedStatement userDeleteStatement, patientDeleteStatement;

		try {
			dbConfig = new DbConfig();
			connection = dbConfig.getConnection();
			connection.setAutoCommit(false);
			userDeleteStatement = connection.prepareStatement(QueryConstants.USER_DELETE_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			userDeleteStatement.setInt(NumberConstants.ONE, id);
			int rowsAffected = userDeleteStatement.executeUpdate();
			if (rowsAffected == NumberConstants.ONE) {
				patientDeleteStatement = connection.prepareStatement(QueryConstants.PATIENT_DELETE_QUERY);
				patientDeleteStatement.setInt(NumberConstants.ONE, id);
				int rowsAffectedPatient = patientDeleteStatement.executeUpdate();
				if (rowsAffectedPatient == NumberConstants.ONE) {
					connection.commit();
					if (connection != null) {
						dbConfig.closeConnection();
					}
					LOGGER.info("Exited the deletePatient DAO method");
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
		LOGGER.info("Exited the deletePatient DAO method");
		return false;

	}
}
