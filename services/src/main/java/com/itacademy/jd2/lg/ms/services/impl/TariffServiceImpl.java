package com.itacademy.jd2.lg.ms.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.ms.dao.ITariffDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Tariff;
import com.itacademy.jd2.lg.ms.dao.filter.TariffFilter;
import com.itacademy.jd2.lg.ms.services.ITariffService;

@Service
public class TariffServiceImpl implements ITariffService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TariffServiceImpl.class);

	@Autowired
	private ITariffDao dao;

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public Tariff save(Tariff tariff) {
		Timestamp modifiedDate = new Timestamp(new Date().getTime());
		tariff.setModified(modifiedDate);
		if (tariff.getId() == null) {
			tariff.setCreated(modifiedDate);
			dao.insert(tariff);
		} else {
			dao.update(tariff);
		}
		return tariff;
	}

	@Override
	public Tariff get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<Tariff> getAll() {
		return dao.getAll();
	}

	@Override
	public Long getCount(TariffFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<Tariff> getAll(TariffFilter filter) {
		return dao.find(filter);
	}
}
