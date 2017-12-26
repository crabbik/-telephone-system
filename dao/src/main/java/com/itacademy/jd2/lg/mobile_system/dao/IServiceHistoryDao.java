package com.itacademy.jd2.lg.mobile_system.dao;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.ServiceHistory;

public interface IServiceHistoryDao {
	ServiceHistory get(Integer id);

	void remove(Integer id);

	void update(ServiceHistory serviceHistory);

	void insert(ServiceHistory serviceHistory);

	List<ServiceHistory> getAll();

	List<ServiceHistory> getAll(int limit, int offset);

}
