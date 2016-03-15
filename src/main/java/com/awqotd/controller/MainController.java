package com.awqotd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.awqotd.vo.ConstantVO;

@Controller
public class MainController {
	 
	@RequestMapping("/homePage")
	public ModelAndView showMessage() {
		System.out.println("in controller");
 
		ModelAndView mv = new ModelAndView("homePage");
		mv.addObject("clientId",ConstantVO.clientId);
		return mv;
	}
}
