package com.itacademy.jd2.lg.mobile_system.dao;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Service;

public interface IServiceDao {

	Service get(Integer id);

	void remove(Integer id);

	void update(Service service);

	void insert(Service service);

	List<Service> getAll();

	List<Service> getAll(int limit, int offset);

}
