package com.itacademy.jd2.lg.ms.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class I18nFilter implements Filter {
	public static final String USER_LANGUAGE = "userLanguage";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpReq = (HttpServletRequest) request;

		String langParameter = request.getParameter("language");

		System.out.println("localization filter:" + langParameter);
		if (langParameter != null) {
			HttpSession session = httpReq.getSession();
			session.setAttribute(USER_LANGUAGE, langParameter);
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}