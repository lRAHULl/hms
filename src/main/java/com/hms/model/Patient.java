package com.hms.model;

/**
 * This is a Bean class used to represent the Patient.
 *
 * @author Rahul.
 *
 */
public class Patient extends User {
	private int patientId;
	private int patientHeight;
	private int patientWeight;
	private String street;
	private String city;
	private String doorNo;
	private String bloodGroup;

	/**
	 * Gets the patientId.
	 *
	 * @return patientId of the patient.
	 */
	public int getPatientId() {
		return patientId;
	}

	/**
	 * Sets the patientId.
	 *
	 * @param patientId Integer.
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	/**
	 * Gets the patientHeight.
	 *
	 * @return patientHeight of the patient.
	 */
	public int getPatientHeight() {
		return patientHeight;
	}

	/**
	 * Sets the patientHeight.
	 *
	 * @param patientHeight Integer.
	 */
	public void setPatientHeight(int patientHeight) {
		this.patientHeight = patientHeight;
	}

	/**
	 * Gets the patientWeight.
	 *
	 * @return patientWeight of the patient.
	 */
	public int getPatientWeight() {
		return patientWeight;
	}

	/**
	 * Sets the patientWeight.
	 *
	 * @param patientWeight Integer.
	 */
	public void setPatientWeight(int patientWeight) {
		this.patientWeight = patientWeight;
	}

	/**
	 * Gets the street.
	 *
	 * @return street of the patient.
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street String.
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the city.
	 *
	 * @return city of the patient.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city String.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the doorNo.
	 *
	 * @return doorNo of the patient.
	 */
	public String getDoorNo() {
		return doorNo;
	}

	/**
	 * Sets the doorNo.
	 *
	 * @param doorNo String.
	 */
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	/**
	 * Gets the bloodGroup.
	 *
	 * @return bloodGroup of the patient.
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * Sets the bloodGroup.
	 *
	 * @param bloodGroup String.
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * Returns the patient object as a string.
	 *
	 * @return Patient as a string.
	 */
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientHeight=" + patientHeight + ", patientWeight="
				+ patientWeight + ", street=" + street + ", city=" + city + ", doorNo=" + doorNo + ", bloodGroup="
				+ bloodGroup + ", getPatientId()=" + getPatientId() + ", getPatientHeight()=" + getPatientHeight()
				+ ", getPatientWeight()=" + getPatientWeight() + ", getStreet()=" + getStreet() + ", getCity()="
				+ getCity() + ", getDoorNo()=" + getDoorNo() + ", getBloodGroup()=" + getBloodGroup() + ", getUserId()="
				+ getUserId() + ", getRoleId()=" + getRoleId() + ", getUsername()=" + getUsername() + ", getPassword()="
				+ getPassword() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
				+ ", getAge()=" + getAge() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
