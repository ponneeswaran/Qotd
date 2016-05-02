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
import com.awqotd.vo.ResponseVO;
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
}
