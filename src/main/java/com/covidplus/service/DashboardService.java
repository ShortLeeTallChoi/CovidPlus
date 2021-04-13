package com.covidplus.service;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.covidplus.model.UserVO;

public interface DashboardService {
	public ModelAndView Dashboard_data(Map<String, Object> paramMap);

	public ModelAndView dashBoardMain(Map<String, Object> paramMap);
}
