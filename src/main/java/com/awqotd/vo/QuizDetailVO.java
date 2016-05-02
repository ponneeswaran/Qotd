package com.awqotd.vo;

import java.util.ArrayList;
import java.util.List;

public class QuizDetailVO 
{
	private int quiz_id;
	private String quiz_name;
	private List<UserDetailsVO> mailingList;
	private QuestionDetailVO question;
	public QuizDetailVO()
	{
		mailingList = new ArrayList<UserDetailsVO>();
	}
	public int getQuiz_id() {
		return quiz_id;
	}
	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}
	public QuestionDetailVO getQuestion() {
		return question;
	}
	public void setQuestion(QuestionDetailVO question) {
		this.question = question;
	}
	public void addUserDetailVO(UserDetailsVO userDetailsVO)
	{
		mailingList.add(userDetailsVO);
	}
	public List<UserDetailsVO> getMailingList() {
		return mailingList;
	}
	public void setMailingList(List<UserDetailsVO> mailingList) {
		this.mailingList = mailingList;
	}
	public String getQuiz_name() {
		return quiz_name;
	}
	public void setQuiz_name(String quiz_name) {
		this.quiz_name = quiz_name;
	}
}