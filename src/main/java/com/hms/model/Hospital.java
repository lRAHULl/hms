package com.hms.model;

import java.util.Date;

/**
 *
 * @author Rahul
 *
 */
public class Hospital {
	private int hospitalId;
	private String hospitalName;
	private int isActive;
	private Date createdDate;
	private Date updatedDate;

	/**
	 * @return String of all the attributes in the Bean.
	 */
	@Override
	public String toString() {
		return "Hospital{" + "hospitalId=" + hospitalId + ", hospitalName='" + hospitalName + '\'' + ", isActive="
				+ isActive + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + '}';
	}

	/**
	 * Sets new hospitalName.
	 *
	 * @param hospitalName New value of hospitalName.
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	/**
	 * Sets new createdDate.
	 *
	 * @param createdDate New value of createdDate.
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Sets new hospitalId.
	 *
	 * @param hospitalId New value of hospitalId.
	 */
	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	/**
	 * Gets updatedDate.
	 *
	 * @return Value of updatedDate.
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * Gets createdDate.
	 *
	 * @return Value of createdDate.
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Gets isActive.
	 *
	 * @return Value of isActive.
	 */
	public int getIsActive() {
		return isActive;
	}

	/**
	 * Sets new isActive.
	 *
	 * @param isActive New value of isActive.
	 */
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets hospitalId.
	 *
	 * @return Value of hospitalId.
	 */
	public int getHospitalId() {
		return hospitalId;
	}

	/**
	 * Gets hospitalName.
	 *
	 * @return Value of hospitalName.
	 */
	public String getHospitalName() {
		return hospitalName;
	}

	/**
	 * Sets new updatedDate.
	 *
	 * @param updatedDate New value of updatedDate.
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
