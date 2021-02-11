package com.covidplus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.covidplus.dao.LoginDao;
import com.covidplus.model.UserVO;
import com.covidplus.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	public LoginDao loginDao;
	
	@Override
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@Override
	public UserVO authenticate(String id, String pass) {
		System.out.println(11);
		return loginDao.memberInfoOne(new UserVO(id, pass));
	}

}
