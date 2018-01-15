package com.itacademy.jd2.lg.ms.dao;

import java.util.List;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Invoice;

public interface IInvoiceDao {

	Invoice get(Integer id);

	void remove(Integer id);

	void update(Invoice invoice);

	int insert(Invoice invoice);

	List<Invoice> getAll();

	List<Invoice> getAll(int limit, int offset);
	
}
