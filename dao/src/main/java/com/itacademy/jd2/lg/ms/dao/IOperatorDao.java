package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Operator;
import com.itacademy.jd2.lg.ms.dao.filter.OperatorFilter;

public interface IOperatorDao extends IHibernateDao<Operator, Integer> {
	Long count(OperatorFilter filter);

	List<Operator> find(OperatorFilter filter);

}
