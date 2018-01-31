package com.itacademy.jd2.lg.ms.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.ms.dao.ITariffItemDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.TariffItem;
import com.itacademy.jd2.lg.ms.dao.filter.TariffItemFilter;
import com.itacademy.jd2.lg.ms.services.ITariffItemService;

@Service
public class TariffItemServiceImpl implements ITariffItemService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TariffItemServiceImpl.class);
	@Autowired
	private ITariffItemDao dao;

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
			dao.insert(tariffItem);
		} else {
			dao.update(tariffItem);
		}
		return tariffItem;
	}

	@Override
	public TariffItem get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<TariffItem> getAll() {
		return dao.getAll();
	}

	@Override
	public Long getCount(TariffItemFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<TariffItem> getAll(TariffItemFilter filter) {
		return dao.find(filter);
	}
}
