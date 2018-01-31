package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Tariff;
import com.itacademy.jd2.lg.ms.dao.filter.TariffFilter;

public interface ITariffDao extends IHibernateDao<Tariff, Integer> {
	Long count(TariffFilter filter);

	List<Tariff> find(TariffFilter filter);

}
