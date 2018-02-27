package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneService;
import com.itacademy.jd2.lg.ms.dao.filter.ServiceFilter;

public interface IPhoneServiceDao extends IHibernateDao<PhoneService, Integer> {

	Long count(ServiceFilter filter);

	List<PhoneService> find(ServiceFilter filter);

}
