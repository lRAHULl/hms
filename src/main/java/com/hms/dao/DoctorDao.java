package com.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hms.config.DbConfig;
import com.hms.constants.LoggerConstants;
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
	private static final Logger LOGGER = LogManager.getLogger(DoctorDao.class);
	private static final ResourceBundle MESSAGE_BUNDLE = ResourceBundle.getBundle(LoggerConstants.LOGGER_MESSAGES);

	/**
	 *
	 * This method creates a doctor in the database.
	 *
	 * @param doctor object.
	 * @return The Doctor object with the generated userId.
	 * @throws UsernameAlreadyExistsException custom exception.
	 * @throws SQLException                   system generated SQL exception.
	 */
	public static Doctor createDoctor(Doctor doctor) throws UsernameAlreadyExistsException, SQLException {
		LOGGER.traceEntry(doctor.toString());
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
				// FIX : ADD A NULL CHECK - DONE
				if (generatedResultSet != null && generatedResultSet.next()) {
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
					LOGGER.traceExit(doctor.toString());
					return doctor;
				} else {
					connection.rollback();
				}
			} else {
				connection.rollback();
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			LOGGER.error(MESSAGE_BUNDLE.getString("HMS4001E"), e.getMessage());
			throw new UsernameAlreadyExistsException("User with username already existd");
		} catch (SQLException e) {
			LOGGER.error(MESSAGE_BUNDLE.getString("HMS4001E"), e.getMessage());
			throw new SQLException("SQL server error");
		} catch (Exception e) {
			LOGGER.error(MESSAGE_BUNDLE.getString("HMS4001E"), e.getMessage());
			throw new SQLException("SQL server error" + e.getMessage());
		} finally {
			LOGGER.traceExit();
			if (connection != null) {
				dbConfig.closeConnection();
			}
		}
		return null;
	}

	/**
	 * Gets the List of Doctors from the Database.
	 *
	 * @return list of Doctors from the database.
	 * @throws UserNotFoundException Custom Exception for no user with that id.
	 * @throws SQLException          SQL server error.
	 */
	public static List<Doctor> readDoctors() throws SQLException {
		LOGGER.traceEntry();
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

			while (resultSet != null && resultSet.next()) {
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
			LOGGER.traceExit(doctors.toString());
			return doctors;
		} catch (SQLException e) {
			LOGGER.error(MESSAGE_BUNDLE.getString("HMS4001E"), e.getMessage());
			throw new SQLException("SQL Error: " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error(MESSAGE_BUNDLE.getString("HMS4001E"), e.getMessage());
			throw new SQLException("System Error: " + e.getMessage());
		} finally {
			LOGGER.traceExit();
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
	 * @throws SQLException          Exception thrown by SQL.
	 */
	public static Doctor readDoctor(int id) throws UserNotFoundException, SQLException {
		LOGGER.traceEntry(Integer.toString(id));
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

			// FIX : ADD LOGIC BASED CHECKS IN HELPER
			if (resultSet != null && resultSet.next()) {
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
			LOGGER.traceExit(doctor.toString());
			return doctor;
		} catch (SQLException e) {
			LOGGER.error(MESSAGE_BUNDLE.getString("HMS4001E"), e.getMessage());
			throw new SQLException("SQL Error " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error(MESSAGE_BUNDLE.getString("HMS4001E"), e.getMessage());
			throw new SQLException("System Error " + e.getMessage());
		} finally {
			LOGGER.traceExit();
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
	 * @throws SQLException system generated SQL exception.
	 */
	public static boolean deleteDoctor(int id) throws SQLException {
		LOGGER.traceEntry(Integer.toString(id));
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
					LOGGER.traceExit(true);
					return true;
				} else {
					connection.rollback();
					return false;
				}
			} else {
				connection.rollback();
			}

		} catch (SQLException e) {
			throw new SQLException("SQL Error: " + e.getMessage());
		} catch (Exception e) {
			throw new SQLException("SQL Error: " + e.getMessage());
		} finally {
			LOGGER.traceExit();
			if (connection != null) {
				dbConfig.closeConnection();
			}
		}
		return false;
	}

	/**
	 *
	 * @param id of the doctor.
	 * @return List of patients for that doctor.
	 * @throws SQLException SQL server error.
	 */
	public static List<Patient> getPatientsForDoctor(int id) throws SQLException {
		LOGGER.traceEntry(Integer.toString(id));
		DbConfig dbConfig = null;
		Connection connection = null;
		ResultSet resultSet;
		PreparedStatement preparedStatement;
		List<Patient> patientsForDoctor = new ArrayList<Patient>();
		Patient patient = null;

		try {
			dbConfig = new DbConfig();
			connection = dbConfig.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(QueryConstants.PATIENTS_FOR_DOCTOR);
			preparedStatement.setInt(NumberConstants.ONE, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet != null && resultSet.next()) {

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
				patientsForDoctor.add(patient);

			}
			if (connection != null) {
				dbConfig.closeConnection();
			}
			LOGGER.traceExit(patientsForDoctor.toString());
			return patientsForDoctor;
		} catch (SQLException e) {
			LOGGER.error(MESSAGE_BUNDLE.getString("HMS4001E"), e.getMessage());
			throw new SQLException("SQL Error: " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error(MESSAGE_BUNDLE.getString("HMS4001E"), e.getMessage());
			throw new SQLException("SQL Error: " + e.getMessage());
		} finally {
			LOGGER.traceExit();
			if (connection != null) {
				dbConfig.closeConnection();
			}
		}
	}

	/**
	 *
	 * @return Map of patients to doctors.
	 * @throws SQLException System exception.
	 */
	public static Map<Integer, List<Patient>> getPatientsForAllDoctors() throws SQLException {
		LOGGER.traceEntry();
		DbConfig dbConfig = null;
		Connection connection = null;
		ResultSet resultSet;
		PreparedStatement preparedStatement;
		int doctorId = -1;
		Map<Integer, List<Patient>> doctorPatientsMap = new HashMap<Integer, List<Patient>>();
		Patient patient = null;

		try {
			dbConfig = new DbConfig();
			connection = dbConfig.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(QueryConstants.PATIENTS_FOR_DOCTORS);

			resultSet = preparedStatement.executeQuery();

			while (resultSet != null && resultSet.next()) {
				doctorId = resultSet.getInt(QueryConstants.FK_DOCTOR_ID);
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
				if (doctorPatientsMap.containsKey(doctorId)) {
					doctorPatientsMap.get(doctorId).add(patient);
				} else {
					doctorPatientsMap.put(doctorId, new ArrayList<Patient>());
					doctorPatientsMap.get(doctorId).add(patient);
				}
			}
			if (connection != null) {
				dbConfig.closeConnection();
			}
			LOGGER.traceExit(doctorPatientsMap.toString());
			return doctorPatientsMap;
		} catch (SQLException e) {
			LOGGER.error(MESSAGE_BUNDLE.getString("HMS4001E"), e.getMessage());
			throw new SQLException("SQL Error: " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error(MESSAGE_BUNDLE.getString("HMS4001E"), e.getMessage());
			throw new SQLException("SQL Error: " + e.getMessage());
		} finally {
			LOGGER.traceExit();
			if (connection != null) {
				dbConfig.closeConnection();
			}
		}
	}
}
