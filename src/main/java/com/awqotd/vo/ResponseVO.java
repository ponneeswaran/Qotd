/**
 * 
 */
package com.awqotd.vo;

/**
 * @author Ponneeswaran
 *
 */
public class ResponseVO {
	private String errorMessage;
	private boolean errorStatus;

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorStatus
	 */
	public boolean getErrorStatus() {
		return errorStatus;
	}

	/**
	 * @param errorStatus the errorStatus to set
	 */
	public void setErrorStatus(boolean errorStatus) {
		this.errorStatus = errorStatus;
	}
}
