package com.covidplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FreeController {
	@GetMapping("/{filePath}")
	public String freeController(@PathVariable("filePath") String filePath) {
		return filePath;
	}
	@GetMapping("/user/denied")
	public ModelAndView deniedController() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("denied");
		return modelAndView;
	}
}
