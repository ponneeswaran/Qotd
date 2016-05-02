/**
 * 
 */
package com.awqotd.vo;

/**
 * @author Ponneeswaran
 *
 */
public class QuizDetailsVO {
	private int quiz_id;
	private String quiz;
	private String created_date;
	private int subscriptions;
	private String errorMessage;
	private boolean errorStatus;
	/**
	 * @return the quiz_id
	 */
	public int getQuiz_id() {
		return quiz_id;
	}
	/**
	 * @param quiz_id the quiz_id to set
	 */
	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}
	/**
	 * @return the quiz
	 */
	public String getQuiz() {
		return quiz;
	}
	/**
	 * @param quiz the quiz to set
	 */
	public void setQuiz(String quiz) {
		this.quiz = quiz;
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
	public int getSubscriptions() {
		return subscriptions;
	}
	/**
	 * @param subscriptions the subscriptions to set
	 */
	public void setSubscriptions(int subscriptions) {
		this.subscriptions = subscriptions;
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
