package com.itacademy.jd2.lg.ms.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.ms.dao.IInvoiceDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.Invoice;
import com.itacademy.jd2.lg.ms.dao.filter.InvoiceFilter;
import com.itacademy.jd2.lg.ms.services.IInvoiceService;

@Service
public class InvoiceServiceImpl implements IInvoiceService {
	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceServiceImpl.class);
	@Autowired
	private IInvoiceDao dao;

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public Invoice save(Invoice invoice) {
		Timestamp modifiedDate = new Timestamp(new Date().getTime());
		invoice.setModified(modifiedDate);
		if (invoice.getId() == null) {
			invoice.setCreated(modifiedDate);
			dao.insert(invoice);
		} else {
			dao.update(invoice);
		}
		return invoice;
	}

	@Override
	public Invoice get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<Invoice> getAll() {
		return dao.getAll();
	}

	@Override
	public Long getCount(InvoiceFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<Invoice> getAll(InvoiceFilter filter) {
		return dao.find(filter);
	}
}
