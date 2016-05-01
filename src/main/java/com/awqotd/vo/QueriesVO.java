/**
 * 
 */
package com.awqotd.vo;


public class QueriesVO {
	public static final String USER_SIGN_UP = "INSERT INTO user_details(PASSWORD,EMAIL_ID) VALUES(?,?)";
	public static final String USER_SIGN_UP_CHECK = "SELECT COUNT(*) AS NUM FROM user_details WHERE EMAIL_ID=?";
	public static final String USER_LOGIN = "SELECT * FROM user_details WHERE PASSWORD=? AND EMAIL_ID=?";
	public static final String TRIAL_CHECK ="SELECT * FROM user_roles";
	public static final String GET_UNIQUE_ID = "{call qotd.getMailUniqueId(?,?,?,?)}";
	public static final String UPDATE_STUDENT_RESPONSE = "{call qotd.updateStudentResponse(?,?,?,?,?,?)}";
	public static final String GET_MAIL_CONTENT = "select qe.quiz_id as quiz_id, qe.question_id as question_id,"
			+ " q.question_text as question_text, o.option_id as option_id, o.option_text as option_text,"
			+ " qu.quiz_name as quiz_name from questions_expiry qe, questions q, options o, quiz qu where"
			+ " qe.quiz_id = qu.quiz_id and qe.question_id = q.question_id and qe.question_id = o.question_id"
			+ " and qe.PublishDate = curdate() order by qe.quiz_id, qe.question_id";
	public static final String GET_USER_LIST = "select u.user_id as user_id, ud.email_id as email_id from user_details ud, user_subscriptions"
			+ " u where u.user_id = ud.user_id and u.quiz_id = ?";
}
