package com.itacademy.jd2.lg.mobile_system.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.lg.mobile_system.dao.ITariffDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Tariff;
import com.itacademy.jd2.lg.mobile_system.dao.impl.TariffDaoImpl;
import com.itacademy.jd2.lg.mobile_system.services.ITariffService;

public class TariffServiceImpl implements ITariffService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TariffServiceImpl.class);
	public static final ITariffService TARIFF_DAO = new TariffServiceImpl();

	private TariffServiceImpl() {

	}

	private ITariffDao dao = TariffDaoImpl.TARIFF_DAO;

	@Override
	public Tariff get(Integer id) {
		return dao.get(id);
	}

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
			int id = dao.insert(tariff);
			tariff.setId(id);
		} else {
			dao.update(tariff);
		}
		return tariff;
	}

	@Override
	public List<Tariff> getAll() {
		return dao.getAll();
	}

	@Override
	public List<Tariff> getAll(int limit, int offset) {
		return dao.getAll(limit, offset);
	}

}