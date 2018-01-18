package com.itacademy.jd2.lg.ms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@RequestMapping(value = "welcome", method = RequestMethod.GET)
	public String welcomePage(ModelAndView model) {
		model.addObject("greeting", "Hello person");
		return "welcome";
	}

}