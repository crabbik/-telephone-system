package com.itacademy.jd2.lg.mobile_system.dao;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Operator;

public interface IOperatorDao {
	Operator get(Integer id);

	void remove(Integer id);

	void update(Operator operator);

	int insert(Operator operator);

	List<Operator> getAll();

	List<Operator> getAll(int limit, int offset);

}
