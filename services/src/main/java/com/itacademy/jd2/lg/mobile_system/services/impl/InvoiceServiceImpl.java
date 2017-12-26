package com.itacademy.jd2.lg.mobile_system.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.lg.mobile_system.dao.IInvoiceDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Invoice;
import com.itacademy.jd2.lg.mobile_system.dao.impl.InvoiceDaoImpl;
import com.itacademy.jd2.lg.mobile_system.services.IInvoiceService;

public class InvoiceServiceImpl implements IInvoiceService {
	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceServiceImpl.class);
	public static final IInvoiceService INVOICE_SERVICE = new InvoiceServiceImpl();

	private InvoiceServiceImpl() {

	}

	private IInvoiceDao dao = InvoiceDaoImpl.INVOICE_DAO;

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