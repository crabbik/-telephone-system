package com.itacademy.jd2.lg.ms.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.lg.ms.dao.IPhoneNumber2InvoiceDao;
import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber2Invoice;
import com.itacademy.jd2.lg.ms.dao.filter.PhoneNumber2InvoiceFilter;
import com.itacademy.jd2.lg.ms.services.IPhoneNumber2InvoiceService;

@Service
public class PhoneNumber2InvoiceServiceImpl implements IPhoneNumber2InvoiceService {

	@Autowired
	private IPhoneNumber2InvoiceDao dao;

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}

	@Override
	public PhoneNumber2Invoice save(PhoneNumber2Invoice phoneNumber2Invoice) {
		Timestamp modifiedDate = new Timestamp(new Date().getTime());
		phoneNumber2Invoice.setModified(modifiedDate);
		if (phoneNumber2Invoice.getId() == null) {
			phoneNumber2Invoice.setCreated(modifiedDate);
			dao.insert(phoneNumber2Invoice);
		} else {
			dao.update(phoneNumber2Invoice);
		}
		return phoneNumber2Invoice;
	}

	@Override
	public PhoneNumber2Invoice get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<PhoneNumber2Invoice> getAll() {
		return dao.getAll();
	}

	@Override
	public Long getCount(PhoneNumber2InvoiceFilter filter) {
		return dao.count(filter);
	}

	@Override
	public List<PhoneNumber2Invoice> getAll(PhoneNumber2InvoiceFilter filter) {
		return dao.find(filter);
	}
}
