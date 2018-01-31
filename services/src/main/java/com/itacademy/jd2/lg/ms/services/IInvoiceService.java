package com.itacademy.jd2.lg.ms.services;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.lg.ms.dao.dbmodel.Invoice;
import com.itacademy.jd2.lg.ms.dao.filter.InvoiceFilter;

public interface IInvoiceService {
	Invoice get(Integer id);

	@Transactional
	void remove(Integer id);

	@Transactional
	Invoice save(Invoice invoice);

	List<Invoice> getAll();

	Long getCount(InvoiceFilter filter);

	List<Invoice> getAll(InvoiceFilter filter);

}
