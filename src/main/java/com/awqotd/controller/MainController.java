package com.awqotd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.awqotd.dao.DataAccessor;
import com.awqotd.vo.ResponseVO;
import com.awqotd.vo.UserDetailsVO;

@Controller
public class MainController {
	
	DataAccessor dAcc = new DataAccessor();
	
	@RequestMapping("/homePage")
	public ModelAndView showMessage() {
		System.out.println("in controller");
 
		ModelAndView mv = new ModelAndView("homePage");
		return mv;
	}
	
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
			responseVO.setErrorMessage("SignUp Failure");
			responseVO.setErrorStatus(false);
		}
		return responseVO;		
	}
}
