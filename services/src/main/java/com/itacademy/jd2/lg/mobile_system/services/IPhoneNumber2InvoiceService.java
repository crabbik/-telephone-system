package com.itacademy.jd2.lg.mobile_system.services;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.PhoneNumber2Invoice;

public interface IPhoneNumber2InvoiceService {
	PhoneNumber2Invoice get(Integer id);

	void remove(Integer id);

	PhoneNumber2Invoice save(PhoneNumber2Invoice phoneNumber2Invoice);

	List<PhoneNumber2Invoice> getAll();

	List<PhoneNumber2Invoice> getAll(int limit, int offset);

}
