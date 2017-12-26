package com.itacademy.jd2.lg.mobile_system.services;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Tariff;

public interface ITariffService {
	Tariff get(Integer id);

	void remove(Integer id);

	Tariff save(Tariff tariff);

	List<Tariff> getAll();

	List<Tariff> getAll(int limit, int offset);

}
