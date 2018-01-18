package com.itacademy.jd2.lg.ms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("/sample")
public class TestController {

	@RequestMapping(method = RequestMethod.GET)
	public String hello() {
		return "hello 1st controller";
	}

}
