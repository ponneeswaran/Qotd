/**
 * 
 */
package com.awqotd.vo;

/**
 * @author Ponneeswaran
 *
 */
public class QuestionDetailsVO {
	private int question_id;
	private String question_text;
	private String created_date;
	private String submissions;
	private String errorMessage;
	private boolean errorStatus;
	/**
	 * @return the question_id
	 */
	public int getQuestion_id() {
		return question_id;
	}
	/**
	 * @param question_id the question_id to set
	 */
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	/**
	 * @return the question_text
	 */
	public String getQuestion_text() {
		return question_text;
	}
	/**
	 * @param question_text the question_text to set
	 */
	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}
	/**
	 * @return the created_date
	 */
	public String getCreated_date() {
		return created_date;
	}
	/**
	 * @param created_date the created_date to set
	 */
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	/**
	 * @return the subscriptions
	 */
	public String getSubmissions() {
		return submissions;
	}
	/**
	 * @param subscriptions the subscriptions to set
	 */
	public void setSubmissions(String submissions) {
		this.submissions = submissions;
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
	public boolean isErrorStatus() {
		return errorStatus;
	}
	/**
	 * @param errorStatus the errorStatus to set
	 */
	public void setErrorStatus(boolean errorStatus) {
		this.errorStatus = errorStatus;
	}
}
