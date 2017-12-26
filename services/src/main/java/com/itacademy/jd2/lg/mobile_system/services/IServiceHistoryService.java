package com.itacademy.jd2.lg.mobile_system.services;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.ServiceHistory;

public interface IServiceHistoryService {
	ServiceHistory get(Integer id);

	void remove(Integer id);

	List<ServiceHistory> getAll();

	List<ServiceHistory> getAll(int limit, int offset);

	void insert(ServiceHistory serviceHistory);

	void update(ServiceHistory serviceHistory);

}

