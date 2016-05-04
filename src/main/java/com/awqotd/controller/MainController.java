package com.awqotd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.awqotd.dao.DataAccessor;
import com.awqotd.manager.CommonManager;
import com.awqotd.vo.Node;
import com.awqotd.vo.QuizDetailsVO;
import com.awqotd.vo.ResponseVO;
import com.awqotd.vo.ScheduleVO;
import com.awqotd.vo.UserDetailsVO;

@Controller
public class MainController 
{
	
	@Autowired
	DataAccessor dAcc;
	@Autowired
	CommonManager commonManager;
	
	@RequestMapping("/homePage")
	public ModelAndView getHomePage() 
	{ 
		System.out.println("In Controller");
		ModelAndView mv = new ModelAndView("homePage");
		return mv;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout() { 
		ModelAndView mv = new ModelAndView("homePage");
		return mv;
	}
	
	@RequestMapping("/instructorDashboard")
	public ModelAndView getInstDashboard() 
	{
		ModelAndView mv = new ModelAndView("instructorDashboard");
		return mv;
	}
	@RequestMapping("/studentDashboard")
	public ModelAndView getStuDashboard() 
	{
		ModelAndView mv = new ModelAndView("studentDashboard");
		return mv;
	}
	/*
	 * Funtion to Signup new User
	 */
	@RequestMapping(value = "/userSignUp", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResponseVO userSignUp(@RequestBody UserDetailsVO userDetails){
		ResponseVO responseVO = new ResponseVO();
		try {
			dAcc.userSignUp(userDetails);
			responseVO.setErrorMessage("SignUp Success");
			responseVO.setErrorStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			responseVO.setErrorMessage(e.getMessage());
			responseVO.setErrorStatus(false);
		}
		return responseVO;		
	}
	
	/*
	 * Funtion to Login existing User
	 */
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public UserDetailsVO userLogin(@RequestBody UserDetailsVO userDetails,HttpServletRequest request,
            HttpServletResponse response){
		try {
			userDetails = dAcc.userLogin(userDetails);
			if(userDetails.isErrorStatus()){
				request.getSession().setAttribute("user", userDetails.getEmailId());
			}
		} catch (Exception e) {
			userDetails = new UserDetailsVO();
			e.printStackTrace();
			userDetails.setErrorMessage("Login Failure");
			userDetails.setErrorStatus(false);
		}
		return userDetails;		
	}
	@RequestMapping(value = "/mailTest")
	public void send_mail()
	{
		commonManager.sendQuiz();
	}
	@RequestMapping(value = "/submitAnswer/{id}/{option}")
	@ResponseBody
	public String receive_answer(@PathVariable("id") String id, @PathVariable("option") String option)
	{
		System.out.println(id+" "+option);
		return commonManager.submitResponse(id, option);
	}

	/*
	 * Page: Instructor Dashboard
	 * Function: Get scheduled quizzes
	 */
	@RequestMapping(value = "/scheduledQuizzes", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResponseVO scheduledQuizzes(@RequestBody UserDetailsVO userDetails){
		ResponseVO responseVO = new ResponseVO();
		try {
			responseVO.setqDetails(dAcc.scheduledQuizzes(userDetails));
			responseVO.setErrorMessage("Quiz Fetch Success");
			responseVO.setErrorStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			responseVO.setErrorMessage(e.getMessage());
			responseVO.setErrorStatus(false);
		}
		return responseVO;		
	}
	
	/*
	 * Page: Instructor Dashboard
	 * Function: Get quiz questions
	 */
	@RequestMapping(value = "/getQuestions", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResponseVO getQuestions(@RequestBody QuizDetailsVO qDetailsVO){
		ResponseVO responseVO = new ResponseVO();
		try {
			responseVO.setQuesDetails(dAcc.getQuestions(qDetailsVO));
			responseVO.setErrorMessage("Question Fetch Success");
			responseVO.setErrorStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			responseVO.setErrorMessage(e.getMessage());
			responseVO.setErrorStatus(false);
		}
		return responseVO;		
	}
	
	/*
	 * Page: Instructor Dashboard
	 * Function: Get Java Topics
	 */
	@RequestMapping(value = "/getJavaTopics", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResponseVO getJavaTopics(){
		ResponseVO responseVO = new ResponseVO();
		try {
			responseVO.setList_obj(dAcc.getJavaTopics());
			responseVO.setErrorMessage("Java Topic Fetch Success");
			responseVO.setErrorStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			responseVO.setErrorMessage(e.getMessage());
			responseVO.setErrorStatus(false);
		}
		return responseVO;		
	}
	
	/*
	 * Page: Instructor Dashboard
	 * Function: Get Java Topics
	 */
	@RequestMapping(value = "/getTopicQuestions", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResponseVO getQuestionsonTopic(@RequestBody Node node){
		ResponseVO responseVO = new ResponseVO();
		try {
			responseVO.setQuesDetails(dAcc.getQuestionsonTopic(node));
			responseVO.setErrorMessage("Java Topic Fetch Success");
			responseVO.setErrorStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			responseVO.setErrorMessage(e.getMessage());
			responseVO.setErrorStatus(false);
		}
		return responseVO;		
	}
	
	/*
	 * Page: Instructor Dashboard
	 * Function: Get Schedule quiz
	 */
	@RequestMapping(value = "/scheduleQuiz", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResponseVO scheduleQuiz(@RequestBody ScheduleVO scheduleVO){
		System.out.println("Inside controller!!!!");
		ResponseVO responseVO = new ResponseVO();
		try {
			dAcc.scheduleQuiz(scheduleVO);
			commonManager.sendQuiz();
			responseVO.setErrorMessage("Java Topic Fetch Success");
			responseVO.setErrorStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			responseVO.setErrorMessage(e.getMessage());
			responseVO.setErrorStatus(false);
		}
		return responseVO;		
	}
	
	@RequestMapping(value = "/getChartJson", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String getChartJson(){
		return "{\"comment\": \"DAG Tag\",\"nodes\": [{\"id\":0, \"name\":\"java\",\"group\":0},{\"id\":1, \"name\":\"object\",\"group\":1},{\"id\":2, \"name\":\"inheritance\",\"group\":0},{\"id\":3, \"name\":\"class\",\"group\":0},{\"id\":4, \"name\":\"statements\",\"group\":0},{\"id\":5, \"name\":\"conditional_blocks\",\"group\":0},{\"id\":6, \"name\":\"loop_blocks\",\"group\":0},{\"id\":7, \"name\":\"boolean_expression\",\"group\":0},{\"id\":8, \"name\":\"variables\",\"group\":0},{\"id\":9, \"name\":\"primitive_types\",\"group\":0},{\"id\":10, \"name\":\"arithmetic_expressions\",\"group\":0},{\"id\":11, \"name\":\"literals\",\"group\":0},{\"id\":12, \"name\":\"methods\",\"group\":0},{\"id\":13, \"name\":\"string\",\"group\":0},{\"id\":14, \"name\":\"arrays\",\"group\":0},{\"id\":15, \"name\":\"interface\",\"group\":0},{\"id\":16, \"name\":\"nested_classes\",\"group\":0},{\"id\":17, \"name\":\"scope\",\"group\":0},{\"id\":18, \"name\":\"generics\",\"group\":0},{\"id\":19, \"name\":\"overloading\",\"group\":0},{\"id\":20, \"name\":\"collections\",\"group\":0},{\"id\":21, \"name\":\"arraylist\",\"group\":0},{\"id\":22, \"name\":\"map\",\"group\":0},{\"id\":23, \"name\":\"throwing_catching_exceptions\",\"group\":0},{\"id\":24, \"name\":\"checked_exception\",\"group\":0},{\"id\":25, \"name\":\"unchecked_exception\",\"group\":0},{\"id\":26, \"name\":\"nested_exception\",\"group\":0},{\"id\":27, \"name\":\"stacktrace\",\"group\":0},{\"id\":28, \"name\":\"threads_and_runnables\",\"group\":0},{\"id\":29, \"name\":\"client_server_programming\",\"group\":0},{\"id\":30, \"name\":\"rmi\",\"group\":0},{\"id\":31, \"name\":\"basic_synchronization\",\"group\":0},{\"id\":32, \"name\":\"reflection\",\"group\":0},{\"id\":33, \"name\":\"dynamic_class_loading\",\"group\":0},{\"id\":34, \"name\":\"dynamic_method_invocation\",\"group\":0},{\"id\":35, \"name\":\"accessing_private_features\",\"group\":0},{\"id\":36, \"name\":\"abstractcollection\",\"group\":0},{\"id\":37, \"name\":\"abstractlist\",\"group\":0},{\"id\":38, \"name\":\"abstractqueue\",\"group\":0},{\"id\":39, \"name\":\"abstractset\",\"group\":0},{\"id\":40, \"name\":\"treeset\",\"group\":0},{\"id\":41, \"name\":\"hashset\",\"group\":0},{\"id\":42, \"name\":\"enumset\",\"group\":0},{\"id\":43, \"name\":\"sortedset\",\"group\":0},{\"id\":44, \"name\":\"linked_hashset\",\"group\":0},{\"id\":45, \"name\":\"vector\",\"group\":0},{\"id\":46, \"name\":\"attributelist\",\"group\":0},{\"id\":47, \"name\":\"role_list\",\"group\":0},{\"id\":48, \"name\":\"role_unresolved_list\",\"group\":0},{\"id\":49, \"name\":\"array_blocking_queue\",\"group\":0},{\"id\":50, \"name\":\"synchronous_queue\",\"group\":0},{\"id\":51, \"name\":\"priority_queue\",\"group\":0},{\"id\":52, \"name\":\"blocking_queue\",\"group\":0},{\"id\":53, \"name\":\"set\",\"group\":0},{\"id\":54, \"name\":\"queue\",\"group\":0},{\"id\":55, \"name\":\"list\",\"group\":0},{\"id\":56, \"name\":\"constructor\",\"group\":0},{\"id\":57, \"name\":\"polymorphism\",\"group\":0},{\"id\":58, \"name\":\"arrays\",\"group\":0},{\"id\":59, \"name\":\"string\",\"group\":0},{\"id\":60, \"name\":\"concurrent_programming\",\"group\":0},{\"id\":61, \"name\":\"datastructures\",\"group\":0},{\"id\":62, \"name\":\"fundamentals\",\"group\":0},{\"id\":63, \"name\":\"exceptions\",\"group\":0}],\"links\": [{\"source\":0, \"target\":3, \"value\": 5},{\"source\":0, \"target\":20, \"value\": 5},{\"source\":0, \"target\":32, \"value\": 5},{\"source\":0, \"target\":60, \"value\": 5},{\"source\":0, \"target\":62, \"value\": 5},{\"source\":0, \"target\":63, \"value\": 5},{\"source\":1, \"target\":32, \"value\": 5},{\"source\":2, \"target\":57, \"value\": 5},{\"source\":3, \"target\":2, \"value\": 5},{\"source\":3, \"target\":15, \"value\": 5},{\"source\":3, \"target\":16, \"value\": 5},{\"source\":3, \"target\":17, \"value\": 5},{\"source\":3, \"target\":32, \"value\": 5},{\"source\":3, \"target\":56, \"value\": 5},{\"source\":3, \"target\":57, \"value\": 5},{\"source\":4, \"target\":5, \"value\": 5},{\"source\":4, \"target\":6, \"value\": 5},{\"source\":4, \"target\":12, \"value\": 5},{\"source\":8, \"target\":7, \"value\": 5},{\"source\":8, \"target\":10, \"value\": 5},{\"source\":8, \"target\":11, \"value\": 5},{\"source\":8, \"target\":17, \"value\": 5},{\"source\":11, \"target\":1, \"value\": 5},{\"source\":11, \"target\":7, \"value\": 5},{\"source\":11, \"target\":10, \"value\": 5},{\"source\":12, \"target\":1, \"value\": 5},{\"source\":12, \"target\":16, \"value\": 5},{\"source\":12, \"target\":17, \"value\": 5},{\"source\":12, \"target\":32, \"value\": 5},{\"source\":12, \"target\":57, \"value\": 5},{\"source\":20, \"target\":36, \"value\": 5},{\"source\":21, \"target\":46, \"value\": 5},{\"source\":21, \"target\":47, \"value\": 5},{\"source\":21, \"target\":48, \"value\": 5},{\"source\":23, \"target\":24, \"value\": 5},{\"source\":23, \"target\":25, \"value\": 5},{\"source\":23, \"target\":26, \"value\": 5},{\"source\":23, \"target\":27, \"value\": 5},{\"source\":27, \"target\":26, \"value\": 5},{\"source\":28, \"target\":29, \"value\": 5},{\"source\":28, \"target\":31, \"value\": 5},{\"source\":29, \"target\":30, \"value\": 5},{\"source\":31, \"target\":29, \"value\": 5},{\"source\":31, \"target\":30, \"value\": 5},{\"source\":32, \"target\":33, \"value\": 5},{\"source\":32, \"target\":34, \"value\": 5},{\"source\":33, \"target\":35, \"value\": 5},{\"source\":34, \"target\":35, \"value\": 5},{\"source\":36, \"target\":37, \"value\": 5},{\"source\":36, \"target\":38, \"value\": 5},{\"source\":36, \"target\":39, \"value\": 5},{\"source\":37, \"target\":21, \"value\": 5},{\"source\":37, \"target\":45, \"value\": 5},{\"source\":38, \"target\":49, \"value\": 5},{\"source\":38, \"target\":50, \"value\": 5},{\"source\":38, \"target\":51, \"value\": 5},{\"source\":39, \"target\":40, \"value\": 5},{\"source\":39, \"target\":41, \"value\": 5},{\"source\":39, \"target\":42, \"value\": 5},{\"source\":40, \"target\":43, \"value\": 5},{\"source\":41, \"target\":44, \"value\": 5},{\"source\":53, \"target\":39, \"value\": 5},{\"source\":54, \"target\":38, \"value\": 5},{\"source\":54, \"target\":52, \"value\": 5},{\"source\":55, \"target\":37, \"value\": 5},{\"source\":57, \"target\":19, \"value\": 5},{\"source\":60, \"target\":28, \"value\": 5},{\"source\":61, \"target\":53, \"value\": 5},{\"source\":61, \"target\":54, \"value\": 5},{\"source\":61, \"target\":55, \"value\": 5},{\"source\":62, \"target\":4, \"value\": 5},{\"source\":62, \"target\":8, \"value\": 5},{\"source\":62, \"target\":61, \"value\": 5},{\"source\":63, \"target\":23, \"value\": 5},{\"source\":63, \"target\":24, \"value\": 5},{\"source\":63, \"target\":25, \"value\": 5},{\"source\":63, \"target\":26, \"value\": 5},{\"source\":63, \"target\":27, \"value\": 5}]}";		
	}
}
