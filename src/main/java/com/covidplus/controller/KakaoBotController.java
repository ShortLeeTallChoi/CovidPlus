package com.covidplus.controller;

import com.covidplus.service.KakaoBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class KakaoBotController {
    @Autowired
    private KakaoBotService kakaoBotService;

    @RequestMapping(value="/corona",produces="application/json;charset=UTF-8", method= RequestMethod.GET)
    @ResponseBody
    public String corona(@RequestParam Map<String,Object> paramMap){
        return kakaoBotService.corona(paramMap);
    }
}
