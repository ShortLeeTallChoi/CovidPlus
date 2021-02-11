package com.covidplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.covidplus.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	public LoginService loginSvc;
	
	@GetMapping
	public ModelAndView login() {
		return loginSvc.login();
	}
}
