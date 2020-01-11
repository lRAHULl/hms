package com.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private static final Logger LOGGER = LoggerFactory.getLogger(PatientDao.class);

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

}
