package com.awqotd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.awqotd.dao.DataAccessor;
import com.awqotd.vo.Node;
import com.awqotd.vo.QuizDetailsVO;
import com.awqotd.vo.ResponseVO;
import com.awqotd.vo.ScheduleVO;
import com.awqotd.vo.UserDetailsVO;

@Controller
public class MainController {
	
	@Autowired
	DataAccessor dAcc;
	
	@RequestMapping("/homePage")
	public ModelAndView getHomePage() { 
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
	public ModelAndView getInstDashboard() {
		ModelAndView mv = new ModelAndView("instructorDashboard");
		return mv;
	}
	
	@RequestMapping("/studentDashboard")
	public ModelAndView getStuDashboard() {
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
	@RequestMapping(value = "/getQuestionsonTopic", method = RequestMethod.POST, produces="application/json")
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
		ResponseVO responseVO = new ResponseVO();
		try {
			dAcc.scheduleQuiz(scheduleVO);
			responseVO.setErrorMessage("Java Topic Fetch Success");
			responseVO.setErrorStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			responseVO.setErrorMessage(e.getMessage());
			responseVO.setErrorStatus(false);
		}
		return responseVO;		
	}
}
