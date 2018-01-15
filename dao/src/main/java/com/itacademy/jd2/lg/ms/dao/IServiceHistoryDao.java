package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.ServiceHistory;

public interface IServiceHistoryDao {
	ServiceHistory get(Integer id);

	void remove(Integer id);

	void update(ServiceHistory serviceHistory);

	int insert(ServiceHistory serviceHistory);

	List<ServiceHistory> getAll();

	List<ServiceHistory> getAll(int limit, int offset);

}
