package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Service;

public interface IServiceDao {

	Service get(Integer id);

	void remove(Integer id);

	void update(Service service);

	int insert(Service service);

	List<Service> getAll();

	List<Service> getAll(int limit, int offset);

}