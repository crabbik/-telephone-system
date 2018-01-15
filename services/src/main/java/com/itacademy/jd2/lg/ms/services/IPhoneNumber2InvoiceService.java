package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.PhoneNumber2Invoice;

public interface IPhoneNumber2InvoiceService {
	PhoneNumber2Invoice get(Integer id);

	void remove(Integer id);

	void insert(PhoneNumber2Invoice phoneNumber2Invoice);

	void update(PhoneNumber2Invoice phoneNumber2Invoice);

	List<PhoneNumber2Invoice> getAll();

	List<PhoneNumber2Invoice> getAll(int limit, int offset);

}
