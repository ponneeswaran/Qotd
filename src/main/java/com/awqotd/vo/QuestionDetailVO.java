package com.awqotd.vo;

import java.util.ArrayList;
import java.util.List;

public class QuestionDetailVO 
{
	private int question_id;
	private String questionText;
	private List<OptionDetailVO> options;
	public QuestionDetailVO()
	{
		options = new ArrayList<OptionDetailVO>();
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public List<OptionDetailVO> getOptions() {
		return options;
	}
	public void setOptions(List<OptionDetailVO> options) {
		this.options = options;
	}
	public void addOptions(OptionDetailVO input)
	{
		this.options.add(input);
	}
}
