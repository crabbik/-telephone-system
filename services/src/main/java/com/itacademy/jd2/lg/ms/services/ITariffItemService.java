package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.lg.ms.dao.dbmodel.TariffItem;
import com.itacademy.jd2.lg.ms.dao.filter.TariffItemFilter;

public interface ITariffItemService {
	TariffItem get(Integer id);

	@Transactional
	void remove(Integer id);

	@Transactional
	TariffItem save(TariffItem tariffItem);

	List<TariffItem> getAll();

	Long getCount(TariffItemFilter filter);

	List<TariffItem> getAll(TariffItemFilter filter);

}
