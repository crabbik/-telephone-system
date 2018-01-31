package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Service;
import com.itacademy.jd2.lg.ms.dao.filter.ServiceFilter;

public interface IServiceService {
	Service get(Integer id);

	@Transactional
	void remove(Integer id);

	@Transactional
	Service save(Service service);

	List<Service> getAll();

	Long getCount(ServiceFilter filter);

	List<Service> getAll(ServiceFilter filter);

}
