package com.hms.model;

/**
 *
 * This Class is used to generate a Custom Response for the Service Layer.
 *
 * @author Rahul
 *
 */
public class ResponseBody {
	private int status;
	private Object data;

	/**
	 * Gets the Status.
	 *
	 * @return status.
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Sets the status code of the response.
	 *
	 * @param status Integer.
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Gets the data.
	 *
	 * @return data.
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Sets the data of the response.
	 *
	 * @param data Object.
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * Returns the response object as a string.
	 *
	 * @return Response as a string.
	 */
	@Override
	public String toString() {
		return "CustomResponse [status=" + status + ", data=" + data + ", getStatus()=" + getStatus() + ", getData()="
				+ getData() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
