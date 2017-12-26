package com.itacademy.jd2.lg.mobile_system.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.lg.mobile_system.dao.IServiceDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Service;
import com.itacademy.jd2.lg.mobile_system.dao.impl.ServiceDaoImpl;
import com.itacademy.jd2.lg.mobile_system.services.IServiceService;

public class ServiceServiceImpl implements IServiceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceServiceImpl.class);
	public static final IServiceService SERVICE_SERVICE = new ServiceServiceImpl();

	private ServiceServiceImpl() {

	}

	private IServiceDao dao = ServiceDaoImpl.SERVICE_DAO;

	@Override
	public Service get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public Service save(Service service) {
		Timestamp modifiedDate = new Timestamp(new Date().getTime());
		service.setModified(modifiedDate);
		if (service.getId() == null) {
			service.setCreated(modifiedDate);
			int id = dao.insert(service);
			service.setId(id);
		} else {
			dao.update(service);
		}
		return service;
	}

	@Override
	public List<Service> getAll() {
		return dao.getAll();
	}

	@Override
	public List<Service> getAll(int limit, int offset) {
		return dao.getAll(limit, offset);
	}

}