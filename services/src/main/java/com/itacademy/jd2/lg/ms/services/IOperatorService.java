package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Operator;
import com.itacademy.jd2.lg.ms.dao.filter.OperatorFilter;

public interface IOperatorService {
	Operator get(Integer id);

	@Transactional
	void remove(Integer id);

	@Transactional
	Operator save(Operator operator);

	List<Operator> getAll();

	Long getCount(OperatorFilter filter);

	List<Operator> getAll(OperatorFilter filter);

}
