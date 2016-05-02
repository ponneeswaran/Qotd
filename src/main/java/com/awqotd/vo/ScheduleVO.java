/**
 * 
 */
package com.awqotd.vo;

import java.util.List;

/**
 * @author Ponneeswaran
 *
 */
public class ScheduleVO {
	private String emailId;
	private int quiz_id;
	private String quiz;
	private int questionId;
	private String question;
	private String questionTag;
	private String createdDate;
	private String expiryDate;
	private List<OptionsVO> options;
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
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
	 * @return the questionId
	 */
	public int getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @return the questionTag
	 */
	public String getQuestionTag() {
		return questionTag;
	}
	/**
	 * @param questionTag the questionTag to set
	 */
	public void setQuestionTag(String questionTag) {
		this.questionTag = questionTag;
	}
	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the expiryDate
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return the options
	 */
	public List<OptionsVO> getOptions() {
		return options;
	}
	/**
	 * @param options the options to set
	 */
	public void setOptions(List<OptionsVO> options) {
		this.options = options;
	}
}
