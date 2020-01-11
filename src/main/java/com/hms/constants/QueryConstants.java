package com.hms.constants;

/**
 *
 * All the Queries done to the database from the DAO come here.
 *
 * @author Rahul.
 *
 */
public final class QueryConstants {

	/**
	 * Utility classes should have private constructors.
	 */
	private QueryConstants() {
	}

	public static final String USER_INSERT_QUERY = "insert into t_user (username,password,fk_role_id,first_name,last_name,age) values(?,?,?,?,?,?)";
	public static final String PATIENT_INSERT_QUERY = "insert into t_patient (fk_user_id,patient_height,patient_weight,door_no,street,city,blood_group) values(?,?,?,?,?,?,?)";
}
