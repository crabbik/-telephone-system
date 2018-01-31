package com.itacademy.jd2.lg.ms.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.ms.dao.IServiceHistoryDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.ServiceHistory;
import com.itacademy.jd2.lg.ms.dao.filter.ServiceHistoryFilter;
import com.itacademy.jd2.lg.ms.services.IServiceHistoryService;

@Service
public class ServiceHistoryServiceImpl implements IServiceHistoryService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceHistoryServiceImpl.class);
	@Autowired
	private IServiceHistoryDao dao;

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public ServiceHistory save(ServiceHistory serviceHistory) {
		Timestamp modifiedDate = new Timestamp(new Date().getTime());
		serviceHistory.setModified(modifiedDate);
		if (serviceHistory.getId() == null) {
			serviceHistory.setCreated(modifiedDate);
			dao.insert(serviceHistory);
		} else {
			dao.update(serviceHistory);
		}
		return serviceHistory;
	}

	@Override
	public ServiceHistory get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<ServiceHistory> getAll() {
		return dao.getAll();
	}

	@Override
	public Long getCount(ServiceHistoryFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<ServiceHistory> getAll(ServiceHistoryFilter filter) {
		return dao.find(filter);
	}
}
