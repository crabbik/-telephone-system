package com.itacademy.jd2.lg.mobile_system.web.servlet;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyServlet {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"context.xml");

		System.out.println("Web Application initialized");
	}
}
