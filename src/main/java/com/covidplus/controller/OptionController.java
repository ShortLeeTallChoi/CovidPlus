package com.covidplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/option")
public class OptionController {
	@GetMapping("/{optionPath}")
	public String testDirectAccess(@PathVariable("optionPath") String optionPath) {
		return "/option/"+optionPath;
	}
}
