package com.covidplus.controller;

import java.lang.reflect.Parameter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.covidplus.service.OptionService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/option")
public class OptionController {
	@Autowired
	public OptionService optionService;
	
	@GetMapping("/{optionPath}")
	public String testDirectAccess(@PathVariable("optionPath") String optionPath) {
		return "/option/"+optionPath;
	}
	
	@GetMapping("/api")
	public ModelAndView apiOption(@RequestParam Map<String,Object> paramMap) {
		ModelAndView modelAndView = optionService.apiOption(paramMap);
		return modelAndView;
	}
	
	@PostMapping("/selectParseInfo")
	public ModelAndView selectParseInfo(@RequestParam Map<String,Object> paramMap) {
		ModelAndView modelAndView = optionService.selectParseInfo(paramMap);
		return modelAndView;
	}
	
	@PostMapping("/processOption")
	public ModelAndView processOption(@RequestParam Map<String,Object> paramMap) {
		ModelAndView modelAndView = optionService.processOption(paramMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/parsingLog", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView parsingLog(@RequestParam Map<String,Object> paramMap) {
		ModelAndView modelAndView = optionService.parsingLog(paramMap);
		return modelAndView;
	}
}
