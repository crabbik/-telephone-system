package com.itacademy.jd2.lg.ms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.ms.dao.IPhoneNumber2InvoiceDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber2Invoice;
import com.itacademy.jd2.lg.ms.dao.impl.PhoneNumber2InvoiceDaoImpl;
import com.itacademy.jd2.lg.ms.services.IPhoneNumber2InvoiceService;
@Service
public class PhoneNumber2InvoiceServiceImpl implements IPhoneNumber2InvoiceService {

	@Autowired
	private IPhoneNumber2InvoiceDao dao;

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