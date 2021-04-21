package com.covidplus.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.covidplus.dao.OptionDao;
import com.covidplus.model.ParseOptionVO;
import com.covidplus.service.OptionService;
import com.covidplus.util.Converter;
import com.google.gson.Gson;

@Service
public class OptionServiceImpl implements OptionService {
	
	@Autowired
	public OptionDao optionDao; 
	
	@Override
	public ModelAndView apiOption(Map<String, Object> paramMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("apiOption");
		modelAndView.addObject("parseVOList",optionDao.selectParseOptionList(new ParseOptionVO()));
		return modelAndView;
	}

	@Override
	public ModelAndView selectParseInfo(Map<String, Object> paramMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		ParseOptionVO parseVO = new ParseOptionVO();
		parseVO.setParsing_key((String) paramMap.get("parsing_key"));
		modelAndView.addObject("parseVO",optionDao.selectParseOptionOne(Converter.map2voConvert(paramMap, ParseOptionVO.class)));
		return modelAndView;
	}

	@Override
	public ModelAndView processOption(Map<String, Object> paramMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		Gson gson = new Gson();
		Map<String,Object> map = gson.fromJson((String) paramMap.get("option"), Map.class);
		for (String key : map.keySet()) {
			map.put(key,map.get(key).toString());
		}
		
		optionDao.replaceParseOption(map);
		System.out.println(map.toString());
		return null;
	}

	@Override
	public ModelAndView parsingLog(Map<String, Object> paramMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("parsingLog");
		modelAndView.addObject("parseVOList",optionDao.selectParseOptionList(new ParseOptionVO()));
		return modelAndView;
	}

}
