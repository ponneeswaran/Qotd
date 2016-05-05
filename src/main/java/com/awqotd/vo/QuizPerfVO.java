/**
 * 
 */
package com.awqotd.vo;

/**
 * @author Ponneeswaran
 *
 */
public class QuizPerfVO {
	private int question_id;
	private int option_id;
	private int total;
	private int student;
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
	 * @return the option_id
	 */
	public int getOption_id() {
		return option_id;
	}
	/**
	 * @param option_id the option_id to set
	 */
	public void setOption_id(int option_id) {
		this.option_id = option_id;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return the student
	 */
	public int getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(int student) {
		this.student = student;
	}
	
}
