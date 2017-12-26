package com.itacademy.jd2.lg.mobile_system.dao;

import java.util.List;

import com.itacademy.jd2.lg.mobile_system.dao.dbmodel.Invoice;

public interface IInvoiceDao {

	Invoice get(Integer id);

	void remove(Integer id);

	void update(Invoice invoice);

	void insert(Invoice invoice);

	List<Invoice> getAll();

	List<Invoice> getAll(int limit, int offset);
	
}
