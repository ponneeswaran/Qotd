package com.awqotd.vo;

public class SubAnsResVO 
{
	private int status;
	private int quiz_id;
	private int question_id;
	private int user_id;
	private String associated_tags;
	private double option_correctness;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getQuiz_id() {
		return quiz_id;
	}
	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAssociated_tags() {
		return associated_tags;
	}
	public void setAssociated_tags(String associated_tags) {
		this.associated_tags = associated_tags;
	}
	public double getOption_correctness() {
		return option_correctness;
	}
	public void setOption_correctness(double option_correctness) {
		this.option_correctness = option_correctness;
	}
}
