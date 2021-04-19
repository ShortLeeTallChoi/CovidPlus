package com.covidplus.service;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public interface OptionService {

	ModelAndView apiOption(Map<String, Object> paramMap);

	ModelAndView selectParseInfo(Map<String, Object> paramMap);

}
