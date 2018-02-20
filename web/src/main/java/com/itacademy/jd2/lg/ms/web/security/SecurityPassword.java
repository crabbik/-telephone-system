package com.itacademy.jd2.lg.ms.web.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityPassword {
	public String coder(String rawPassword) {
		return new BCryptPasswordEncoder().encode(rawPassword);
	}

	public Boolean matchesPassword(String rawPassword, String dbPassword) {
		return new BCryptPasswordEncoder().matches(rawPassword, dbPassword);
	}
}