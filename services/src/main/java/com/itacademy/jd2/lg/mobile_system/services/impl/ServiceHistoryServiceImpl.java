package com.itacademy.jd2.lg.mobile_system.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.lg.mobile_system.dao.IServiceHistoryDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.ServiceHistory;
import com.itacademy.jd2.lg.mobile_system.dao.impl.ServiceHistoryDaoImpl;
import com.itacademy.jd2.lg.mobile_system.services.IServiceHistoryService;

public class ServiceHistoryServiceImpl implements IServiceHistoryService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceHistoryServiceImpl.class);
	public static final IServiceHistoryService SERVICEHISTORY_SERVICE = new ServiceHistoryServiceImpl();

	private ServiceHistoryServiceImpl() {

	}

	private IServiceHistoryDao dao = ServiceHistoryDaoImpl.SERVICEHISTORY_DAO;

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