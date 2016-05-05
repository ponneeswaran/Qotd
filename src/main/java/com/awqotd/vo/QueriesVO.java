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
	public static final String DAG_TAG_QUERY1 = "select * from dag_tag order by tag_id";
	public static final String DAG_TAG_QUERY2 = "select * from dag_tag_links";
	public static final String FETCH_USER_TAG_KNOWLEDGE = "select tag_name, weight, last_adj_wgt from user_knowledge where user_id = ?";
	public static final String FETCH_Q_TAG_WEIGHTS = "select tags from options where question_id = ? and option_id = ?";
	public static final String GET_CORRECTNESS = "select weight from options where option_id = ?";
	public static final String USER_EMAIL = "select email_id from user_details where user_id = ?";
	public static final String SCHEDULED_QUIZ = "SELECT QZ.QUIZ_ID, QZ.QUIZ_NAME, COUNT(SUBS.QUIZ_ID) AS SUBSCRIPTIONS "
												+ "FROM quiz QZ "
												+ "INNER JOIN user_details USER ON USER.USER_ID = QZ.CREATED_BY AND USER.EMAIL_ID = ? "
												+ "LEFT JOIN user_subscriptions SUBS ON SUBS.QUIZ_ID = QZ.QUIZ_ID "
												+ "ORDER BY CREATED_DATE DESC";
	public static final String QUIZ_QUESTIONS = "SELECT QS.QUESTION_ID, QS.QUESTION_TEXT, QS.CREATED_DATE, COUNT(RS.USER_ID) AS SUBMISSIONS "
												+ "FROM questions QS "
												+ "INNER JOIN questions_expiry QZ ON QZ.QUESTION_ID = QS.QUESTION_ID AND QZ.QUIZ_ID = ? "
												+ "LEFT JOIN results RS ON RS.QUESTION_ID = QZ.QUESTION_ID AND RS.QUIZ_ID = QZ.QUIZ_ID "
												+ "GROUP BY QS.QUESTION_TEXT, QS.CREATED_DATE "
												+ "ORDER BY QS.CREATED_DATE DESC";
	public static final String JAVA_TOPICS = "SELECT TAG_NAME AS LIST_OBJ FROM dag_tag";
	public static final String TOPIC_QUESTIONS = "SELECT QUESTION_ID, QUESTION_TEXT FROM questions WHERE TAG LIKE '%";
	public static final String GET_AUTO_INCREMENT = "SELECT AUTO_INCREMENT AS ID "
													+ "FROM information_schema.TABLES "
													+ "WHERE TABLE_SCHEMA = 'qotd' "
													+ "AND TABLE_NAME = ?";
	public static final String QUIZ_INSERT = "INSERT INTO quiz(QUIZ_ID, QUIZ_NAME, CREATED_BY, CREATED_DATE) VALUES(?,?,(SELECT USER_ID FROM user_details WHERE EMAIL_ID = ? AND ROLE_ID =2),?)";
	public static final String QUESTIONS_INSERT = "INSERT INTO questions(QUESTION_ID, QUESTION_TEXT, CREATED_BY, CREATED_DATE, TAG) VALUES(?,?,(SELECT USER_ID FROM user_details WHERE EMAIL_ID = ? AND ROLE_ID =2),?,?)";
	public static final String EXPIRY_INSERT = "INSERT INTO questions_expiry(QUIZ_ID, QUESTION_ID, EXPIRYDATE, PUBLISHDATE) VALUES(?,?,?,?)";
	public static final String OPTIONS_INSERT = "INSERT INTO options(OPTION_ID, QUESTION_ID, OPTION_TEXT, TAGS, WEIGHT) VALUES(?,?,?,?,?)";
	public static final String GET_QUIZ_PERF = "SELECT RS.QUESTION_ID, OPT.OPTION_ID, SUM(OPT.WEIGHT) AS TOTAL, COUNT(RS.QUESTION_ID) AS STUDENTS "
												+ "FROM options OPT "
												+ "RIGHT JOIN results RS ON OPT.OPTION_ID = RS.OPTION_ID AND OPT.QUESTION_ID = RS.QUESTION_ID "
												+ "WHERE RS.QUIZ_ID = ? "
												+ "GROUP BY RS.QUESTION_ID "
												+ "ORDER BY RS.QUESTION_ID";
	public static final String GET_SUBSCRIPTIONS = "SELECT SUBS.QUIZ_ID, QZ.QUIZ_NAME, COUNT(RES.QUESTION_ID) AS QUESTIONS "
												+ "FROM user_details UDET "
												+ "INNER JOIN user_subscriptions SUBS ON UDET.USER_ID = SUBS.USER_ID AND UDET.EMAIL_ID = ? "
												+ "LEFT JOIN results RES ON SUBS.USER_ID = RES.USER_ID AND SUBS.QUIZ_ID = RES.QUIZ_ID "
												+ "INNER JOIN quiz QZ ON SUBS.QUIZ_ID = QZ.QUIZ_ID "
												+ "GROUP BY SUBS.QUIZ_ID, QZ.QUIZ_NAME "
												+ "ORDER BY SUBS.QUIZ_ID";
	public static final String STUDENT_QUESTIONS = "SELECT QZ.QUESTION_ID, QS.QUESTION_TEXT, QZ.PUBLISHDATE, CASE WHEN ( "
												+ "SELECT OPTION_ID FROM results "
												+ "WHERE SUBS.USER_ID = USER_ID AND SUBS.QUIZ_ID = QUIZ_ID AND QZ.QUESTION_ID = QUESTION_ID) THEN 1 ELSE 0 END AS ANSWERED, CASE WHEN ( "
												+ "SELECT OPTION_ID FROM results "
												+ "WHERE SUBS.USER_ID = USER_ID AND SUBS.QUIZ_ID = QUIZ_ID AND QZ.QUESTION_ID = QUESTION_ID) THEN ( "
												+ "SELECT WEIGHT FROM options "
												+ "WHERE OPTION_ID = ( "
												+ "SELECT OPTION_ID FROM results "
												+ "WHERE SUBS.USER_ID = USER_ID AND SUBS.QUIZ_ID = QUIZ_ID AND QZ.QUESTION_ID = QUESTION_ID)) ELSE 0 END AS CORRECTNESS "
												+ "FROM user_details UDET "
												+ "INNER JOIN user_subscriptions SUBS ON UDET.USER_ID = SUBS.USER_ID AND UDET.EMAIL_ID = ? " 
												+ "INNER JOIN questions_expiry QZ ON QZ.QUIZ_ID = SUBS.QUIZ_ID AND QZ.QUIZ_ID = ? "
												+ "INNER JOIN questions QS ON QZ.QUESTION_ID = QS.QUESTION_ID";
	public static final String GET_NON_SUBSCRIPTIONS = "SELECT qz.quiz_id, qz.quiz_name, UDET.email_id, COUNT(SUBS.QUIZ_ID) AS SUBSCRIPTIONS "
														+ "FROM quiz qz "
														+ "LEFT JOIN user_subscriptions SUBS ON SUBS.QUIZ_ID = qz.QUIZ_ID "
														+ "JOIN user_details UDET ON qz.created_by = UDET.user_id "
														+ "WHERE qz.quiz_id NOT IN " 
														+ "(SELECT quiz_id "
														+ "FROM user_subscriptions "
														+ "WHERE user_id = ( "
														+ "SELECT user_id "
														+ "FROM user_details "
														+ "WHERE email_id = ?))";
	public static final String QUIZ_SUBSCRIPTION = "INSERT INTO user_subscriptions(USER_ID,QUIZ_ID) VALUES((SELECT USER_ID FROM user_details WHERE EMAIL_ID = ?),?)";
}
