package com.hms.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hms.constants.QueryConstants;

/**
 * This Class is used to create a connection to Database using JDBC.
 *
 * @author Rahul
 *
 */
public class DbConfig {
	private Connection connection;
	private static final Logger LOGGER = LogManager.getLogger(DbConfig.class);

	/**
	 * The Constructor of this class creates a connection after calling the class.
	 *
	 * @throws SQLException by the server.
	 *
	 */
	public DbConfig() throws SQLException {
		connection = DriverManager.getConnection(QueryConstants.JDBC_CONNECTION, QueryConstants.JDBC_USERNAME,
				QueryConstants.JDBC_PASSWORD);
	}

	/**
	 * Returns the Database Connection Object.
	 *
	 * @return Connection Object.
	 */
	public Connection getConnection() {
		LOGGER.info("Created Connection");
		return connection;
	}

	/**
	 * Closes the opened Connection with the Database.
	 *
	 * @throws SQLException by the server.
	 *
	 */
	public void closeConnection() {
		try {
			LOGGER.info("Closed Database Connection");
			connection.close();
		} catch (SQLException e) {
			LOGGER.error("Error while Closing the connection" + e.getMessage());
		}
	}
}
