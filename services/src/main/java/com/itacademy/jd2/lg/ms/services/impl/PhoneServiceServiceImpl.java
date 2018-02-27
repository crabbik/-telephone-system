package com.itacademy.jd2.lg.ms.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.itacademy.jd2.lg.ms.dao.IPhoneServiceDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneService;
import com.itacademy.jd2.lg.ms.dao.filter.ServiceFilter;
import com.itacademy.jd2.lg.ms.services.IPhoneServiceService;

@org.springframework.stereotype.Service
public class PhoneServiceServiceImpl implements IPhoneServiceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PhoneServiceServiceImpl.class);
	@Autowired
	private IPhoneServiceDao dao;

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public PhoneService save(PhoneService service) {
		Timestamp modifiedDate = new Timestamp(new Date().getTime());
		service.setModified(modifiedDate);
		if (service.getId() == null) {
			service.setCreated(modifiedDate);
			dao.insert(service);
		} else {
			dao.update(service);
		}
		return service;
	}

	@Override
	public PhoneService get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<PhoneService> getAll() {
		return dao.getAll();
	}

	@Override
	public Long getCount(ServiceFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<PhoneService> getAll(ServiceFilter filter) {
		return dao.find(filter);
	}
}
