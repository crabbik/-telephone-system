package com.itacademy.jd2.lg.ms.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.ms.dao.IOperatorDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Operator;
import com.itacademy.jd2.lg.ms.dao.filter.OperatorFilter;
import com.itacademy.jd2.lg.ms.services.IOperatorService;

@Service
public class OperatorServiceImpl implements IOperatorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OperatorServiceImpl.class);
	@Autowired
	private IOperatorDao dao;

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public Operator save(Operator operator) {
		Timestamp modifiedDate = new Timestamp(new Date().getTime());
		operator.setModified(modifiedDate);
		if (operator.getId() == null) {
			operator.setCreated(modifiedDate);
			dao.insert(operator);
		} else {
			dao.update(operator);
		}
		return operator;
	}

	@Override
	public Operator get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<Operator> getAll() {
		return dao.getAll();
	}

	@Override
	public Long getCount(OperatorFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<Operator> getAll(OperatorFilter filter) {
		return dao.find(filter);
	}
}
