package com.covidplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FreeController {
	@GetMapping("/{filePath}")
	public String freeController(@PathVariable("filePath") String filePath) {
		return filePath;
	}
}
