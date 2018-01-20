package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Invoice;

public interface IInvoiceService {
	Invoice get(Integer id);

	void remove(Integer id);

	List<Invoice> getAll();

	List<Invoice> getAll(int limit, int offset);

	void update(Invoice invoice);

	void insert(Invoice invoice);

}
