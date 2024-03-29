package com.covidplus.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.covidplus.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	public LoginService loginSvc;
	

	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login() {
		return loginSvc.login();
	}
	
	@RequestMapping(value="/join",method = RequestMethod.GET)
	public ModelAndView register(@RequestParam Map<String,Object> paramMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@RequestMapping(value="/join",method = RequestMethod.POST)
	public ModelAndView join(@RequestParam Map<String,Object> paramMap) {
		return loginSvc.join(paramMap);
	}
}
