package com.itacademy.jd2.lg.mobile_system.services;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.TariffItem;

public interface ITariffItemService {
	TariffItem get(Integer id);

	void remove(Integer id);

	TariffItem save(TariffItem tariffItem);

	List<TariffItem> getAll();

	List<TariffItem> getAll(int limit, int offset);

}
