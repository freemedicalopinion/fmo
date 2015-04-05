package com.healthcare.freemedicalopinion.valueobject;

import java.io.Serializable;

public class BaseResponseVO implements Serializable {

	public BaseResponseVO() {

	}

	public BaseResponseVO(HTTP_RESPONSE response) {
		this.responseStatus = response;
	}

	public BaseResponseVO(HTTP_RESPONSE response, String errorMessage) {
		this.responseStatus = response;
		this.errorMessage = errorMessage;
	}

	private static final long serialVersionUID = -5454124553456642672L;

	public enum HTTP_RESPONSE {
		SUCCESS, FAIL;
	}

	private HTTP_RESPONSE responseStatus;
	private String errorMessage;
	private String message;

	public HTTP_RESPONSE getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(HTTP_RESPONSE responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
