package com.itacademy.jd2.lg.ms.web.servlet;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyServlet {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"context.xml");

		System.out.println("Web Application initialized");
	}
}
