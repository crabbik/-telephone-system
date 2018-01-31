package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.ServiceHistory;
import com.itacademy.jd2.lg.ms.dao.filter.ServiceHistoryFilter;

public interface IServiceHistoryDao extends IHibernateDao<ServiceHistory, Integer> {
	Long count(ServiceHistoryFilter filter);

	List<ServiceHistory> find(ServiceHistoryFilter filter);

}
