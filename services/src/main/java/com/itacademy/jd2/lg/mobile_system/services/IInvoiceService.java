package com.itacademy.jd2.lg.mobile_system.services;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Invoice;

public interface IInvoiceService {
	Invoice get(Integer id);

	void remove(Integer id);

	Invoice save(Invoice invoice);

	List<Invoice> getAll();

	List<Invoice> getAll(int limit, int offset);

}
