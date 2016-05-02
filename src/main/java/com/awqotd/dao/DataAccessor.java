/**
 * 
 */
package com.awqotd.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.awqotd.vo.Edge;
import com.awqotd.vo.Node;
import com.awqotd.vo.OptionDetailVO;
import com.awqotd.vo.QueriesVO;
import com.awqotd.vo.QuestionDetailVO;
import com.awqotd.vo.QuizDetailVO;
import com.awqotd.vo.SubAnsResVO;
import com.awqotd.vo.UserDetailsVO;
import com.awqotd.vo.UserKnowledgeVO;

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
	public int getUniqueId(int user_id, int quiz_id, int question_id)
	{
		Connection conn = null;
		CallableStatement cStmt = null;
		int result=0;
		try 
		{
			conn = dataSource.getConnection();
			cStmt = conn.prepareCall(QueriesVO.GET_UNIQUE_ID);
			cStmt.registerOutParameter(4, Types.INTEGER);
			cStmt.setInt(1, user_id);
			cStmt.setInt(2, question_id);
			cStmt.setInt(3, quiz_id);
			cStmt.execute();
			result = cStmt.getInt(4);
			System.out.println("Unique Id for user_id: "+user_id+" and quiz_id: "+quiz_id+" is "+result);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(cStmt!=null)
			{
				try {cStmt.close();} catch (SQLException e) {e.printStackTrace();}
			}
			if(conn!=null)
			{
				try{conn.close();}catch (SQLException e){e.printStackTrace();}
			}
		}
		return result;
	}
	public SubAnsResVO updateStudentResponse(int unique_id, int option_id)
	{
		SubAnsResVO result = null;
		Connection conn = null;
		CallableStatement cStmt = null;
		try 
		{
			conn = dataSource.getConnection();
			cStmt = conn.prepareCall(QueriesVO.UPDATE_STUDENT_RESPONSE);
			cStmt.registerOutParameter(3, Types.INTEGER);
			cStmt.registerOutParameter(4, Types.INTEGER);
			cStmt.registerOutParameter(5, Types.INTEGER);
			cStmt.registerOutParameter(6, Types.INTEGER);
			cStmt.setInt(1, unique_id);
			cStmt.setInt(2, option_id);
			cStmt.execute();
			result = new SubAnsResVO();
			result.setStatus(cStmt.getInt(3));
			result.setUser_id(cStmt.getInt(4));
			result.setQuestion_id(cStmt.getInt(5));
			result.setQuiz_id(cStmt.getInt(6));
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(cStmt!=null)
			{
				try {cStmt.close();} catch (SQLException e) {e.printStackTrace();}
			}
			if(conn!=null)
			{
				try{conn.close();}catch (SQLException e){e.printStackTrace();}
			}
		}
		return result;
	}
	public List<UserDetailsVO> getQuizUsers(int quiz_id)
	{
		List<UserDetailsVO> result = new ArrayList<UserDetailsVO>();
		Connection conn = null;
		PreparedStatement statement=null;
		try 
		{
			conn=dataSource.getConnection();
			statement=conn.prepareStatement(QueriesVO.GET_USER_LIST);
			statement.setInt(1, quiz_id);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				UserDetailsVO temp = new UserDetailsVO();
				temp.setUserId(rs.getInt("user_id"));
				temp.setEmailId(rs.getString("email_id"));
				result.add(temp);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try{conn.close();}catch (SQLException e){e.printStackTrace();}
			}
		}
		return result;
	}
	public List<QuizDetailVO> getQuizContent()
	{
		Connection conn = null;
		PreparedStatement statement=null;
		Map<Integer, QuizDetailVO> lookup = new HashMap<Integer, QuizDetailVO>();
		List<QuizDetailVO> result = new ArrayList<QuizDetailVO>();
		try 
		{
			conn=dataSource.getConnection();
			statement=conn.prepareStatement(QueriesVO.GET_MAIL_CONTENT);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				QuizDetailVO quizDetailVO = null;
				Integer quiz_id = rs.getInt("quiz_id");
				if((quizDetailVO=lookup.get(quiz_id))==null)
				{
					quizDetailVO = new QuizDetailVO();
					quizDetailVO.setQuiz_id(quiz_id);
					QuestionDetailVO question = new QuestionDetailVO();
					question.setQuestion_id(rs.getInt("question_id"));
					question.setQuestionText(rs.getString("question_text"));
					OptionDetailVO options = new OptionDetailVO();
					options.setOption_id(rs.getInt("option_id"));
					String option = rs.getString("option_text");
					options.setOption_text(option);
					question.addOptions(options);
					quizDetailVO.setQuestion(question);
					quizDetailVO.setQuiz_name(rs.getString("quiz_name"));
					lookup.put(quiz_id, quizDetailVO);
					System.out.println("QuizName: "+quizDetailVO.getQuiz_name()+" Question Text: "+quizDetailVO.getQuestion().getQuestionText());
					System.out.println(option);
				}
				else
				{
					OptionDetailVO options = new OptionDetailVO();
					options.setOption_id(rs.getInt("option_id"));
					String option = rs.getString("option_text");
					options.setOption_text(option);
					quizDetailVO.getQuestion().addOptions(options);
					System.out.println(option);
					lookup.put(quiz_id, quizDetailVO);
				}
			}
			for(QuizDetailVO temp: lookup.values())
			{
				result.add(temp);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try{conn.close();}catch (SQLException e){e.printStackTrace();}
			}
		}
		return result;
	}
	public void displayCheck()
	{
		System.out.println("Inside Display Check");
		Connection conn = null;
		PreparedStatement statement=null;
		try 
		{
			conn = dataSource.getConnection();
			System.out.println("Getting Connection Complete");
			statement=conn.prepareStatement(QueriesVO.TRIAL_CHECK);
			System.out.println("before query execution");
			ResultSet rs = statement.executeQuery();
			System.out.println("Query Execution Complete");
			while(rs.next())
				System.out.println(rs.getInt("role_id")+" "+rs.getString("role_name"));
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try{conn.close();}catch (SQLException e){e.printStackTrace();}
			}
		}
	}
	public List<Node> getDAGNodes()
	{
		List<Node> result = new ArrayList<Node>();
		Connection conn = null;
		PreparedStatement statement=null;
		try 
		{
			conn=dataSource.getConnection();
			statement=conn.prepareStatement(QueriesVO.DAG_TAG_QUERY1);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
				result.add(new Node(rs.getString("tag_name"), rs.getString("url"), rs.getInt("tag_id")));
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try{conn.close();}catch (SQLException e){e.printStackTrace();}
			}
		}
		return result;
	}
	public List<Edge> getDAGEdges()
	{
		List<Edge> result = new ArrayList<Edge>();
		Connection conn = null;
		PreparedStatement statement=null;
		try 
		{
			conn=dataSource.getConnection();
			statement=conn.prepareStatement(QueriesVO.DAG_TAG_QUERY2);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
				result.add(new Edge(rs.getInt("source_tag_id"), rs.getInt("target_tag_id"), rs.getDouble("weight")));
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try{conn.close();}catch (SQLException e){e.printStackTrace();}
			}
		}
		return result;
	}
	public Map<String, UserKnowledgeVO> getUserKnowledgeData(int user_id)
	{
		Map<String, UserKnowledgeVO> result = new HashMap<String, UserKnowledgeVO>();
		Connection conn = null;
		PreparedStatement statement=null;
		try 
		{
			conn=dataSource.getConnection();
			statement=conn.prepareStatement(QueriesVO.FETCH_USER_TAG_KNOWLEDGE);
			statement.setInt(1, user_id);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				String tag_name = rs.getString("tag_name");
				result.put(tag_name, new UserKnowledgeVO(tag_name, rs.getDouble("weight"), rs.getDouble("last_adj_wgt")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try{conn.close();}catch (SQLException e){e.printStackTrace();}
			}
		}
		return result;
	}
	public Map<String, Double> getQTagData(int question_id, int option_id)
	{
		Map<String, Double> result = new HashMap<String, Double>();
		Connection conn = null;
		PreparedStatement statement=null;
		try 
		{
			conn=dataSource.getConnection();
			statement=conn.prepareStatement(QueriesVO.FETCH_Q_TAG_WEIGHTS);
			statement.setInt(1, question_id);
			statement.setInt(2, option_id);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				String temp = rs.getString("tags");
				String [] tok1 = temp.split(",");
				for(String first_split: tok1)
				{
					String [] tok2 = first_split.split("\\|");
					result.put(tok2[0], Double.parseDouble(tok2[1]));
				}
				break;
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try{conn.close();}catch (SQLException e){e.printStackTrace();}
			}
		}
		return result;
	}
	public void executeBatch(String query)
	{
		Connection conn = null;
		Statement statement=null;
		try 
		{
			conn=dataSource.getConnection();
			statement=conn.createStatement();
			statement.executeUpdate(query);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try{conn.close();}catch (SQLException e){e.printStackTrace();}
			}
		}
	}
	public double getCorrectness(int option_id)
	{
		Connection conn = null;
		PreparedStatement statement = null;
		try 
		{
			conn=dataSource.getConnection();
			statement=conn.prepareStatement(QueriesVO.GET_CORRECTNESS);
			statement.setInt(1, option_id);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				return rs.getInt("weight");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try{conn.close();}catch (SQLException e){e.printStackTrace();}
			}
		}
		return 0.0;
	}
	public String getUserEmail(int user_id)
	{
		Connection conn = null;
		PreparedStatement statement = null;
		try 
		{
			conn=dataSource.getConnection();
			statement=conn.prepareStatement(QueriesVO.USER_EMAIL);
			statement.setInt(1, user_id);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				return rs.getString("email_id");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if(conn!=null)
			{
				try{conn.close();}catch (SQLException e){e.printStackTrace();}
			}
		}
		return "";
	}
}
