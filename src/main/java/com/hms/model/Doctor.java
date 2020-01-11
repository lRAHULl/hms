package com.hms.model;

/**
 * This is a Bean class used to represent the Doctor.
 *
 * @author Rahul
 */
public class Doctor extends User {
	private int doctorId;
	private int experience;
	private String specilization;

	/**
	 * Gets the doctorId.
	 *
	 * @return doctorId.
	 */
	public int getDoctorId() {
		return doctorId;
	}

	/**
	 * Sets the doctorId.
	 *
	 * @param doctorId Integer.
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * Gets the experience.
	 *
	 * @return experience.
	 */
	public int getExperience() {
		return experience;
	}

	/**
	 * Sets the experience.
	 *
	 * @param experience Integer.
	 */
	public void setExperience(int experience) {
		this.experience = experience;
	}

	/**
	 * Gets the specilization.
	 *
	 * @return specilization.
	 */
	public String getSpecilization() {
		return specilization;
	}

	/**
	 * Sets the specilization.
	 *
	 * @param specilization String.
	 */
	public void setSpecilization(String specilization) {
		this.specilization = specilization;
	}

	/**
	 * Returns the Doctor object as a string.
	 *
	 * @return Doctor as a string.
	 */
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", experience=" + experience + ", specilization=" + specilization
				+ ", getDoctorId()=" + getDoctorId() + ", getExperience()=" + getExperience() + ", getSpecilization()="
				+ getSpecilization() + ", getUserId()=" + getUserId() + ", getRoleId()=" + getRoleId()
				+ ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + ", getAge()=" + getAge() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
