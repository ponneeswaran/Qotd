/**
 * 
 */
package com.awqotd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.awqotd.vo.Node;
import com.awqotd.vo.OptionsVO;
import com.awqotd.vo.QueriesVO;
import com.awqotd.vo.QuestionDetailsVO;
import com.awqotd.vo.QuizDetailsVO;
import com.awqotd.vo.ScheduleVO;
import com.awqotd.vo.UserDetailsVO;

/**
 * @author Ponneeswaran
 *
 */
@Component
public class DataAccessor
{
	@Autowired
	DataSource dataSource;
	/*
	 * Funtion to Signup new User
	 */
	public void userSignUp(UserDetailsVO userDetails) throws Exception
	{
		Connection conn = dataSource.getConnection();
		PreparedStatement statement=conn.prepareStatement(QueriesVO.USER_SIGN_UP_CHECK);
		statement.setString(1, userDetails.getEmailId());
		ResultSet rs = statement.executeQuery();
		while(rs.next())
		{
			if(rs.getInt("NUM")>=1)
			{
				throw new Exception("User already Exists!");
			}
		}
		statement=conn.prepareStatement(QueriesVO.USER_SIGN_UP);
		statement.setString(1, userDetails.getPassword());
		statement.setString(2, userDetails.getEmailId());
		statement.executeUpdate();
		conn.close();
	}

	public UserDetailsVO userLogin(UserDetailsVO userDetails) throws Exception 
	{
		Connection conn = dataSource.getConnection();
		PreparedStatement statement=conn.prepareStatement(QueriesVO.USER_LOGIN);
		statement.setString(1, userDetails.getPassword());
		statement.setString(2, userDetails.getEmailId());
		ResultSet rs = statement.executeQuery();
		if(rs.last())
		{
			if(rs.getRow()==1)
			{
				userDetails.setRoleId(rs.getInt("ROLE_ID"));
				userDetails.setErrorMessage("Login Success");
				userDetails.setErrorStatus(true);				
			}
			else
			{
				userDetails.setErrorMessage("Username or Password is wrong!");
				userDetails.setErrorStatus(false);
			}
		}
		conn.close();
		return userDetails;
	}

	public List<QuizDetailsVO> scheduledQuizzes(UserDetailsVO userDetails) throws Exception{
		
		List<QuizDetailsVO> qDetailsVO = new ArrayList<QuizDetailsVO>();
		Connection conn = dataSource.getConnection();
		PreparedStatement statement=conn.prepareStatement(QueriesVO.SCHEDULED_QUIZ);
		statement.setString(1, userDetails.getEmailId());
		ResultSet rs = statement.executeQuery();
		while(rs.next())
		{
			QuizDetailsVO qVO = new QuizDetailsVO();
			qVO.setQuiz_id(rs.getInt("QUIZ_ID"));
			qVO.setQuiz(rs.getString("QUIZ_NAME"));
			qVO.setSubscriptions(rs.getInt("SUBSCRIPTIONS"));
			qDetailsVO.add(qVO);
		}
		conn.close();
		return qDetailsVO;
		
	}

	public List<QuestionDetailsVO> getQuestions(QuizDetailsVO qDetailsVO) throws Exception {
		List<QuestionDetailsVO> quesDetailsVO = new ArrayList<QuestionDetailsVO>();
		Connection conn = dataSource.getConnection();
		PreparedStatement statement=conn.prepareStatement(QueriesVO.QUIZ_QUESTIONS);
		statement.setString(1, Integer.toString(qDetailsVO.getQuiz_id()));
		ResultSet rs = statement.executeQuery();
		while(rs.next())
		{
			QuestionDetailsVO qVO = new QuestionDetailsVO();
			qVO.setQuestion_id(rs.getInt("QUESTION_ID"));
			qVO.setQuestion_text(rs.getString("QUESTION_TEXT"));
			qVO.setCreated_date(rs.getString("CREATED_DATE"));
			qVO.setSubmissions(rs.getString("SUBMISSIONS"));
			quesDetailsVO.add(qVO);
		}
		conn.close();
		
		return quesDetailsVO;
	}

