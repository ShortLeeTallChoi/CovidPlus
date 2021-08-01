package com.covidplus.controller;

import com.covidplus.service.KakaoBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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

    @RequestMapping(value="/kakao/test",produces="application/json;charset=UTF-8", method= RequestMethod.GET)
    public ModelAndView kakao_test(@RequestParam Map<String,Object> paramMap){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("kakaoresult");
        modelAndView.addObject("result","테스트");
        return modelAndView;
    }

    @RequestMapping(value="/kakao/weather",produces="application/json;charset=UTF-8", method= RequestMethod.GET)
    public ModelAndView kakao_weather(@RequestParam Map<String,Object> paramMap) throws IOException {
        ModelAndView modelAndView = kakaoBotService.weather(paramMap);
        return modelAndView;
    }

    @RequestMapping(value="/kakao/input_profile",produces="application/json;charset=UTF-8", method= RequestMethod.GET)
    public ModelAndView kakao_profile_post(@RequestParam Map<String,Object> paramMap) throws IOException {
        ModelAndView modelAndView = kakaoBotService.inputProfile(paramMap);
        return modelAndView;
    }
}
