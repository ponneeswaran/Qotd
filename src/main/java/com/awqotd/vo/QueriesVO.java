/**
 * 
 */
package com.awqotd.vo;


public class QueriesVO {
	public static final String USER_SIGN_UP = "INSERT INTO user_details(PASSWORD,EMAIL_ID) VALUES(?,?)";
	public static final String USER_SIGN_UP_CHECK = "SELECT COUNT(*) AS NUM FROM user_details WHERE EMAIL_ID=?";
	public static final String USER_LOGIN = "SELECT * FROM user_details WHERE PASSWORD=? AND EMAIL_ID=?";
	public static final String SCHEDULED_QUIZ = "SELECT QZ.QUIZ_ID, QZ.QUIZ_NAME, COUNT(SUBS.QUIZ_ID) AS SUBSCRIPTIONS "
												+ "FROM quiz QZ "
												+ "INNER JOIN user_details USER ON USER.USER_ID = QZ.CREATED_BY AND USER.EMAIL_ID = ? "
												+ "INNER JOIN user_subscriptions SUBS ON SUBS.QUIZ_ID = QZ.QUIZ_ID "
												+ "ORDER BY CREATED_DATE DESC";
	public static final String QUIZ_QUESTIONS = "SELECT QS.QUESTION_ID, QS.QUESTION_TEXT, QS.CREATED_DATE, COUNT(RS.USER_ID) AS SUBMISSIONS "
												+ "FROM questions QS "
												+ "INNER JOIN questions_expiry QZ ON QZ.QUESTION_ID = QS.QUESTION_ID AND QZ.QUIZ_ID = ? "
												+ "INNER JOIN results RS ON RS.QUESTION_ID = QZ.QUESTION_ID AND RS.QUIZ_ID = QZ.QUIZ_ID "
												+ "GROUP BY QS.QUESTION_TEXT, QS.CREATED_DATE "
												+ "ORDER BY QS.CREATED_DATE DESC";
	public static final String JAVA_TOPICS = "SELECT TAG_NAME AS LIST_OBJ FROM dag_tag";
	public static final String TOPIC_QUESTIONS = "SELECT QUESTION_ID, QUESTION_TEXT FROM questions WHERE TAG LIKE '%";
	public static final String GET_AUTO_INCREMENT = "SELECT AUTO_INCREMENT AS ID "
													+ "FROM information_schema.TABLES "
													+ "WHERE TABLE_SCHEMA = 'qotd' "
													+ "AND TABLE_NAME = ?";
	public static final String QUIZ_INSERT = "INSERT INTO quiz VALUES (?,?,(SELECT USER_ID FROM user_details WHERE EMAIL_ID = ? AND ROLE_ID =2),?)";
	public static final String QUESTIONS_INSERT = "INSERT INTO questions VALUES (?,?,(SELECT USER_ID FROM user_details WHERE EMAIL_ID = ? AND ROLE_ID =2),?,?)";
	public static final String EXPIRY_INSERT = "INSERT INTO questions_expiry VALUES (?,?,?,?)";
	public static final String OPTIONS_INSERT = "INSERT INTO options VALUES (?,?,?,?,?)";
}
