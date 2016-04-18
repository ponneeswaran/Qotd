/**
 * 
 */
package com.awqotd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.awqotd.vo.QueriesVO;
import com.awqotd.vo.UserDetailsVO;

/**
 * @author Ponneeswaran
 *
 */
public class DataAccessor{
	private Connection dbConnection = DBConnection.getConnection();
	private PreparedStatement statement;
	
	public void userSignUp(UserDetailsVO userDetails) throws Exception{
		if(dbConnection==null){
			throw new Exception("DB Connection Problem");
		}
		
		statement=dbConnection.prepareStatement(QueriesVO.USER_SIGN_UP);
		statement.setString(1, userDetails.getPassword());
		statement.setString(2, userDetails.getEmailId());
		statement.executeUpdate();
	}
}
