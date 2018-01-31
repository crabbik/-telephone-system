package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Tariff;
import com.itacademy.jd2.lg.ms.dao.filter.TariffFilter;

public interface ITariffService {
	Tariff get(Integer id);

	@Transactional
	void remove(Integer id);

	@Transactional
	Tariff save(Tariff tariff);

	List<Tariff> getAll();

	Long getCount(TariffFilter filter);

	List<Tariff> getAll(TariffFilter filter);

}
