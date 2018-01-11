package com.itacademy.jd2.lg.mobile_system.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.mobile_system.dao.IServiceHistoryDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.ServiceHistory;
import com.itacademy.jd2.lg.mobile_system.dao.impl.ServiceHistoryDaoImpl;
import com.itacademy.jd2.lg.mobile_system.services.IServiceHistoryService;
@Service
public class ServiceHistoryServiceImpl implements IServiceHistoryService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceHistoryServiceImpl.class);
	@Autowired
	private IServiceHistoryDao dao;

	@Override
	public ServiceHistory get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public void update(ServiceHistory serviceHistory) {
		dao.update(serviceHistory);

	}

	@Override
	public void insert(ServiceHistory serviceHistory) {
		dao.insert(serviceHistory);
	}

	@Override
	public List<ServiceHistory> getAll() {
		return dao.getAll();
	}

	@Override
	public List<ServiceHistory> getAll(int limit, int offset) {
		return dao.getAll(limit, offset);
	}

}