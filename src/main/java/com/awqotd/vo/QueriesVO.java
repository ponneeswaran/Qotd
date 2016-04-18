/**
 * 
 */
package com.awqotd.vo;


public class QueriesVO {
	public static final String USER_SIGN_UP = "INSERT INTO user_details(PASSWORD,EMAIL_ID) VALUES(?,?)";
	public static final String USER_SIGN_UP_CHECK = "SELECT COUNT(*) AS NUM FROM user_details WHERE EMAIL_ID=?";
	public static final String USER_LOGIN = "SELECT * FROM user_details WHERE PASSWORD=? AND EMAIL_ID=?";
}
