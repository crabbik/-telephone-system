package com.itacademy.jd2.lg.mobile_system.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.mobile_system.dao.IOperatorDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Operator;
import com.itacademy.jd2.lg.mobile_system.dao.impl.OperatorDaoImpl;
import com.itacademy.jd2.lg.mobile_system.services.IOperatorService;
@Service
public class OperatorServiceImpl implements IOperatorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OperatorServiceImpl.class);
	@Autowired
	private IOperatorDao dao ;

	@Override
	public Operator get(Integer id) {
		return dao.get(id);
	}

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
			int id = dao.insert(operator);
			operator.setId(id);
		} else {
			dao.update(operator);
		}
		return operator;
	}

	@Override
	public List<Operator> getAll() {
		return dao.getAll();
	}

	@Override
	public List<Operator> getAll(int limit, int offset) {
		return dao.getAll(limit, offset);
	}

}