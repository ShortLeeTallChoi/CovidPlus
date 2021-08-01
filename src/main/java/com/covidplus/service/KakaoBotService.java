package com.covidplus.service;

import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Map;

public interface KakaoBotService {

	String corona(Map<String, Object> paramMap);

	ModelAndView weather(Map<String,Object> paramMap) throws IOException;

	ModelAndView inputProfile(Map<String, Object> paramMap);
}
