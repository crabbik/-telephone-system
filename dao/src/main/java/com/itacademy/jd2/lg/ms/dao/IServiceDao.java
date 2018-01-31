package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Service;
import com.itacademy.jd2.lg.ms.dao.filter.ServiceFilter;

public interface IServiceDao extends IHibernateDao<Service, Integer> {

	Long count(ServiceFilter filter);

	List<Service> find(ServiceFilter filter);

}
