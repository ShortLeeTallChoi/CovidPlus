package com.covidplus.util;

import org.springframework.web.servlet.ModelAndView;

public class AlertAndRedirect {
	public static ModelAndView success(String message, String path) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", message);
		modelAndView.addObject("redirectPath",path);
		modelAndView.setViewName("util/success");
		return modelAndView;
	}
	
	public static ModelAndView failure(String message) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", message);
		modelAndView.setViewName("util/failure");
		return modelAndView;
	}
}
