package com.itacademy.jd2.lg.mobile_system.dao;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Tariff;

public interface ITariffDao {
	Tariff get(Integer id);

	void remove(Integer id);

	void update(Tariff tariff);

	int insert(Tariff tariff);

	List<Tariff> getAll();

	List<Tariff> getAll(int limit, int offset);

}