	public List<String> getJavaTopics() throws Exception {
		List<String> node = new ArrayList<String>();
		Connection conn = dataSource.getConnection();
		PreparedStatement statement=conn.prepareStatement(QueriesVO.JAVA_TOPICS);
		ResultSet rs = statement.executeQuery();
		while(rs.next())
		{
			node.add(rs.getString("LIST_OBJ"));
		}
		conn.close();
		return node;
	}

	public List<QuestionDetailsVO> getQuestionsonTopic(Node node) throws Exception{
		List<QuestionDetailsVO> quesDetailsVO = new ArrayList<QuestionDetailsVO>();
		Connection conn = dataSource.getConnection();
		PreparedStatement statement=conn.prepareStatement(QueriesVO.TOPIC_QUESTIONS+node.getTagName()+"%'");
		ResultSet rs = statement.executeQuery();
		while(rs.next())
		{
			QuestionDetailsVO qVO = new QuestionDetailsVO();
			qVO.setQuestion_id(rs.getInt("QUESTION_ID"));
			qVO.setQuestion_text(rs.getString("QUESTION_TEXT"));
			quesDetailsVO.add(qVO);
		}
		conn.close();
		
		return quesDetailsVO;
	}

	public void scheduleQuiz(ScheduleVO scheduleVO) throws Exception {
		Connection conn = dataSource.getConnection();
		PreparedStatement statement;
		ResultSet rs;
		if(scheduleVO.getQuiz_id()==0){
			statement=conn.prepareStatement(QueriesVO.GET_AUTO_INCREMENT);
			statement.setString(1, "quiz");
			rs = statement.executeQuery();
			while(rs.last()){
				scheduleVO.setQuiz_id(rs.getInt("ID"));
			}
			statement=conn.prepareStatement(QueriesVO.QUIZ_INSERT);
			statement.setInt(1, scheduleVO.getQuiz_id());
			statement.setString(2, scheduleVO.getQuiz());
			statement.setString(3, scheduleVO.getEmailId());
			statement.setString(4, scheduleVO.getCreatedDate());
			statement.executeUpdate();
		}
		statement=conn.prepareStatement(QueriesVO.GET_AUTO_INCREMENT);
		statement.setString(1, "questions");
		rs = statement.executeQuery();
		while(rs.last()){
			scheduleVO.setQuestionId(rs.getInt("ID"));
		}
		statement=conn.prepareStatement(QueriesVO.QUESTIONS_INSERT);
		statement.setInt(1, scheduleVO.getQuestionId());
		statement.setString(2, scheduleVO.getQuestion());
		statement.setString(3, scheduleVO.getEmailId());
		statement.setString(4, scheduleVO.getCreatedDate());
		statement.setString(5, scheduleVO.getQuestionTag());
		statement.executeUpdate();
		
		statement=conn.prepareStatement(QueriesVO.EXPIRY_INSERT);
		statement.setInt(1, scheduleVO.getQuiz_id());
		statement.setInt(2, scheduleVO.getQuestionId());
		statement.setString(3, scheduleVO.getExpiryDate());
		statement.setString(4, scheduleVO.getCreatedDate());
		statement.executeUpdate();
		
		for(OptionsVO oVO : scheduleVO.getOptions()){
			statement=conn.prepareStatement(QueriesVO.GET_AUTO_INCREMENT);
			statement.setString(1, "options");
			rs = statement.executeQuery();
			int optionId =0;
			while(rs.last()){
				optionId = rs.getInt("ID");
			}
			statement=conn.prepareStatement(QueriesVO.OPTIONS_INSERT);
			statement.setInt(1, optionId);
			statement.setInt(2, scheduleVO.getQuestionId());
			statement.setString(3, oVO.getOptionText());
			statement.setString(4, oVO.getOptTag());
			statement.setInt(5, oVO.getCorrectness());
			statement.executeUpdate();
		}
		
		conn.close();
	}
}
