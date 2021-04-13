package com.covidplus.service.impl;

import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.covidplus.configuration.SecurityConfig;
import com.covidplus.dao.DashboardDao;
import com.covidplus.dao.LoginDao;
import com.covidplus.model.UserVO;
import com.covidplus.service.DashboardService;
import com.covidplus.service.LoginService;
import com.covidplus.util.AlertAndRedirect;
import com.covidplus.util.Converter;

@Service
public class DashboardServiceImpl implements DashboardService,UserDetailsService {
	@Autowired
	public DashboardDao dashboardDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView Dashboard_data(Map<String, Object> paramMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		
		modelAndView.addObject("sidoCase", dashboardDao.selectSidoCaseChartData(paramMap));
		modelAndView.addObject("genAgeCase", dashboardDao.selectGenAgeCaseData(paramMap));
		//modelAndView.addObject("natCase", dashboardDao.selectNatCaseData(paramMap));
		//modelAndView.addObject("totalCase", dashboardDao.selectTotalCaseData(paramMap));
		
		return modelAndView;
	}

	@Override
	public ModelAndView dashBoardMain(Map<String, Object> paramMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dashboard");
		
		modelAndView.addObject("totalCase", dashboardDao.selectTotalCaseData(paramMap));
		modelAndView.addObject("totalCase_distance", dashboardDao.selectTotalCaseData_distance(paramMap));
		
		return modelAndView;
	}


}
