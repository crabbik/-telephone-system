package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneService;
import com.itacademy.jd2.lg.ms.dao.filter.ServiceFilter;

public interface IPhoneServiceService {
	PhoneService get(Integer id);

	@Transactional
	void remove(Integer id);

	@Transactional
	PhoneService save(PhoneService service);

	List<PhoneService> getAll();

	Long getCount(ServiceFilter filter);

	List<PhoneService> getAll(ServiceFilter filter);

}
