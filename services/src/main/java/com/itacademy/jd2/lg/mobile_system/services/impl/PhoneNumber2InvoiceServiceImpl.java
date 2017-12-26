package com.itacademy.jd2.lg.mobile_system.services.impl;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.IPhoneNumber2InvoiceDao;
import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.PhoneNumber2Invoice;
import com.itacademy.jd2.lg.mobile_system.dao.impl.PhoneNumber2InvoiceDaoImpl;
import com.itacademy.jd2.lg.mobile_system.services.IPhoneNumber2InvoiceService;

public class PhoneNumber2InvoiceServiceImpl implements IPhoneNumber2InvoiceService {

	public static final IPhoneNumber2InvoiceService PHONENUMBER_2_INVOICE_DAO = new PhoneNumber2InvoiceServiceImpl();

	private PhoneNumber2InvoiceServiceImpl() {

	}

	private IPhoneNumber2InvoiceDao dao = PhoneNumber2InvoiceDaoImpl.PHONENUMBER_2_INVOICE_DAO;

	@Override
	public PhoneNumber2Invoice get(Integer userId) {
		return dao.get(userId);
	}

	@Override
	public void remove(Integer userId) {
		dao.remove(userId);

	}

	@Override
	public List<PhoneNumber2Invoice> getAll() {
		return dao.getAll();
	}

	@Override
	public void update(PhoneNumber2Invoice phoneNumber2Invoice) {
		dao.update(phoneNumber2Invoice);

	}

	@Override
	public void insert(PhoneNumber2Invoice phoneNumber2Invoice) {
		dao.insert(phoneNumber2Invoice);
	}

	@Override
	public List<PhoneNumber2Invoice> getAll(int limit, int offset) {
		return dao.getAll(limit, offset);
	}

}