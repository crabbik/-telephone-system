package com.itacademy.jd2.lg.mobile_system.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.mobile_system.dao.IInvoiceDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Invoice;
import com.itacademy.jd2.lg.mobile_system.dao.impl.InvoiceDaoImpl;
import com.itacademy.jd2.lg.mobile_system.services.IInvoiceService;

@Service
public class InvoiceServiceImpl implements IInvoiceService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(InvoiceServiceImpl.class);
	@Autowired
	private IInvoiceDao dao;

	@Override
	public Invoice get(Integer id) {
		return dao.get(id);
	}

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public void update(Invoice invoice) {
		dao.update(invoice);

	}

	@Override
	public void insert(Invoice invoice) {
		dao.insert(invoice);
	}

	@Override
	public List<Invoice> getAll() {
		return dao.getAll();
	}

	@Override
	public List<Invoice> getAll(int limit, int offset) {
		return dao.getAll(limit, offset);
	}

}