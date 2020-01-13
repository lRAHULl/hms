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

	public static final String USER_INSERT_QUERY = "INSERT INTO t_user (username,password,fk_role_id,first_name,last_name,age) VALUES (?,?,?,?,?,?)";
	public static final String PATIENT_INSERT_QUERY = "INSERT INTO t_patient (fk_user_id,patient_height,patient_weight,door_no,street,city,blood_group) VALUES (?,?,?,?,?,?,?)";
	public static final String DOCTOR_INSERT_QUERY = "INSERT INTO t_doctor (fk_user_id,doctor_specialisation,experience) VALUES (?,?,?)";

	public static final String PATIENT_READ_QUERY = "SELECT * FROM t_user JOIN t_patient ON t_user.pk_user_id=t_patient.fk_user_id "
			+ "WHERE pk_user_id=? AND t_user.is_deleted=0 AND fk_role_id=1";
	public static final String DOCTOR_READ_QUERY = "SELECT * FROM t_user JOIN t_doctor ON t_user.pk_user_id=t_doctor.fk_user_id "
			+ "WHERE pk_user_id=? AND t_user.is_deleted=0 AND fk_role_id=2";

	public static final String PATIENTS_READ_QUERY = "SELECT * FROM t_user JOIN t_patient ON t_user.pk_user_id=t_patient.fk_user_id WHERE t_user.is_deleted=0 AND fk_role_id=1";
	public static final String DOCTORS_READ_QUERY = "SELECT * FROM t_user JOIN t_doctor ON t_user.pk_user_id=t_doctor.fk_user_id WHERE t_user.is_deleted=0 AND fk_role_id=2";

	public static final String USER_UPDATE_QUERY = "UPDATE t_user SET password=?,age=? WHERE pk_user_id=? AND is_deleted=0";
	public static final String PATIENT_UPDATE_QUERY = "UPDATE t_patient SET patient_height=?,patient_weight=? WHERE fk_user_id=? AND is_deleted=0";
	public static final String DOCTOR_UPDATE_QUERY = "UPDATE t_doctor SET doctor_specialisation=?,experience=? WHERE fk_user_id=? AND is_deleted=0";

	public static final String USER_DELETE_QUERY = "UPDATE t_user SET is_deleted=1 WHERE pk_user_id=? AND is_deleted=0";
	public static final String PATIENT_DELETE_QUERY = "UPDATE t_patient SET is_deleted=1 WHERE fk_user_id=? AND is_deleted=0";
	public static final String DOCTOR_DELETE_QUERY = "UPDATE t_doctor SET is_deleted=1 WHERE fk_user_id=? AND is_deleted=0";

	public static final String MAP_DOCTOR_FOR_PATIENT = "select user.pk_user_id,user.username,user.password,user.first_name,user.last_name,user.age"
			+ ",patient.patient_height,patient.patient_weight,patient.door_no,patient.street, patient.city, patient.blood_group from t_user JOIN t_patient"
			+ " ON t_user.pk_user_id=t_patient.fk_user_id "
			+ "INNER JOIN t_patient_doctor_mapping as mapping where user.is_deleted=0 and mapping.is_deleted=0 and user.fk_role_id=1 and mapping.fk_doctor_id=?";

	public static final String PATIENT_IDS_FOR_A_GIVEN_DOCTOR = "SELECT fk_patient_id from t_patient_doctor_mapping WHERE fk_doctor_id = ?";
	public static final String PATIENTS_WITH_IDS = "SELECT * FROM t_user JOIN t_patient ON t_user.pk_user_id=t_patient.fk_user_id "
			+ "WHERE t_user.is_deleted=0 AND fk_role_id=1 AND t_user.pk_user_id IN (?)";

	public static final String PATIENTS_FOR_DOCTORS = "select * from t_user join t_patient_doctor_mapping on t_user.pk_user_id=t_patient_doctor_mapping.fk_patient_id "
			+ "join t_user as u on t_patient_doctor_mapping.fk_doctor_id=u.pk_user_id where u.is_deleted=0";

	public static final String JDBC_CONNECTION = "jdbc:mysql://localhost:3306/hospital";
	public static final String JDBC_USERNAME = "root";
	public static final String JDBC_PASSWORD = "root";

	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String PK_USER_ID = "pk_user_id";
	public static final String FK_ROLE_ID = "fk_role_id";
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	public static final String AGE = "age";

	public static final String FK_DOCTOR_ID = "fk_doctor_id";
	public static final String PK_PATIENT_ID = "pk_patient_id";
	public static final String PATIENT_HEIGHT = "patient_height";
	public static final String PATIENT_WEIGHT = "patient_weight";
	public static final String DOOR_NO = "door_no";
	public static final String STREET = "street";
	public static final String CITY = "city";
	public static final String BLOOD_GROUP = "blood_group";

	public static final String PK_DOCTOR_ID = "pk_doctor_id";
	public static final String SPECIALIZATION = "doctor_specialisation";
	public static final String EXPERIENCE = "experience";
	public static final String FK_PATIENT_ID = "fk_patient_id";

}
