package com.itacademy.jd2.lg.mobile_system.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.lg.mobile_system.dao.ITariffItemDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.TariffItem;
import com.itacademy.jd2.lg.mobile_system.dao.impl.TariffItemDaoImpl;
import com.itacademy.jd2.lg.mobile_system.services.ITariffItemService;

public class TariffItemServiceImpl implements ITariffItemService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TariffItemServiceImpl.class);
	public static final ITariffItemService TARIFFITEM_DAO = new TariffItemServiceImpl();

	private TariffItemServiceImpl() {

	}

	private ITariffItemDao dao = TariffItemDaoImpl.TARIFFITEM_DAO;

	@Override
	public TariffItem get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public TariffItem save(TariffItem tariffItem) {
		Timestamp modifiedDate = new Timestamp(new Date().getTime());
		tariffItem.setModified(modifiedDate);
		if (tariffItem.getId() == null) {
			tariffItem.setCreated(modifiedDate);
			int id = dao.insert(tariffItem);
			tariffItem.setId(id);
		} else {
			dao.update(tariffItem);
		}
		return tariffItem;
	}

	@Override
	public List<TariffItem> getAll() {
		return dao.getAll();
	}

	@Override
	public List<TariffItem> getAll(int limit, int offset) {
		return dao.getAll(limit, offset);
	}

}