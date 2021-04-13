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

import com.covidplus.service.DashboardService;
import com.covidplus.service.LoginService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	public DashboardService dashboardService;
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView dashBoardMain(@RequestParam Map<String,Object> paramMap) {
		ModelAndView modelAndView = dashboardService.dashBoardMain(paramMap);
		return modelAndView;
	}
	@RequestMapping(value="index",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView dashBoardMain2() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dashboard");
		return modelAndView;
	}
	
	@RequestMapping(value = "/chartData.do", method = RequestMethod.POST)
	public ModelAndView dashBoardData(@RequestParam Map<String,Object> paramMap) {
		return dashboardService.Dashboard_data(paramMap);
	}
}
