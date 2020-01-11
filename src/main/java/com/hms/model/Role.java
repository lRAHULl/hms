package com.hms.model;

import java.util.Date;

/**
 *
 * @author Rahul
 *
 */
public class Role {
	private int roleId;
	private String roleName;
	private int isActive;
	private Date createdDate;
	private Date updatedDate;

	/**
	 * Sets new updatedDate.
	 *
	 * @param updatedDate New value of updatedDate.
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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
	 * Sets new createdDate.
	 *
	 * @param createdDate New value of createdDate.
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Sets new roleName.
	 *
	 * @param roleName New value of roleName.
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * Gets roleId.
	 *
	 * @return Value of roleId.
	 */
	public int getRoleId() {
		return roleId;
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
	 * Sets new roleId.
	 *
	 * @param roleId New value of roleId.
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets roleName.
	 *
	 * @return Value of roleName.
	 */
	public String getRoleName() {
		return roleName;
	}
}
