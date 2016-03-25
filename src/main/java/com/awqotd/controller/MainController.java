package com.awqotd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.awqotd.vo.UserDetailsVO;

@Controller
public class MainController {
	 
	@RequestMapping("/homePage")
	public ModelAndView showMessage() {
		System.out.println("in controller");
 
		ModelAndView mv = new ModelAndView("homePage");
		return mv;
	}
	
	@RequestMapping(value = "/userSignUp", method = RequestMethod.POST)
	public @ResponseBody String userSignUp(@RequestBody UserDetailsVO userDetails){
		System.out.println("EmailId: "+userDetails.getEmailId());
		System.out.println("Password: "+userDetails.getPassword());
		return "success";		
	}
}
