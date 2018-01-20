package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Operator;

public interface IOperatorService {
	Operator get(Integer id);

	void remove(Integer id);

	Operator save(Operator operator);

	List<Operator> getAll();

	List<Operator> getAll(int limit, int offset);

}
