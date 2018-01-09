package com.itacademy.jd2.lg.mobile_system.services.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceComponent {

	@Autowired
	private DaoComponent dao;

	public DaoComponent getDao() {
		return dao;
	}

	public void setDao(DaoComponent dao) {
		this.dao = dao;
	}

}