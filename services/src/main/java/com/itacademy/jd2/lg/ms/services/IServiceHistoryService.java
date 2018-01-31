package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.lg.ms.dao.dbmodel.ServiceHistory;
import com.itacademy.jd2.lg.ms.dao.filter.ServiceHistoryFilter;

public interface IServiceHistoryService {
	ServiceHistory get(Integer id);

	@Transactional
	void remove(Integer id);

	@Transactional
	ServiceHistory save(ServiceHistory serviceHistory);

	List<ServiceHistory> getAll();

	Long getCount(ServiceHistoryFilter filter);

	List<ServiceHistory> getAll(ServiceHistoryFilter filter);

}
