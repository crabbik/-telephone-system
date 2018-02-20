package com.itacademy.jd2.lg.ms.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class I18nFilter implements Filter {
	public static final String USER_LANGUAGE = "userLanguage";

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse responce, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponce = (HttpServletResponse) responce;
		String langParametr = httpRequest.getParameter("language");
		HttpSession session = httpRequest.getSession();

		if (langParametr != null) {
			Cookie cookie = new Cookie("language", langParametr);
			httpResponce.addCookie(cookie);

			session.setAttribute(USER_LANGUAGE, langParametr);
		}
		if (session.getAttribute(USER_LANGUAGE) == null) {
			Cookie[] cookies = httpRequest.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("language")) {
						session.setAttribute(USER_LANGUAGE, cookie.getValue());
					}
				}
			}
		}
		chain.doFilter(request, responce);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}