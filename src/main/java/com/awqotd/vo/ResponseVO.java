/**
 * 
 */
package com.awqotd.vo;

import java.util.List;

/**
 * @author Ponneeswaran
 *
 */
public class ResponseVO {
	private List<QuizDetailsVO> qDetails;
	private List<QuestionDetailsVO> quesDetails;
	private List<String> list_obj;
	private String errorMessage;
	private boolean errorStatus;

	/**
	 * @return the qDetails
	 */
	public List<QuizDetailsVO> getqDetails() {
		return qDetails;
	}

	/**
	 * @param qDetails the qDetails to set
	 */
	public void setqDetails(List<QuizDetailsVO> qDetails) {
		this.qDetails = qDetails;
	}

	/**
	 * @return the quesDetails
	 */
	public List<QuestionDetailsVO> getQuesDetails() {
		return quesDetails;
	}

	/**
	 * @param quesDetails the quesDetails to set
	 */
	public void setQuesDetails(List<QuestionDetailsVO> quesDetails) {
		this.quesDetails = quesDetails;
	}

	/**
	 * @return the list_obj
	 */
	public List<String> getList_obj() {
		return list_obj;
	}

	/**
	 * @param list_obj the list_obj to set
	 */
	public void setList_obj(List<String> list_obj) {
		this.list_obj = list_obj;
	}

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
