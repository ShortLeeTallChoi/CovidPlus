package com.covidplus.service;

import org.springframework.web.servlet.ModelAndView;

import com.covidplus.model.UserVO;

public interface LoginService {
	public ModelAndView login();

	public UserVO authenticate(String id, String pass);
}
