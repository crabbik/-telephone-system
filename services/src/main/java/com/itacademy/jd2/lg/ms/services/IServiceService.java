package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Service;

public interface IServiceService {
	Service get(Integer id);

	void remove(Integer id);

	Service save(Service service);

	List<Service> getAll();

	List<Service> getAll(int limit, int offset);

}
