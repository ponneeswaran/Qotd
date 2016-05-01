/**
 * 
 */
package com.awqotd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.awqotd.vo.QueriesVO;
import com.awqotd.vo.ResponseVO;
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
}
