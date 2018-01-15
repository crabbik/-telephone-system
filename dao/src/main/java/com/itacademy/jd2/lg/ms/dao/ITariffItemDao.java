package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.TariffItem;

public interface ITariffItemDao {
	TariffItem get(Integer id);

	void remove(Integer id);

	void update(TariffItem tariffItem);

	int insert(TariffItem tariffItem);

	List<TariffItem> getAll();

	List<TariffItem> getAll(int limit, int offset);

}
