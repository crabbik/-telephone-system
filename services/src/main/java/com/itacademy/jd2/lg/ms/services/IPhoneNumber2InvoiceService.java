package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber2Invoice;
import com.itacademy.jd2.lg.ms.dao.filter.PhoneNumber2InvoiceFilter;

public interface IPhoneNumber2InvoiceService {
	PhoneNumber2Invoice get(Integer id);

	@Transactional
	void remove(Integer id);

	@Transactional
	PhoneNumber2Invoice save(PhoneNumber2Invoice phoneNumber2Invoice);

	List<PhoneNumber2Invoice> getAll();

	Long getCount(PhoneNumber2InvoiceFilter filter);

	List<PhoneNumber2Invoice> getAll(PhoneNumber2InvoiceFilter filter);

}
