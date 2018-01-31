package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.TariffItem;
import com.itacademy.jd2.lg.ms.dao.filter.TariffItemFilter;

public interface ITariffItemDao extends IHibernateDao<TariffItem, Integer> {
	Long count(TariffItemFilter filter);

	List<TariffItem> find(TariffItemFilter filter);

}
