package com.covidplus.service;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public interface KakaoBotService {

	String corona(Map<String, Object> paramMap);
}
